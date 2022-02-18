package com.grplife.qamanager.api;

import com.grplife.qamanager.model.Person;
import com.grplife.qamanager.repository.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Project: qamanager
 * Package: com.grplife.qamanager.api
 * <p>
 * User: eugenewilliams
 * Date: Monday January 31, 2022
 * Time: 5:53 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/person")
public class PersonController {

    private static Logger logger = LogManager.getLogger(PersonController.class);

    @Autowired
    private PersonRepository personRepository;

    @PostMapping("")
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        try {
            person.setDateCreated(new Date());
            Person p = personRepository.save(person);
            return new ResponseEntity<>(p, HttpStatus.OK);
        }
        catch(Exception e){
            logger.error("Could not save person",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Person> savePerson(@RequestBody Person person){
        try{
            Optional<Person> _personData = personRepository.findById(person.getGuid());
            if( _personData.isPresent() ){
                Person _person = _personData.get();
                _person.setComments(person.getComments());
                _person.setEmail(person.getEmail());
                _person.setLastname(person.getLastname());
                _person.setExternalId(person.getExternalId());
                _person.setFirstname(person.getFirstname());
                personRepository.save(person);
                return new ResponseEntity<>(_person,HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
        }
        catch(Exception e){
            logger.error("Could not save person [put]",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/{page}/{size}")
    public ResponseEntity<List<Person>> getPeople(@PathVariable Integer page, @PathVariable Integer size,
                                                  @RequestParam(required=false) String sort,
                                                  @RequestParam(required=false) String order){
        try{
            List<Person> people = new ArrayList<Person>();
            Pageable peoplePage = null;
            if( !StringUtils.isEmpty(sort) ){
                if( "desc".equalsIgnoreCase(order) ){
                    peoplePage = PageRequest.of(page, size, Sort.by(sort).descending());
                }
                else{
                    peoplePage = PageRequest.of(page, size, Sort.by(sort).ascending());
                }
            }
            personRepository.findAll(peoplePage);
            if( people.isEmpty() ){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(people, HttpStatus.OK);
        }
        catch(Exception e){
            logger.error("Could not retrieve people by app id",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/application/{appId}")
    public ResponseEntity<List<Person>> getPeopleByAppId(@PathVariable String appId){
        try{
            List<Person> people = new ArrayList<Person>();
            people = personRepository.getByApplicationId(UUID.fromString(appId));
            if( people.isEmpty() ){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(people, HttpStatus.OK);
        }
        catch(Exception e){
            logger.error("Could not retrieve people by app id",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
