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
 * Date: Monday January 31, 2022
 * Time: 10:12 AM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="person")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Person {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="guid", updatable = false, nullable = false)
    private UUID guid;

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    @Column(name="ext_id")
    private String externalId;

    @Column(name="email")
    private String email;

    @Column(name="phone")
    private String phone;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="dt_created")
    private Date dateCreated;

    @Column(name="comments")
    private String comments;

    @ManyToMany
    @JoinTable(name="psn_app_xref", joinColumns = @JoinColumn(name="guid")
            , inverseJoinColumns = @JoinColumn(name="app_id"))
    public Set<Application> applications;

    @Transient
    private List<PersonRole> roles;

    public UUID getGuid() {
        return guid;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

    public List<PersonRole> getRoles() {
        return roles;
    }

    public void setRoles(List<PersonRole> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equal(firstname, person.firstname) && Objects.equal(lastname, person.lastname) && Objects.equal(externalId, person.externalId) && Objects.equal(email, person.email) && Objects.equal(phone, person.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(firstname, lastname, externalId, email, phone);
    }

    @Override
    public String toString() {
        return "Person{" +
                "guid=" + guid +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", externalId='" + externalId + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", dateCreated=" + dateCreated +
                ", comments='" + comments + '\'' +
                ", applications=" + applications +
                ", roles=" + roles +
                '}';
    }

}
