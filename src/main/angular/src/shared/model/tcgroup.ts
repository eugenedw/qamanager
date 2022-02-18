export class TestCaseGroup {

    constructor(){
        this.children = [];
    }

    guid!:string;
    applicationId!:string;
    parentId!:string;
    name!:string;
    children!:Array<TestCaseGroup>;

}