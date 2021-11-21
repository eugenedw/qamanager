import { Person } from "./person";
import { ActivityLogType } from "./activitylogtype";
import { UtilityService } from "../service/util.service";

export class ActivityLog {

    constructor(message:string,author:Person,type:ActivityLogType){
        this.message = message;
        this.author = author;
        this.type = type;
        this.dt_created = new Date();
        this.guid = UtilityService.uuid();
    }

    guid:string;
    message:string;
    dt_created:Date;
    author:Person;
    type:ActivityLogType;

}