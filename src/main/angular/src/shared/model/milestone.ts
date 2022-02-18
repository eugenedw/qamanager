import { Person } from "./person";

export class Milestone {

    constructor(name:string,appId:string){
        this.name = name;
        this.applicationId = appId;
    }

    guid!:string;
    name:string;
    applicationId:string;
    dateCreated!:Date;
    dateLastRun!:Date;
    creator!:Person;

}