package com.grplife.qamanager.repository;

import com.grplife.qamanager.model.Application;
import com.grplife.qamanager.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * The JPA Class for Application Objects
 * <p>
 * com.grplife.qamanager.repository.ApplicationRepository
 * <p>
 * Created by @author eugene
 * <p>
 * Date: 12/1/21 at 12:52 AM
 */
@Repository
public interface ApplicationRepository extends JpaRepository<Application, UUID>{

    List<Application> findByProgramId(String programId);

    @Query("SELECT a FROM Application a INNER JOIN a.people p WHERE p.guid =: personId")
    public List<Person> getByPersonId(@Param("personId") String personId);

}
