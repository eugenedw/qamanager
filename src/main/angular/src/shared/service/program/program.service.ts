import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { ProgramArea } from "../../model/program.area";

@Injectable({
    providedIn: 'root'
})
export class ProgramService {

    public programList : Array<ProgramArea>;

    programDataUrl = "/assets/data/programs.json";

    public constructor(public http:HttpClient){
        this.http.get<any>(this.programDataUrl).toPromise().then((resp)=>{
            this.programList = resp;
        });
    }

    public getPrograms() {
        return this.http.get<any>(this.programDataUrl).toPromise();
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