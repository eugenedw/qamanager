package com.grplife.qamanager.service;

import com.grplife.qamanager.model.Application;
import com.grplife.qamanager.model.BusinessProgram;
import com.grplife.qamanager.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Business Program Service
 * <p>
 * com.grplife.qamanager.service. BusinessProgramService
 * <p>
 * Created by @author eugene
 * <p>
 * Date: 12/2/21 at 4:09 PM
 */
@Service
public class BusinessProgramService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public void build(BusinessProgram program){
        List<Application> apps = applicationRepository.findByProgramId(program.getGuid().toString());
        program.setApplications(apps);
    }

    public void build(List<BusinessProgram> programList){
        for (BusinessProgram businessProgram : programList) {
            this.build(businessProgram);
        }
    }

}
