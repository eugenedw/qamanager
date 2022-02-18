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
 * Date: Monday January 31, 2022
 * Time: 5:43 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="psn_app_xref")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ApplicationPersonXref {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="guid", updatable = false, nullable = false)
    private UUID guid;

    @Column(name="app_id")
    private UUID appId;

    @Column(name="person_id")
    private UUID personId;

    public UUID getGuid() {
        return guid;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }

    public UUID getAppId() {
        return appId;
    }

    public void setAppId(UUID appId) {
        this.appId = appId;
    }

    public UUID getPersonId() {
        return personId;
    }

    public void setPersonId(UUID personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationPersonXref that = (ApplicationPersonXref) o;
        return Objects.equal(appId, that.appId) && Objects.equal(personId, that.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(appId, personId);
    }

    @Override
    public String toString() {
        return "ApplicationPersonXref{" +
                "guid=" + guid +
                ", appId='" + appId + '\'' +
                ", personId='" + personId + '\'' +
                '}';
    }

}
