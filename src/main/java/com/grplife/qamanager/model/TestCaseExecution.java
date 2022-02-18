package com.grplife.qamanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Project: qamanager
 * Package: com.grplife.qamanager.model
 * <p>
 * User: eugenewilliams
 * Date: Sunday January 30, 2022
 * Time: 4:42 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="test_case_execution")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TestCaseExecution {

    public enum TestCaseSelect {
        ALL,
        SELECTED
    }

    public enum ExecutionMethod {
        MANUAL,
        AUTOMATED
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="guid", updatable = false, nullable = false)
    private UUID guid;

    @Column(name="app_id")
    private String applicationId;

    @Column(name="milestone_id")
    private String milestoneId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="dt_executed")
    private Date dateExecuted;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="dt_created")
    private Date dateCreated;

    @Column(name="trig_by")
    private String triggeredBy;

    @Column(name="test_pln_id")
    private String testPlanId;

    @Column(name="tc_select")
    private TestCaseSelect testCaseSelect;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="exec_meth")
    private ExecutionMethod executionMethod;

    @Column(name="deleted", columnDefinition = "int default 0")
    private short deleted;

    @ManyToMany
    @JoinTable(name="test_case_execution_xref", joinColumns = @JoinColumn(name="exec_id")
            , inverseJoinColumns = @JoinColumn(name="tc_id"))
    public Set<TestCase> testCases;

    public UUID getGuid() {
        return guid;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(String milestoneId) {
        this.milestoneId = milestoneId;
    }

    public Date getDateExecuted() {
        return dateExecuted;
    }

    public void setDateExecuted(Date dateExecuted) {
        this.dateExecuted = dateExecuted;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TestCaseSelect getTestCaseSelect() {
        return testCaseSelect;
    }

    public void setTestCaseSelect(TestCaseSelect testCaseSelect) {
        this.testCaseSelect = testCaseSelect;
    }

    public ExecutionMethod getExecutionMethod() {
        return executionMethod;
    }

    public void setExecutionMethod(ExecutionMethod executionMethod) {
        this.executionMethod = executionMethod;
    }

    public String getTriggeredBy() {
        return triggeredBy;
    }

    public void setTriggeredBy(String triggeredBy) {
        this.triggeredBy = triggeredBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getDeleted() {
        return deleted;
    }

    public void setDeleted(short deleted) {
        this.deleted = deleted;
    }

    public Set<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(Set<TestCase> testCases) {
        this.testCases = testCases;
    }

    public String getTestPlanId() {
        return testPlanId;
    }

    public void setTestPlanId(String testPlanId) {
        this.testPlanId = testPlanId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestCaseExecution that = (TestCaseExecution) o;
        return Objects.equal(executionMethod, that.executionMethod) && Objects.equal(testCaseSelect, that.testCaseSelect) && Objects.equal(milestoneId, that.milestoneId) && Objects.equal(name, that.name) && Objects.equal(applicationId, that.applicationId) && Objects.equal(dateExecuted, that.dateExecuted) && Objects.equal(dateCreated, that.dateCreated) && Objects.equal(triggeredBy, that.triggeredBy) && Objects.equal(testPlanId, that.testPlanId) && Objects.equal(deleted, that.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, executionMethod, testCaseSelect, applicationId, milestoneId, dateExecuted, dateCreated, triggeredBy, testPlanId, deleted);
    }

    @Override
    public String toString() {
        return "TestCaseExecution{" +
                "guid=" + guid +
                ", name='" + name + '\'' +
                ", applicationId='" + applicationId + '\'' +
                ", milestoneId='" + milestoneId + '\'' +
                ", dateExecuted=" + dateExecuted +
                ", dateCreated=" + dateCreated +
                ", triggeredBy='" + triggeredBy + '\'' +
                ", testPlanId='" + testPlanId + '\'' +
                ", description='" + description + '\'' +
                ", deleted='" + deleted + '\'' +
                ", testCaseSelect='" + testCaseSelect + '\'' +
                ", executionMethod='" + executionMethod + '\'' +
                '}';
    }

}
