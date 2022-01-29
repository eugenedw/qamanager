import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { ProgramArea } from "../../model/program.area";

@Injectable({
    providedIn: 'root'
})
export class ProgramService {

    public programList !: Array<ProgramArea>;

    programDataUrl = "/api/program";

    public constructor(public http:HttpClient){
    }

    public getPrograms() {
        return this.http.get<any>(this.programDataUrl.concat("/list")).toPromise();
    }

    public saveProgram(_program:ProgramArea){
        if( _program.guid === undefined 
                || _program.guid.trim() == "" ){
            return this.http.post(this.programDataUrl,_program).toPromise();
        }
        else{
            return this.http.put(this.programDataUrl,_program).toPromise();
        }
    }

    public getProgramById(id:string){
        if( this.programList.length > 0 ){
            for( let ind in this.programList ){
                if( this.programList[ind]["guid"] == id ){
                    return this.programList[ind];
                }
            }
        }
        return null;
    }

}