package com.grplife.qamanager.service;

import com.grplife.qamanager.model.Person;
import com.grplife.qamanager.model.Milestone;
import com.grplife.qamanager.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Project: qamanager
 * Package: com.grplife.qamanager.service
 * <p>
 * User: eugenewilliams
 * Date: Monday January 31, 2022
 * Time: 4:17 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
@Service
public class MilestoneService {

    @Autowired
    private PersonRepository personRepository;

    public void build(List<Milestone> projects){
        if(!projects.isEmpty()) {
            projects.forEach(p->{
                build(p);
            });
        }
    }

    public void build(Milestone project){
        if(!StringUtils.isEmpty(project.getCreatedById())){
            Optional<Person> personData = personRepository.findById(UUID.fromString(project.getCreatedById()));
            project.setCreator(personData.get());
        }
    }

}
