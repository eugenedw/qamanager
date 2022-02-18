package com.grplife.qamanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * Project: qamanager
 * Package: com.grplife.qamanager.model
 * <p>
 * User: eugenewilliams
 * Date: Sunday January 30, 2022
 * Time: 5:47 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="test_case_execution_xref")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TestCaseExecutionXref {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="guid", updatable = false, nullable = false)
    private UUID guid;

    @Column(name="exec_id")
    private UUID executionId;

    @Column(name="tc_id")
    private UUID testCaseId;

    public UUID getGuid() {
        return guid;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }

    public UUID getExecutionId() {
        return executionId;
    }

    public void setExecutionId(UUID exectuionId) {
        this.executionId = exectuionId;
    }

    public UUID getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(UUID testCaseId) {
        this.testCaseId = testCaseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestCaseExecutionXref that = (TestCaseExecutionXref) o;
        return Objects.equal(executionId, that.executionId) && Objects.equal(testCaseId, that.testCaseId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(executionId, testCaseId);
    }

    @Override
    public String toString() {
        return "TestCaseExecutionXref{" +
                "guid=" + guid +
                ", exectuionId='" + executionId + '\'' +
                ", testCaseId='" + testCaseId + '\'' +
                '}';
    }

}
