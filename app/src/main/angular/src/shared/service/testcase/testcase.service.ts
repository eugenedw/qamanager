import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { map } from "rxjs/operators";
import { TestCaseGroup } from "src/shared/model/tcgroup";
import { TestCase } from "src/shared/model/testcase";
import { TestRun } from "src/shared/model/testrun";

@Injectable({
    providedIn: 'root'
})
export class TestCaseService {

    public constructor(public http:HttpClient){
    }

    /* test cases */

    public getTestCases(applicationid:string){
        return this.http.get<any>("/api/tc/application/".concat(applicationid)).pipe(
            map((resp) => { return resp as Array<TestCase> })
        )
    }
    
    public getTestCase(tcid:string){
        return this.http.get<any>("/api/tc/".concat(tcid)).pipe(
            map((resp) => { return resp as TestCase })
        )
    }

    public deleteTestCase(tcid:string){
        return this.http.delete<any>("api/tc/".concat(tcid)).pipe(
            map((resp) => {return resp})
        );
    }

    public saveTestCase(tc:TestCase){
        if( tc.guid === undefined
                || tc.guid.trim() == "" ){
            return this.http.post("/api/tc",tc).pipe(
                map(resp => {return resp as TestCase})
            );
        }
        else{
            return this.http.put("/api/tc",tc).pipe(
                map(resp => {return resp as TestCase})
            );
        }
    }

    /* test case groups */

    public saveTestCaseGroup(group:TestCaseGroup){
        if( group.guid === undefined
                || group.guid.trim() == "" ){
            return this.http.post("/api/tc/group",group).pipe(
                map(resp => {return resp as TestCaseGroup})
            );
        }
        else{
            return this.http.put("/api/tc/group",group).pipe(
                map(resp => {return resp as TestCaseGroup})
            );
        }
    }

    public getTestCaseGroups(applicationid:string){
        return this.http.get<any>("/api/tc/group/application/".concat(applicationid)).pipe(
            map((resp) => { return resp as Array<TestCaseGroup> })
        )
    }

    /* test runs */
    
    public getTestCaseRun(tcrid:string){
        return this.http.get<any>("/api/tc/execution/".concat(tcrid)).pipe(
            map((resp) => { return resp as TestRun })
        )
    }

    public getTestCaseRunsByMilestone(milestoneId:string){
        return this.http.get<any>("/api/tc/execution/milestone/".concat(milestoneId)).pipe(
            map((resp) => { return resp as Array<TestRun>})
        )
    }

    public saveTestRun(tr:TestRun){
        if( tr.guid === undefined
                || tr.guid.trim() == "" ){
            return this.http.post("/api/tc/execution",tr).pipe(
                map(resp => {return resp as TestRun})
            );
        }
        else{
            return this.http.put("/api/tc/execution",tr).pipe(
                map(resp => {return resp as TestRun})
            );
        }
    }

    public deleteTestRun(trid:string){
        return this.http.delete<any>("api/tc/execution/".concat(trid)).pipe(
            map((resp) => {return resp})
        );
    }


    buildgroupchildren(tcgroups:Array<TestCaseGroup>){
      let orphans = [];
      let parents = [];
      for( let i in tcgroups ){
        if( tcgroups[i].parentId !== null && tcgroups[i].parentId != '' ){
          orphans.push(tcgroups[i]);
        }
        else{
          parents.push(tcgroups[i]);
        }
      }
      return this.buildgroups(parents,orphans);
    }

    buildgroups(grouplist:any,orphans:any){
        for( let i in grouplist ){
        let g = grouplist[i];
        if( g.children === undefined ){
            g.children = [];
        }
        for( let k in orphans ){
            if( orphans[k].parentId == g.guid ){
            g.children.push(orphans[k]);
            }
        }
        this.buildgroups(g.children,orphans);
        }
        return grouplist;
    }

}