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
 * Time: 5:16 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="test_case_type")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TestCaseType {

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

    @Column(name="typ_code")
    private String typeCode;

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

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestCaseType that = (TestCaseType) o;
        return Objects.equal(name, that.name) && Objects.equal(typeCode, that.typeCode);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, typeCode);
    }

    @Override
    public String toString() {
        return "TestCaseType{" +
                "guid=" + guid +
                ", name='" + name + '\'' +
                ", typeCode='" + typeCode + '\'' +
                '}';
    }

}
