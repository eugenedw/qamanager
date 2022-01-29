import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class UtilityService {

    appDataUrl = "/assets/data/applications.json";

    public constructor(public http:HttpClient){
    }

    public removeFromList(arr : Array<any>, ind : number){
        arr.splice(ind,1);
    }
    
    public arrayTrackBy(index:any,item:any) {
        return index;
    }

    public static uuid(){
        return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
            var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
            return v.toString(16);
        });
    }

}