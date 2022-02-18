package com.grplife.qamanager.repository;

import com.grplife.qamanager.model.Milestone;
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
 * Time: 10:02 AM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface MilestoneRepository extends JpaRepository<Milestone, UUID> {

    @Query("SELECT p FROM Milestone p where p.applicationId = :appId and p.deleted=0")
    List<Milestone> findByApplicationId(@Param("appId") String appId);

}
