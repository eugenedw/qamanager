package com.grplife.qamanager.repository;

import com.grplife.qamanager.model.TestPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Project: qamanager
 * Package: com.grplife.qamanager.repository
 * <p>
 * User: eugenewilliams
 * Date: Monday February 07, 2022
 * Time: 9:07 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface TestPlanRepository extends JpaRepository<TestPlan, UUID> {
    List<TestPlan> findByApplicationId(String applicationId);
}
