package com.grplife.qamanager.repository;

import com.grplife.qamanager.model.TestCase;
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
 * Date: Monday February 07, 2022
 * Time: 7:11 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface TestCaseRepository extends JpaRepository<TestCase, UUID> {

    List<TestCase> getByApplicationIdAndDeleted(String applicationId,short deleted);

    @Query("SELECT tc FROM TestCase tc INNER JOIN tc.testCaseExecutions tcx where tcx.guid = :executionId and tc.deleted=0")
    List<TestCase> getTestCasesByExecution(@Param("executionId") String executionId);

    @Query("UPDATE TestCase SET groupId=null WHERE groupId=:groupId")
    void updateTestCaseGroupsOnGroupDelete(@Param("groupId") String groupId);

}
