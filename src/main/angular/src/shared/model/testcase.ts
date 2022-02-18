export class TestCase {

    constructor(){
        this.testCaseType = "";
        this.testCasePriority = "TRIVIAL";
        this.automated = 0;
    }

    guid!:string;
    testCaseId!:string;
    applicationId!:string;
    businessRequirement!:string;
    name!:string;
    testCaseType!:string;
    automated!:number;
    createdBy!:string;
    dateCreated!:Date;
    preconditions!:string;
    steps!:string;
    expectedResults!:string;
    groupId!:string;
    deleted!:number;
    testCasePriority!:string;
    timeToComplete!:number;

}