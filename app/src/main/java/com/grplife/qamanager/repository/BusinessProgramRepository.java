package com.grplife.qamanager.repository;

import com.grplife.qamanager.model.BusinessProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * The JPA Persistence Class for Business Program
 * <p>
 * com.grplife.qamanager.repository.BusinessProgramRepository
 * <p>
 * Created by @author eugene
 * <p>
 * Date: 12/1/21 at 10:19 AM
 */
@Repository
public interface BusinessProgramRepository extends JpaRepository<BusinessProgram, UUID> {
}
