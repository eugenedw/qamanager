import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Person } from "../../model/person";

@Injectable({
    providedIn: 'root'
})
export class PersonService {

  constructor(public http:HttpClient){}

  public get(guid:string){
      
      let promise = new Promise((resolve,reject)=>{
          let url = "/assets/data/sample/team/list.json";
          this.http.get<Array<Person>>(url).toPromise().then((resp)=>{
              let person = resp.find(p => p.guid === guid);
              resolve(person);
          });
      });
      return promise;
  }
    
  public getPeople(page: number, size: number) {
      let promise = new Promise((resolve,reject)=>{
          let url = "/assets/data/sample/team/list.json";
          this.http.get<Array<Person>>(url).toPromise().then((resp)=>{
              resolve(resp);
          });
      });
      return promise;
  }

  printroles(roles){
    let rolarr = [];
    for( let k in roles ){
      if( roles[k] ){
        rolarr.push(k);
      }
    }
    if( rolarr.length == 0 ){
      return "No Roles";
    }
    else{
      const rolestr = rolarr.join(", ").replace("_"," ").toLowerCase();
      const words = rolestr.split(" ");
      for (let i = 0; i < words.length; i++) {
        words[i] = words[i][0].toUpperCase() + words[i].substr(1);
      }
      return words.join(" ");
    }
  }

}