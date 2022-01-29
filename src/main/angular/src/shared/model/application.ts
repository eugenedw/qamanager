import { ProgramArea } from "./program.area";

export class Application {
    guid!:string;
    name!: string;
    programId!:string;
    program!:ProgramArea;
    appId!:string;
    artifact!:string;
    type!:number;
    automated!:string;
    productOwner!:string;
    projectManager!:string;
    comments!:string;
}