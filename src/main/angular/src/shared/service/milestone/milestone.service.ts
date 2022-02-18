import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { map } from "rxjs/operators";
import { Milestone } from "src/shared/model/milestone";

@Injectable({
    providedIn: 'root'
})
export class MilestoneService {

    public milestoneList !: Array<Milestone>;
    
    milestoneDataUrl = "/api/milestone"

    public constructor(public http:HttpClient){
        this.milestoneList = [];
    }

    public getProject(project_id:string){
        return this.http.get<any>(this.milestoneDataUrl.concat("/").concat(project_id)).pipe(
            map((resp) => { return resp as Milestone })
        )
    }

    public getTestProjectList(){
        try{
            return this.http.get<any>(this.milestoneDataUrl.concat("/list")).toPromise()
        }
        catch(e){
            alert(e);
        }
        return null;
    }

    public getByAppId(appid:string){
        return this.http.get<Milestone>(this.milestoneDataUrl.concat("/application/").concat(appid)).toPromise();
    }

    public saveProject(project:Milestone){
        if( project.guid === undefined
                || project.guid.trim() == "" ){
            return this.http.post(this.milestoneDataUrl,project).pipe(
                map(resp => {return resp as Milestone})
            );
        }
        else{
            return this.http.put(this.milestoneDataUrl,project).pipe(
                map(resp => {return resp as Milestone})
            );
        }
    }

}