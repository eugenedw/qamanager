package com.grplife.qamanager.service;

import com.grplife.qamanager.model.Application;
import com.grplife.qamanager.model.BusinessProgram;
import com.grplife.qamanager.repository.BusinessProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The Application Service
 * <p>
 * com.grplife.qamanager.ApplicationService
 * <p>
 * Created by @author eugene
 * <p>
 * Date: 12/2/21 at 8:41 AM
 */
@Service
public class ApplicationService {

    @Autowired
    private BusinessProgramRepository businessProgramRepository;

    public void build(Application application){
        if( !StringUtils.isEmpty(application.getProgramId()) ){
            try{
                Optional<BusinessProgram> programData = businessProgramRepository.findById(UUID.fromString(application.getProgramId()));
                if( programData.isPresent() ){
                    application.setProgram(programData.get());
                }
                else{
                    application.setProgramId(null);
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void build(List<Application> apps){
        apps.forEach(this::build);
    }

}
