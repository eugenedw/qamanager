package com.grplife.qamanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.common.base.Objects;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.*;

/**
 * Project: qamanager
 * Package: com.grplife.qamanager.model
 * <p>
 * User: eugenewilliams
 * Date: Monday February 07, 2022
 * Time: 7:11 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="test_plan")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TestPlan {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="guid", updatable = false, nullable = false)
    private UUID guid;

    @Column(name="name")
    private String name;

    @Column(name="app_id")
    private String applicationId;

    @Column(name="dt_created")
    private Date dateCreated;

    @Column(name="dt_processed")
    private Date dateProcessed;

    @Column(name="created_by")
    private String createdBy;

    @Column(name="description", length=2000)
    private String description;

    @Column(name="milestone_id")
    private String milestoneId;

    @Column(name="metrics", length=10000)
    private String metrics;

    @Column(name="deleted", columnDefinition = "int default 0")
    private short deleted;

    @Transient
    private Map<String,Object> metricsData;

    @Transient
    private List<TestCaseExecution> executions;

    public UUID getGuid() {
        return guid;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateProcessed() {
        return dateProcessed;
    }

    public void setDateProcessed(Date dateProcessed) {
        this.dateProcessed = dateProcessed;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(String milestoneId) {
        this.milestoneId = milestoneId;
    }

    public String getMetrics() {
        return metrics;
    }

    public void setMetrics(String metrics) {
        this.metrics = metrics;
    }

    public short getDeleted() {
        return deleted;
    }

    public void setDeleted(short deleted) {
        this.deleted = deleted;
    }

    public List<TestCaseExecution> getExecutions() {
        return executions;
    }

    public void setExecutions(List<TestCaseExecution> executions) {
        this.executions = executions;
    }

    public Map<String, Object> getMetricsData() {
        LinkedTreeMap<String,Object> metricsData = new LinkedTreeMap<>();
        if( !StringUtils.isEmpty(this.metrics) ){
            Gson gson = new Gson();
            metricsData = gson.fromJson(metrics,metricsData.getClass());
        }
        return metricsData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestPlan testPlan = (TestPlan) o;
        return Objects.equal(name, testPlan.name) && Objects.equal(applicationId, testPlan.applicationId) && Objects.equal(createdBy, testPlan.createdBy) && Objects.equal(description, testPlan.description) && Objects.equal(milestoneId, testPlan.milestoneId) && Objects.equal(deleted, testPlan.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, applicationId, createdBy, description, milestoneId, deleted);
    }

    @Override
    public String toString() {
        return "TestPlan{" +
                "guid=" + guid +
                ", name='" + name + '\'' +
                ", applicationId='" + applicationId + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateProcessed=" + dateProcessed +
                ", createdBy='" + createdBy + '\'' +
                ", description='" + description + '\'' +
                ", milestoneId='" + milestoneId + '\'' +
                ", metrics='" + metrics + '\'' +
                ", deleted='" + deleted + '\'' +
                ", executions='" + executions + '\'' +
                '}';
    }

}
