import { ViewportScroller } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { TestCaseGroup } from 'src/shared/model/tcgroup';
import { TestCase } from 'src/shared/model/testcase';
import { TestCaseService } from 'src/shared/service/testcase/testcase.service';

@Component({
  selector: 'app-test-case-list',
  templateUrl: './test-case-list.component.html',
  styleUrls: ['./test-case-list.component.scss']
})
export class TestCaseListComponent implements OnInit {

  @Input("applicationId") applicationId !: string;
  @Input("milestoneId") milestoneId !: string;

  public testcasegroups : Array<TestCaseGroup> = [];
  public testCaseGroupMap : any = {}
  public testcases : Array<TestCase> = [];

  public tables : Array<any> = [];

  constructor(public testCaseService:TestCaseService, private viewportScroller:ViewportScroller) { }

  ngOnInit(): void {
    this.gettestcases();
  }

  gettestcases(){
    this.testCaseService.getTestCases(this.applicationId).subscribe(
      (resp:Array<TestCase>) => {
        if( resp ){
          this.testcases = resp;
          this.loadtestcasegroups();
        }
      }
    )
  }


  loadtestcasegroups(){
    this.testCaseService.getTestCaseGroups(this.applicationId).subscribe(
      (resp) => {
        this.testcasegroups = this.testCaseService.buildgroupchildren(resp);
        for( let idx in resp ){
          this.testCaseGroupMap[resp[idx].guid] = {"name":resp[idx].name,"test_cases":[]};
        }
        this.testCaseGroupMap["uncategorized"] = {"name":"Uncategorized","test_cases":[]};
        this.loadTestCaseGroupMap();
      }
    )
  }

  loadTestCaseGroupMap(){

    for( let key in this.testCaseGroupMap ){
      for( let tci in this.testcases ){
        if( this.testcases[tci].groupId == key ){
          this.testCaseGroupMap[key]["test_cases"].push(this.testcases[tci]);
        }
      }
    }

    for( let tci in this.testcases ){
      if( this.testcases[tci].groupId == null || this.testcases[tci].groupId === undefined || this.testcases[tci].groupId.trim() == "" ){
        this.testCaseGroupMap["uncategorized"]["test_cases"].push(this.testcases[tci]);
      }
    }

    let utcg = new TestCaseGroup();
    utcg.name = "Uncategorized";
    utcg.guid = "uncategorized";
    this.createGroupTable(utcg,0);

    this.buildTestCaseTables(this.testcasegroups,0);

  }

  buildTestCaseTables(groups:Array<TestCaseGroup>, ilevel:number){
    for( let idx in groups ){
      this.createGroupTable(groups[idx],ilevel);
      if( groups[idx].children ){
        this.buildTestCaseTables(groups[idx].children, ilevel+1);
      }
    }
  }

  createGroupTable(tcgroup:TestCaseGroup, ilevel:number){

    let tcrows = [];
    let groupTable : any = {
      "table_id" : tcgroup.guid,
      "emptyMessage" : "No Test Cases Created",
      "title" : tcgroup.name,
      "table_indent" : ilevel,
      "header" : [
        {
          "width" : "col-1",
          "label" : "ID"
        },
        {
          "width" : "col-5",
          "label" : "Name"
        },
        {
          "width" : "col-2",
          "label" : "Estimate"
        },
        {
          "width" : "col-2",
          "label" : "References"
        }
      ]
    };
    let _tcs = this.testCaseGroupMap[tcgroup.guid].test_cases;
    for( let tcx in _tcs ){
      let tc = _tcs[tcx];
      let row = {
        "link" : "/test-case/" + tc.guid,
        "params" : {"milestone": this.milestoneId},
        "columns" : [{"body":tc.testCaseId},{"body":tc.name},{"body":tc.timeToComplete},{"body":tc.businessRequirement}]
      };
      tcrows.push(row);
    }
    groupTable["rows"] = tcrows;
    this.tables.push(groupTable);

  }

  groupscroll(name:string){
    this.viewportScroller.scrollToAnchor(name);
  }

}
