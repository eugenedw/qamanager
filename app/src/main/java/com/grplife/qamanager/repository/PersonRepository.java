package com.grplife.qamanager.repository;

import com.grplife.qamanager.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Project: qamanager
 * Package: com.grplife.qamanager.repository
 * <p>
 * User: eugenewilliams
 * Date: Monday January 31, 2022
 * Time: 4:21 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
    @Query("SELECT p FROM Person p INNER JOIN p.applications app WHERE app.guid = :appId")
    public List<Person> getByApplicationId(@Param("appId") UUID appId);
}
