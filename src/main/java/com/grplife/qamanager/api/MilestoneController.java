package com.grplife.qamanager.api;

import com.grplife.qamanager.model.Milestone;
import com.grplife.qamanager.repository.MilestoneRepository;
import com.grplife.qamanager.service.MilestoneService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Project: qamanager
 * Package: com.grplife.qamanager.api
 * <p>
 * User: eugenewilliams
 * Date: Monday January 31, 2022
 * Time: 4:08 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/milestone")
public class MilestoneController {

    private static Logger logger = LogManager.getLogger(MilestoneController.class);

    @Autowired
    private MilestoneRepository milestoneRepository;

    @Autowired
    private MilestoneService milestoneService;

    @GetMapping("/{id}")
    private ResponseEntity<Milestone> getMilestoneById(@PathVariable String id){
        try{
            Optional<Milestone> milestoneData = milestoneRepository.findById(UUID.fromString(id));
            if( milestoneData.isPresent() ){
                Milestone milestone = milestoneData.get();
                milestoneService.build(milestone);
                return new ResponseEntity<>(milestone, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/application/{appId}")
    public ResponseEntity<List<Milestone>> getMilestonesByAppId(@PathVariable String appId){
        try{
            List<Milestone> projects = new ArrayList<Milestone>();
            projects = milestoneRepository.findByApplicationId(appId);
            if( projects.isEmpty() ){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            milestoneService.build(projects);
            return new ResponseEntity<>(projects, HttpStatus.OK);
        }
        catch(Exception e){
            logger.error("Could not retrieve projects by app id",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    private ResponseEntity<Milestone> createMilestone(@RequestBody Milestone project){
        try{
            project.setDateCreated(new Date());
            Milestone _project = milestoneRepository.save(project);
            milestoneService.build(_project);
            return new ResponseEntity<>(_project, HttpStatus.CREATED);
        }
        catch (Exception e){
            logger.error("There was a problem saving the test project",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    private ResponseEntity<Milestone> saveMilestone(@RequestBody Milestone project){
        try{
            Optional<Milestone> projectData = milestoneRepository.findById(project.getGuid());
            if( projectData.isPresent() ){
                Milestone _project = projectData.get();
                _project.setName(project.getName());
                return new ResponseEntity<>(_project,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
        catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/:guid")
    private ResponseEntity<Milestone> deleteMilestone(@PathVariable String projectId){
        try{
            Optional<Milestone> projectData = milestoneRepository.findById(UUID.fromString(projectId));
            if( projectData.isPresent() ){
                Milestone _project = projectData.get();
                _project.setDeleted((short)1);
                return new ResponseEntity<>(_project,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
        catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
