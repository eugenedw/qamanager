import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Application } from "../../model/application";

@Injectable({
    providedIn: 'root'
})
export class ApplicationService {

    public applicationList : Array<Application>;

    appDataUrl = "/assets/data/applications.json";
    //appDataUrl = "/api/application"

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

    public getApplicationById(appid:string){
        return this.http.get<Application>(this.appDataUrl.concat("/").concat(appid)).toPromise();
    }

    public saveApplication(application:Application){
        if( application.guid === undefined
                || application.guid.trim() == "" ){
            return this.http.post(this.appDataUrl,application).toPromise();
        }
        else{
            return this.http.put(this.appDataUrl,application).toPromise();
        }
    }

    public uploadApplications(applications:Array<Application>){
        return this.http.put(this.appDataUrl.concat("/list"),applications).toPromise();
    }

}