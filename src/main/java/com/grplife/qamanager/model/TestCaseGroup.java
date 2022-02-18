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
 * Time: 5:27 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="test_case_group")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TestCaseGroup {

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

    @Column(name="parent_id")
    private UUID parentId;

    @Column(name="name")
    private String name;

    @Column(name="sorder")
    private Integer sorder;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getParentId() {
        return parentId;
    }

    public void setParentId(UUID parentId) {
        this.parentId = parentId;
    }

    public Integer getSorder() {
        return sorder;
    }

    public void setSorder(Integer sorder) {
        this.sorder = sorder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestCaseGroup that = (TestCaseGroup) o;
        return Objects.equal(applicationId, that.applicationId) && Objects.equal(parentId, that.parentId) && Objects.equal(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(applicationId, parentId, name);
    }

    @Override
    public String toString() {
        return "TestCaseGroup{" +
                "guid=" + guid +
                ", applicationId='" + applicationId + '\'' +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", sorder='" + sorder + '\'' +
                '}';
    }

}
