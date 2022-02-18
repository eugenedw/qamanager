package com.grplife.qamanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 * A QA Manager Managed Application
 * <p>
 * com.grplife.qamanager.model.Application
 * <p>
 * Created by @author eugene
 * <p>
 * Date: 11/30/21 at 8:03 AM
 */
@Entity
@Table(name="application")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Application {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="guid", updatable = false, nullable = false)
    private UUID guid;

    @Column(name="dt_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name="name")
    private String name;

    @Column(name="program_id")
    private String programId;

    @Column(name="app_id")
    private String appId;

    @Column(name="artifact")
    private String artifact;

    @Column(name="app_type")
    private ApplicationType type;

    @Column(name="automated", columnDefinition="int default 0")
    private int automated;

    @Column(name="product_owner")
    private String productOwner;

    @Column(name="project_mgr")
    private String projectManager;

    @Column(name="comments")
    private String comments;

    @ManyToMany
    @JoinTable(name="psn_app_xref", joinColumns = @JoinColumn(name="app_id")
                                  , inverseJoinColumns = @JoinColumn(name="person_id"))
    public Set<Person> people;

    @Transient
    private BusinessProgram program;

    public UUID getGuid() {
        return guid;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getArtifact() {
        return artifact;
    }

    public void setArtifact(String artifact) {
        this.artifact = artifact;
    }

    public ApplicationType getType() {
        return type;
    }

    public void setType(ApplicationType type) {
        this.type = type;
    }

    public int getAutomated() {
        return automated;
    }

    public void setAutomated(int automated) {
        this.automated = automated;
    }

    public String getProductOwner() {
        return productOwner;
    }

    public void setProductOwner(String productOwner) {
        this.productOwner = productOwner;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public BusinessProgram getProgram() {
        return program;
    }

    public void setProgram(BusinessProgram program) {
        this.program = program;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Application)) return false;
        Application that = (Application) o;
        return com.google.common.base.Objects.equal(getName(), that.getName()) &&
                com.google.common.base.Objects.equal(getProgramId(), that.getProgramId()) &&
                com.google.common.base.Objects.equal(getAppId(), that.getAppId()) &&
                com.google.common.base.Objects.equal(getArtifact(), that.getArtifact()) &&
                getType() == that.getType();
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(getName(), getProgramId(), getAppId(), getArtifact(), getType());
    }

    @Override
    public String toString() {
        return "Application{" +
                "guid=" + guid +
                ", dateCreated=" + dateCreated +
                ", name='" + name + '\'' +
                ", programId='" + programId + '\'' +
                ", appId='" + appId + '\'' +
                ", artifact='" + artifact + '\'' +
                ", type=" + type +
                ", automated=" + automated +
                ", productOwner='" + productOwner + '\'' +
                ", projectManager='" + projectManager + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }

}
