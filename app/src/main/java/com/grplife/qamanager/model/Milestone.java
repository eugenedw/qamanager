package com.grplife.qamanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Project: qamanager
 * Package: com.grplife.qamanager.model
 * <p>
 * User: eugenewilliams
 * Date: Monday January 31, 2022
 * Time: 9:58 AM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="test_case_project")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Milestone {

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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="dt_created")
    private Date dateCreated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="dt_last_run")
    private Date dateLastRun;

    @Column(name="created_by_id")
    private String createdById;

    @Column(name="deleted", columnDefinition="int default 0")
    private short deleted;

    @Transient
    private Person creator;

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

    public String getCreatedById() {
        return createdById;
    }

    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    public short getDeleted() {
        return deleted;
    }

    public void setDeleted(short deleted) {
        this.deleted = deleted;
    }

    public Person getCreator() {
        return creator;
    }

    public void setCreator(Person creator) {
        this.creator = creator;
    }

    public Date getDateLastRun() {
        return dateLastRun;
    }

    public void setDateLastRun(Date dateLastRun) {
        this.dateLastRun = dateLastRun;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Milestone that = (Milestone) o;
        return deleted == that.deleted && Objects.equal(name, that.name) && Objects.equal(applicationId, that.applicationId) && Objects.equal(createdById, that.createdById);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, applicationId, createdById, deleted);
    }

    @Override
    public String toString() {
        return "TestProject{" +
                "guid=" + guid +
                ", name='" + name + '\'' +
                ", applicationId='" + applicationId + '\'' +
                ", dateCreated=" + dateCreated + '\'' +
                ", dateLastRun=" + dateLastRun + '\'' +
                ", createdById='" + createdById + '\'' +
                ", deleted=" + deleted +
                '}';
    }

}
