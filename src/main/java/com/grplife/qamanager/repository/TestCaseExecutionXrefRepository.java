package com.grplife.qamanager.repository;

import com.grplife.qamanager.model.TestCase;
import com.grplife.qamanager.model.TestCaseExecutionXref;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Project: qamanager
 * Package: com.grplife.qamanager.repository
 * <p>
 * User: eugenewilliams
 * Date: Monday February 07, 2022
 * Time: 11:22 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface TestCaseExecutionXrefRepository extends JpaRepository<TestCaseExecutionXref, UUID> {
    TestCaseExecutionXref getByExecutionIdAndTestCaseId(UUID executionId, UUID testCaseId);
    @Transactional
    @Modifying
    @Query("DELETE FROM TestCaseExecutionXref WHERE executionId=:executionId")
    void deleteExecutionXrefs(@Param("executionId") UUID executionId);
}
