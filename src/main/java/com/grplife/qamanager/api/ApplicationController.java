package com.grplife.qamanager.api;

import com.grplife.qamanager.service.ApplicationService;
import com.grplife.qamanager.model.Application;
import com.grplife.qamanager.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.*;

/**
 * The API Application Controller
 * <p>
 * com.grplife.qamanager.api.ApplicationController
 * <p>
 * Created by @author eugene
 * <p>
 * Date: 12/1/21 at 12:55 AM
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/application")
public class ApplicationController {

    private static Logger logger = LogManager.getLogger(ApplicationController.class);

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/list")
    private ResponseEntity<List<Application>> getApplications(){
        try {
            List<Application> applications = new ArrayList<Application>();
            applicationRepository.findAll().forEach(applications::add);
            if (applications.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            applicationService.build(applications);
            return new ResponseEntity<>(applications, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity<Application> getApplicationById(@PathVariable String id){
        try{
            Optional<Application> applicationData = applicationRepository.findById(UUID.fromString(id));
            if( applicationData.isPresent() ){
                Application application = applicationData.get();
                applicationService.build(application);
                return new ResponseEntity<>(application, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    private ResponseEntity<Application> createApplication(@RequestBody Application application){
        try{
            application.setDateCreated(new Date());
            Application _application = applicationRepository.save(application);
            applicationService.build(_application);
            return new ResponseEntity<>(_application, HttpStatus.CREATED);
        }
        catch (Exception e){
            logger.error("There was a problem saving the application",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    private ResponseEntity<Application> saveApplication(@RequestBody Application application){
        try{
            Optional<Application> applicationData = applicationRepository.findById(application.getGuid());
            if( applicationData.isPresent() ){
                Application _application = applicationData.get();
                _application.setAppId(application.getAppId());
                _application.setArtifact(application.getArtifact());
                _application.setAutomated(application.getAutomated());
                _application.setComments(application.getComments());
                _application.setName(application.getName());
                _application.setProductOwner(application.getProductOwner());
                _application.setProgramId(application.getProgramId());
                _application.setProjectManager(application.getProjectManager());
                _application.setType(application.getType());
                applicationRepository.save(_application);
                applicationService.build(_application);
                return new ResponseEntity<>(_application,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/list")
    private ResponseEntity<List<String>> saveApplicationList(@RequestBody List<Application> applications){
        try{
            if( applications != null && applications.size() > 0 ){
                List<String> errors = new ArrayList<String>();
                for( Application a : applications ){
                    try {
                        applicationRepository.save(a);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                        errors.add("[" + a.getName() + "] Not Added; ".concat(e.getMessage()));
                    }
                }
                return new ResponseEntity<>(errors,HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
