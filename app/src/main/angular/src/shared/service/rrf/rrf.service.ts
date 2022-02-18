import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { ProgramArea } from "../../model/program.area";
import { ReleaseRRF } from "../../model/releaserrf";
import { RRFStatus } from "../../model/rrfstatus";

@Injectable({
    providedIn: 'root'
})
export class RRFService {

    rrfDataUrl = "/assets/data/sample/rrf/list.json";

    public constructor(public http:HttpClient){
    }

    public list(page:number, size:number){
        let deferred = new Promise((resolve,reject)=>{
            this.http.get<any>(this.rrfDataUrl).toPromise().then((resp:Array<ReleaseRRF>)=>{
                resolve(resp);
            });
        });
        return deferred;
    }

    public get(id:string){
        let deferred = new Promise((resolve,reject)=>{
            this.list(1,1).then((resp:any)=>{
                resolve(resp.find((rrf: { guid: string; }) => rrf.guid === id));
            })
        });
        return deferred;
    }

    public getByApp(app_id:string){ 
        let deferred = new Promise((r,R)=>{
            this.list(1,1).then((resp:any)=>{
                r(resp.filter((rrf: { app_id: string; }) => rrf.app_id === app_id));
            });
        });
        return deferred;
    }
    
    public statusText(idx:any){
        return RRFStatus[idx];
    }

}