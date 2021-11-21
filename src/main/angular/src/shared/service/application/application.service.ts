import { Injectable } from "@angular/core";
import { Application } from "../../model/application";
import { HttpClient } from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class ApplicationService {

    public applicationList : Array<Application>;

    appDataUrl = "/assets/data/applications.json";

    public constructor(public http:HttpClient){
        this.applicationList = [];
    }

    public getApplicationList(){
        try{
            return this.http.get<any>(this.appDataUrl).toPromise()
        }
        catch(e){
            alert(e)
        }
        return null;
    }

}