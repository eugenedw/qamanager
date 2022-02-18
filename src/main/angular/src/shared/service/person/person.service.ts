import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Person } from "../../model/person";

@Injectable({
    providedIn: 'root'
})
export class PersonService {

  private dataUrl = "/api/person";

  constructor(public http:HttpClient){}

  public get(guid:string){
      
      let promise = new Promise((resolve,reject)=>{
          let url = "/assets/data/sample/team/list.json";
          //let url = this.dataUrl.concat("/").concat(guid);
          this.http.get<Array<Person>>(url).toPromise().then((resp)=>{
              let person = resp!.find(p => p.guid === guid);
              resolve(person);
          });
      });
      return promise;

  }
    
  public getPeople(page: number, size: number, sort? :string, order? :string) {
      let promise = new Promise((resolve,reject)=>{
          let url = "/assets/data/sample/team/list.json";
          //let url = this.dataUrl.concat("/list/"+page+"/"+size);
          let _params = {
            "sort" : sort?sort:"firstname",
            "order" : order?order:"asc"
          };
          this.http.get<Array<Person>>(url,{
            params : _params
          }).toPromise().then((resp)=>{
              resolve(resp);
          });
      });
      return promise;
  }

  public getByApplication(appId: string){
      let promise = new Promise((resolve,reject)=>{
          this.http.get<Array<Person>>(this.dataUrl.concat("/application/".concat(appId))).toPromise().then((resp)=>{
              resolve(resp);
          });
      });
      return promise;
  }

  public savePerson(person:Person){
    if( person.guid === undefined || person.guid == "" ){
      return this.http.post<Person>(this.dataUrl,person);
    }
    else{
      return this.http.put<Person>(this.dataUrl,person);
    }
  }

  printroles(roles:any){
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