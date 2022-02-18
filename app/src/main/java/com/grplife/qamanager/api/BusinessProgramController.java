package com.grplife.qamanager.api;

import com.grplife.qamanager.model.Application;
import com.grplife.qamanager.model.BusinessProgram;
import com.grplife.qamanager.repository.BusinessProgramRepository;
import com.grplife.qamanager.service.BusinessProgramService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * The controller for BusinessProgram
 * <p>
 * com.grplife.qamanager.api.BusinessProgramController
 * <p>
 * Created by @author eugene
 * <p>
 * Date: 12/1/21 at 10:24 AM
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/program")
public class BusinessProgramController {

    private static Logger logger = LogManager.getLogger(BusinessProgramController.class);

    @Autowired
    private BusinessProgramRepository businessProgramRepository;

    @Autowired
    private BusinessProgramService businessProgramService;

    @GetMapping("/list")
    public ResponseEntity<List<BusinessProgram>> getPrograms(){
        try {
            List<BusinessProgram> programs = new ArrayList<BusinessProgram>();
            businessProgramRepository.findAll().forEach(programs::add);
            if (programs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            businessProgramService.build(programs);
            return new ResponseEntity<>(programs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessProgram> getProgramById(@PathVariable String id){
        try{
            BusinessProgram program = businessProgramRepository.getOne(UUID.fromString(id));
            if( program != null ){
                businessProgramService.build(program);
                return new ResponseEntity<>(program, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<BusinessProgram> createBusinessProgram(@RequestBody BusinessProgram program){
        try{
            program.setDateCreated(new Date());
            BusinessProgram _program = businessProgramRepository.save(program);
            return new ResponseEntity<>(_program,HttpStatus.CREATED);
        }
        catch(Exception e){
            logger.error("Could not save the business program",e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<BusinessProgram> updateProgram(@RequestBody BusinessProgram program){
        try{
            Optional<BusinessProgram> programData = businessProgramRepository.findById(program.getGuid());
            if( programData.isPresent() ){
                BusinessProgram _program = programData.get();
                _program.setName(program.getName());
                _program.setShortName(program.getShortName());
                businessProgramRepository.save(_program);
                businessProgramService.build(_program);
                return new ResponseEntity<>(_program,HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
