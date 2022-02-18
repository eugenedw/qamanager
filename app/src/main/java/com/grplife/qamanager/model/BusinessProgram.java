package com.grplife.qamanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * The business program area
 * <p>
 * com.grplife.qamanager.model.BusinessProgram
 * <p>
 * Created by @author eugene
 * <p>
 * Date: 12/1/21 at 10:16 AM
 */
@Entity
@Table(name="business_program")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BusinessProgram {

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

    @Column(name="name_short")
    private String shortName;

    @Column(name="dt_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Transient
    private List<Application> applications;

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusinessProgram)) return false;
        BusinessProgram that = (BusinessProgram) o;
        return Objects.equal(getName(), that.getName()) && Objects.equal(getShortName(), that.getShortName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName(), getShortName());
    }

    @Override
    public String toString() {
        return "BusinessProgram{" +
                "guid=" + guid +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }

}
