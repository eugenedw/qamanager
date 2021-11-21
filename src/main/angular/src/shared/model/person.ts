import { PersonRole } from "./personrole";

export class Person {

    constructor(firstname:string,lastname:string,email:string){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    guid:string;
    firstname:string;
    lastname:string;
    email:string;
    phone:string;
    roles:any = {};
    applications:Array<string> = [];
    dt_created:Date;
    comments:string;

}