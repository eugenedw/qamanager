package com.grplife.qamanager.service;

import com.grplife.qamanager.model.TestCase;
import com.grplife.qamanager.model.TestCaseExecution;
import com.grplife.qamanager.model.TestCaseExecutionXref;
import com.grplife.qamanager.model.TestPlan;
import com.grplife.qamanager.repository.TestCaseExecutionRepository;
import com.grplife.qamanager.repository.TestCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Project: qamanager
 * Package: com.grplife.qamanager.service
 * <p>
 * User: eugenewilliams
 * Date: Monday February 07, 2022
 * Time: 9:08 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TestCaseService {

    @Autowired
    private TestCaseExecutionRepository testCaseExecutionRepository;

    @Autowired
    private TestCaseRepository testCaseRepository;

    public void build(TestPlan testPlan){
        List<TestCaseExecution> testCaseExecutionList = testCaseExecutionRepository.getByTestPlanId(testPlan.getGuid().toString());
        if( testCaseExecutionList != null ){
            for( TestCaseExecution exec : testCaseExecutionList ){
                List<TestCase> testCases = new ArrayList<TestCase>();
                testCases = testCaseRepository.getTestCasesByExecution(exec.getGuid().toString());
                exec.setTestCases(new HashSet<TestCase>(testCases));
            }
        }
        testPlan.setExecutions(testCaseExecutionList);
    }

}
