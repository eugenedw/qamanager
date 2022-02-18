import { TestCase } from "./testcase";

export class TestRun {

    constructor(applicationId:string){
        this.applicationId = applicationId;
    }

    guid!:string;
    applicationId!:string;
    milestoneId!:string;
    dateExecuted!:Date;
    dateCreated!:Date;
    triggeredBy!:string;
    testPlanId!:string;
    name!:string;
    deleted!:number;
    description!:string;
    testCaseSelect!:string;
    testCases!:Array<TestCase>;
    executionMethod!:string;

}