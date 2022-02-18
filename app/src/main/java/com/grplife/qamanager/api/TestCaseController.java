package com.grplife.qamanager.api;

import com.grplife.qamanager.model.*;
import com.grplife.qamanager.repository.*;
import com.grplife.qamanager.service.TestCaseService;
import org.apache.coyote.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Project: qamanager
 * Package: com.grplife.qamanager.api
 * <p>
 * User: eugenewilliams
 * Date: Monday February 07, 2022
 * Time: 7:08 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/tc")
public class TestCaseController {

    private static Logger logger = LogManager.getLogger(TestCaseController.class);

    @Autowired
    private TestPlanRepository testPlanRepository;

    @Autowired
    private TestCaseExecutionRepository testCaseExecutionRepository;

    @Autowired
    private TestCaseGroupRepository testCaseGroupRepository;

    @Autowired
    private TestCaseExecutionXrefRepository testCaseExecutionXrefRepository;

    @Autowired
    private TestCaseRepository testCaseRepository;

    @Autowired
    private TestCaseService testCaseService;

    //test cases

    @GetMapping("/{id}")
    private ResponseEntity<TestCase> getTestCase(@PathVariable String id){
        try{
            Optional<TestCase> testCaseData = testCaseRepository.findById(UUID.fromString(id));
            if( testCaseData.isPresent() ){
                TestCase testCase = testCaseData.get();
                return new ResponseEntity<>(testCase, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/application/{appId}")
    public ResponseEntity<List<TestCase>> getTestCasesByApplication(@PathVariable String appId){
        try{
            List<TestCase> testCases = new ArrayList<TestCase>();
            testCases = testCaseRepository.getByApplicationIdAndDeleted(appId,(short)0);
            if( testCases.isEmpty() ){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(testCases, HttpStatus.OK);
        }
        catch(Exception e){
            logger.error("Could not retrieve test cases by app id",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<TestCase> createTestCase(@RequestBody TestCase testCase){
        try {
            if(StringUtils.isEmpty(testCase.getApplicationId())
                    || StringUtils.isEmpty(testCase.getName())){
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
            testCase.setDateCreated(new Date());
            testCaseRepository.save(testCase);
            return new ResponseEntity<>(testCase, HttpStatus.OK);
        }
        catch(Exception e){
            logger.error("Could not save test case",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<TestCase> updateTestCase(@RequestBody TestCase testCase){
        try{
            Optional<TestCase> testCaseData = testCaseRepository.findById(testCase.getGuid());
            if( testCaseData.isPresent() ){
                TestCase _testCase = testCaseData.get();
                _testCase.setTestCaseType(testCase.getTestCaseType());
                _testCase.setAutomated(testCase.getAutomated());
                _testCase.setBusinessRequirement(testCase.getBusinessRequirement());
                _testCase.setTestCasePriority(testCase.getTestCasePriority());
                _testCase.setExpectedResults(testCase.getExpectedResults());
                _testCase.setGroupId(testCase.getGroupId());
                _testCase.setName(testCase.getName());
                _testCase.setSteps(testCase.getSteps());
                _testCase.setPreconditions(testCase.getPreconditions());
                _testCase.setTimeToComplete(testCase.getTimeToComplete());
                testCaseRepository.save(testCase);
                return new ResponseEntity<>(testCase, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            logger.error("Could not save test case",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/execution/{executionId}")
    public ResponseEntity<String> removeTestCaseFromExecution(@PathVariable String id, @PathVariable String executionId){
        try{
            TestCaseExecutionXref tcx = testCaseExecutionXrefRepository.getByExecutionIdAndTestCaseId(UUID.fromString(executionId),UUID.fromString(id));
            if( tcx != null ){
                testCaseExecutionXrefRepository.delete(tcx);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            logger.error("Could not remove test case from execution",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTestCase(@PathVariable String id){
        try{
            Optional<TestCase> testCaseData = testCaseRepository.findById(UUID.fromString(id));
            if( testCaseData.isPresent() ){
                TestCase testCase = testCaseData.get();
                testCase.setDeleted((short)1);
                testCaseRepository.save(testCase);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception e){
            logger.error("Could not delete test case with id",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //test plans

    @GetMapping("/plan/{id}")
    public ResponseEntity<TestPlan> getTestPlanById(@PathVariable String id){
        try{
            TestPlan testPlan = testPlanRepository.getOne(UUID.fromString(id));
            if( testPlan != null ){
                testCaseService.build(testPlan);
                return new ResponseEntity<>(testPlan,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            logger.error("Could not find test plan",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/plan/application/{appId}")
    public ResponseEntity<List<TestPlan>> getTestPlansByApplicationId(@PathVariable String appId){
        try{
            List<TestPlan> testPlans = new ArrayList<TestPlan>();
            testPlans = testPlanRepository.findByApplicationId(appId);
            if( testPlans.isEmpty() ){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(testPlans, HttpStatus.OK);
        }
        catch(Exception e){
            logger.error("Could not retrieve test plans by app id",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/plan")
    public ResponseEntity<TestPlan> createTestPlan(@RequestBody TestPlan testPlan){
        try {
            if(StringUtils.isEmpty(testPlan.getApplicationId())
                    || StringUtils.isEmpty(testPlan.getName())){
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
            testPlan.setDateCreated(new Date());
            testPlanRepository.save(testPlan);
            return new ResponseEntity<>(testPlan, HttpStatus.OK);
        }
        catch(Exception e){
            logger.error("Could not save test plan",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/plan")
    public ResponseEntity<TestPlan> updateTestPlan(@RequestBody TestPlan testPlan){
        try{
            Optional<TestPlan> data = testPlanRepository.findById(testPlan.getGuid());
            if( data.isPresent() ){
                TestPlan _testPlan = data.get();
                _testPlan.setDescription(testPlan.getDescription());
                _testPlan.setName(testPlan.getName());
                return new ResponseEntity<>(_testPlan, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            logger.error("could not update test plan",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/plan/{id}")
    public ResponseEntity<String> deleteTestPlan(@PathVariable String id){
        try{
            Optional<TestPlan> data = testPlanRepository.findById(UUID.fromString(id));
            if( data.isPresent() ){
                TestPlan testPlan = data.get();
                testPlan.setDeleted((short)1);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception e){
            logger.error("Could not delete test plan with id",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //test groups

    @GetMapping("/group/{id}")
    public ResponseEntity<TestCaseGroup> getTestGroupById(@PathVariable String id){
        try{
            TestCaseGroup testCaseGroup = testCaseGroupRepository.getOne(UUID.fromString(id));
            if( testCaseGroup != null ){
                return new ResponseEntity<>(testCaseGroup,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            logger.error("Could not find test group",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/group/application/{appId}")
    public ResponseEntity<List<TestCaseGroup>> getTestCaseGroupsByApplicationId(@PathVariable String appId){
        try{
            List<TestCaseGroup> testCaseGroups = new ArrayList<TestCaseGroup>();
            testCaseGroups = testCaseGroupRepository.findByApplicationIdOrderBySorder(appId);
            if( testCaseGroups.isEmpty() ){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(testCaseGroups, HttpStatus.OK);
        }
        catch(Exception e){
            logger.error("Could not retrieve test case group by app id",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/group")
    public ResponseEntity<TestCaseGroup> createTestCaseGroup(@RequestBody TestCaseGroup testCaseGroup){
        try {
            if(StringUtils.isEmpty(testCaseGroup.getApplicationId())
                    || StringUtils.isEmpty(testCaseGroup.getName())){
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
            testCaseGroupRepository.save(testCaseGroup);
            return new ResponseEntity<>(testCaseGroup, HttpStatus.OK);
        }
        catch(Exception e){
            logger.error("Could not save test case group",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/group")
    public ResponseEntity<TestCaseGroup> updateTestCaseGroup(@RequestBody TestCaseGroup testCaseGroup){
        try{
            Optional<TestCaseGroup> data = testCaseGroupRepository.findById(testCaseGroup.getGuid());
            if( data.isPresent() ){
                TestCaseGroup _testCaseGroup = data.get();
                _testCaseGroup.setApplicationId(testCaseGroup.getApplicationId());
                _testCaseGroup.setName(testCaseGroup.getName());
                _testCaseGroup.setParentId(testCaseGroup.getParentId());
                _testCaseGroup.setSorder(testCaseGroup.getSorder());
                testCaseGroupRepository.save(_testCaseGroup);
                return new ResponseEntity<>(_testCaseGroup, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            logger.error("could not update test case group",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/group/{id}")
    public ResponseEntity<String> deleteTestCaseGroup(@PathVariable String id){
        try{
            Optional<TestCaseGroup> data = testCaseGroupRepository.findById(UUID.fromString(id));
            if( data.isPresent() ){
                TestCaseGroup testCaseGroup = data.get();
                testCaseGroupRepository.delete(testCaseGroup);
                testCaseRepository.updateTestCaseGroupsOnGroupDelete(id);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception e){
            logger.error("Could not delete test group with provided id",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //test case execution

    @GetMapping("/execution/{id}")
    public ResponseEntity<TestCaseExecution> getExecutionById(@PathVariable String id){
        try{
            TestCaseExecution testCaseExecution = testCaseExecutionRepository.getOne(UUID.fromString(id));
            if( testCaseExecution != null ){
                return new ResponseEntity<>(testCaseExecution,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            logger.error("Could not find test case execution with provided id",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/execution/application/{appId}")
    public ResponseEntity<List<TestCaseExecution>> getTestCaseExecutionsByApplicationId(@PathVariable String appId){
        try{
            List<TestCaseExecution> testCaseExecutions = new ArrayList<TestCaseExecution>();
            testCaseExecutions = testCaseExecutionRepository.findByApplicationId(appId);
            if( testCaseExecutions.isEmpty() ){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(testCaseExecutions, HttpStatus.OK);
        }
        catch(Exception e){
            logger.error("Could not retrieve test case group by app id",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/execution/milestone/{milestoneId}")
    public ResponseEntity<List<TestCaseExecution>> getTestCaseExecutionsByMilestoneId(@PathVariable String milestoneId){
        try{
            List<TestCaseExecution> testCaseExecutions = new ArrayList<TestCaseExecution>();
            testCaseExecutions = testCaseExecutionRepository.findByMilestoneId(milestoneId);
            if( testCaseExecutions.isEmpty() ){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(testCaseExecutions, HttpStatus.OK);
        }
        catch(Exception e){
            logger.error("Could not retrieve test case group by app id",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/execution/plan/{appId}")
    public ResponseEntity<List<TestCaseExecution>> getTestCaseExecutionsByPlanId(@PathVariable String planId){
        try{
            List<TestCaseExecution> testCaseExecutions = new ArrayList<TestCaseExecution>();
            testCaseExecutions = testCaseExecutionRepository.getByTestPlanId(planId);
            if( testCaseExecutions.isEmpty() ){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(testCaseExecutions, HttpStatus.OK);
        }
        catch(Exception e){
            logger.error("Could not retrieve test case group by app id",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/execution")
    public ResponseEntity<TestCaseExecution> createTestCaseExecution(@RequestBody TestCaseExecution testCaseExecution){
        try {
            if(StringUtils.isEmpty(testCaseExecution.getApplicationId())
                    || StringUtils.isEmpty(testCaseExecution.getName())){
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
            Set<TestCase> testCases = testCaseExecution.getTestCases();
            testCaseExecution.setDateCreated(new Date());
            testCaseExecution.setTestCases(null);
            testCaseExecutionRepository.save(testCaseExecution);
            testCaseExecutionXrefRepository.deleteExecutionXrefs(testCaseExecution.getGuid());
            if( testCases != null ){
                for( TestCase tc : testCases ){
                    TestCaseExecutionXref tcx = new TestCaseExecutionXref();
                    tcx.setExecutionId(testCaseExecution.getGuid());
                    tcx.setTestCaseId(tc.getGuid());
                    testCaseExecutionXrefRepository.save(tcx);
                }
            }
            return new ResponseEntity<>(testCaseExecution, HttpStatus.OK);
        }
        catch(Exception e){
            logger.error("Could not save test case execution",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/execution")
    public ResponseEntity<TestCaseExecution> updateTestCaseExecution(@RequestBody TestCaseExecution testCaseExecution){
        try{
            Optional<TestCaseExecution> data = testCaseExecutionRepository.findById(testCaseExecution.getGuid());
            if( data.isPresent() ){
                TestCaseExecution _testCaseExecution = data.get();
                _testCaseExecution.setApplicationId(testCaseExecution.getApplicationId());
                _testCaseExecution.setName(testCaseExecution.getName());
                _testCaseExecution.setDateExecuted(testCaseExecution.getDateExecuted());
                _testCaseExecution.setTriggeredBy(testCaseExecution.getTriggeredBy());
                _testCaseExecution.setTestPlanId(testCaseExecution.getTestPlanId());
                testCaseExecutionRepository.save(_testCaseExecution);
                Set<TestCase> testCases = testCaseExecution.getTestCases();
                testCaseExecutionXrefRepository.deleteExecutionXrefs(testCaseExecution.getGuid());
                if( testCases != null ){
                    for( TestCase tc : testCases ){
                        TestCaseExecutionXref tcx = new TestCaseExecutionXref();
                        tcx.setExecutionId(testCaseExecution.getGuid());
                        tcx.setTestCaseId(tc.getGuid());
                        testCaseExecutionXrefRepository.save(tcx);
                    }
                }
                return new ResponseEntity<>(_testCaseExecution, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            logger.error("could not update test case group",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/execution/{id}")
    public ResponseEntity<String> deleteTestCaseExecution(@PathVariable String id){
        try{
            Optional<TestCaseExecution> data = testCaseExecutionRepository.findById(UUID.fromString(id));
            if( data.isPresent() ){
                TestCaseExecution testCaseExecution = data.get();
                testCaseExecution.setDeleted((short)1);
                testCaseExecutionRepository.delete(testCaseExecution);
                testCaseExecutionXrefRepository.deleteExecutionXrefs(UUID.fromString(id));
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception e){
            logger.error("Could not delete test case execution with provided id",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
