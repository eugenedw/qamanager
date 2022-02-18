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
 * Time: 3:45 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="person_role")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PersonRole {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="guid", updatable = false, nullable = false)
    private UUID guid;

    @Column(name="role_name")
    private String roleName;

    @Column(name="role_code")
    private String roleCode;

    public UUID getGuid() {
        return guid;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonRole that = (PersonRole) o;
        return Objects.equal(roleName, that.roleName) && Objects.equal(roleCode, that.roleCode);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(roleName, roleCode);
    }

    @Override
    public String toString() {
        return "PersonRole{" +
                "guid=" + guid +
                ", roleName='" + roleName + '\'' +
                ", roleCode='" + roleCode + '\'' +
                '}';
    }

}
