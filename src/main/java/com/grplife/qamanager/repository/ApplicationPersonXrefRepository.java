package com.grplife.qamanager.repository;

import com.grplife.qamanager.model.ApplicationPersonXref;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Project: qamanager
 * Package: com.grplife.qamanager.repository
 * <p>
 * User: eugenewilliams
 * Date: Monday January 31, 2022
 * Time: 5:52 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface ApplicationPersonXrefRepository extends JpaRepository<ApplicationPersonXref, UUID> {
}
