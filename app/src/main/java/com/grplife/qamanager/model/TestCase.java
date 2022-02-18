package com.grplife.qamanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 *
 */
@Entity
@Table(name="test_case")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TestCase {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="guid", updatable = false, nullable = false)
    private UUID guid;

    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    //@Column(name="tc_id", columnDefinition = "serial", unique = true, nullable = false)
    @Generated(GenerationTime.INSERT)
    @Column(name = "tc_id", columnDefinition = "serial", updatable = false)
    private int testCaseId;

    @Column(name="app_id")
    private String applicationId;

    @Column(name="br_id")
    private String businessRequirement;

    @Column(name="tc_name")
    private String name;

    @Column(name="tc_type")
    private String testCaseType;

    @Column(name="is_automated", columnDefinition="int default 0")
    private short automated;

    @Column(name="priority")
    private TestCasePriority testCasePriority;

    @Column(name="ttc")
    private String timeToComplete;

    @Column(name="created_by")
    private String createdBy;

    @Column(name="dt_created")
    private Date dateCreated;

    @Column(name="preconditions")
    private String preconditions;

    @Column(name="steps")
    private String steps;

    @Column(name="exp_rslt")
    private String expectedResults;

    @Column(name="tc_group_id")
    private String groupId;

    @Column(name="deleted", columnDefinition = "int default 0")
    private short deleted;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name="test_case_execution_xref", joinColumns = @JoinColumn(name="tc_id")
            , inverseJoinColumns = @JoinColumn(name="exec_id"))
    public Set<TestCaseExecution> testCaseExecutions;

    public UUID getGuid() {
        return guid;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }

    public int getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(int testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getBusinessRequirement() {
        return businessRequirement;
    }

    public void setBusinessRequirement(String businessRequirement) {
        this.businessRequirement = businessRequirement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTestCaseType() {
        return testCaseType;
    }

    public void setTestCaseType(String testCaseType) {
        this.testCaseType = testCaseType;
    }

    public short getAutomated() {
        return automated;
    }

    public void setAutomated(short automated) {
        this.automated = automated;
    }

    public TestCasePriority getTestCasePriority() {
        return testCasePriority;
    }

    public void setTestCasePriority(TestCasePriority testCasePriority) {
        this.testCasePriority = testCasePriority;
    }

    public String getTimeToComplete() {
        return timeToComplete;
    }

    public void setTimeToComplete(String timeToComplete) {
        this.timeToComplete = timeToComplete;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getPreconditions() {
        return preconditions;
    }

    public void setPreconditions(String preconditions) {
        this.preconditions = preconditions;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getExpectedResults() {
        return expectedResults;
    }

    public void setExpectedResults(String expectedResults) {
        this.expectedResults = expectedResults;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public short getDeleted() {
        return deleted;
    }

    public void setDeleted(short deleted) {
        this.deleted = deleted;
    }

    public Set<TestCaseExecution> getTestCaseExecutions() {
        return testCaseExecutions;
    }

    public void setTestCaseExecutions(Set<TestCaseExecution> testCaseExecutions) {
        this.testCaseExecutions = testCaseExecutions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestCase testCase = (TestCase) o;
        return automated == testCase.automated && Objects.equal(testCaseId, testCase.testCaseId) && Objects.equal(applicationId, testCase.applicationId) && Objects.equal(name, testCase.name) && Objects.equal(testCaseType, testCase.testCaseType) && testCasePriority == testCase.testCasePriority && Objects.equal(groupId, testCase.groupId) && Objects.equal(deleted, testCase.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(testCaseId, applicationId, name, testCaseType, automated, testCasePriority, groupId, deleted);
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "guid=" + guid +
                ", testCaseId=" + testCaseId +
                ", applicationId='" + applicationId + '\'' +
                ", businessRequirement='" + businessRequirement + '\'' +
                ", name='" + name + '\'' +
                ", testCaseType='" + testCaseType + '\'' +
                ", automated=" + automated +
                ", testCasePriority=" + testCasePriority +
                ", timeToComplete=" + timeToComplete +
                ", createdBy='" + createdBy + '\'' +
                ", dateCreated=" + dateCreated +
                ", preconditions='" + preconditions + '\'' +
                ", steps='" + steps + '\'' +
                ", expectedResults='" + expectedResults + '\'' +
                ", groupId='" + groupId + '\'' +
                ", deleted='" + deleted + '\'' +
        '}';
    }

}
