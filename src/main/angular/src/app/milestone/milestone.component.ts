import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NotFoundError } from 'rxjs';
import { Milestone } from 'src/shared/model/milestone';
import { TestCaseGroup } from 'src/shared/model/tcgroup';
import { TestCase } from 'src/shared/model/testcase';
import { TestRun } from 'src/shared/model/testrun';
import { ApplicationService } from 'src/shared/service/application/application.service';
import { MilestoneService } from 'src/shared/service/milestone/milestone.service';
import { TestCaseService } from 'src/shared/service/testcase/testcase.service';

@Component({
  selector: 'app-milestone',
  templateUrl: './milestone.component.html',
  styleUrls: ['./milestone.component.scss']
})
export class MilestoneComponent implements OnInit {

  currentApplication : any = {};
  currentMilestone : any = null;
  current_tab : string = "app-tab-plans";
  testcasegroups : any = []
  testcasegroups_all : any = [];
  
  currentGroup : any = new TestCaseGroup();
  _currentGroup : any = "";
  closeResult : any = null;
  groupsaving : boolean = false;

  formerrors : any = {
    "tcgroup" : ""
  }

  testRunsTable : any = {
    "emptyMessage" : "No Test Runs Created",
    "header" : [
      {
        "width" : "col-3",
        "label" : "Name"
      },
      {
        "width" : "col-3",
        "label" : "Test Cases"
      },
      {
        "width" : "col-3",
        "label" : "Date Executed"
      },
      {
        "width" : "col-3",
        "label" : "Results"
      }
    ]
  };

  testPlansTable : any = {
    "emptyMessage" : "No Test Plans Created",
    "header" : [
      {
        "width" : "col-2",
        "label" : "Name"
      },
      {
        "width" : "col-4",
        "label" : "Description"
      },
      {
        "width" : "col-2",
        "label" : "Test Runs"
      },
      {
        "width" : "col-2",
        "label" : "Test Cases"
      },
      {
        "width" : "col-2",
        "label" : "Estimate"
      }
    ]
  };

  testCasesTable : any = {
    "emptyMessage" : "No Test Cases Created",
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

  constructor(public appsvc:ApplicationService, public testCaseService:TestCaseService, public projectsvc:MilestoneService, public route:ActivatedRoute, public router:Router, public modalService:NgbModal) { 
    
    const id : any = this.route.snapshot.paramMap.get('milestoneid');
    const app_id : any = this.route.snapshot.queryParamMap.get('app_id');
    const _current_tab = this.route.snapshot.queryParamMap.get('tab');

    if( _current_tab ){
      this.current_tab = _current_tab;
    }

    this.loadApplication(app_id);

    if( id.toLowerCase() == "new" ){
      this.currentMilestone = new Milestone("",app_id);
    }
    else{
      this.projectsvc.getProject(id).subscribe(
        resp => {
          this.currentMilestone = resp;
          this.loadApplication(resp.applicationId);
        }
      )
    }

  }

  public loadApplication(applicationId:string){
    if( applicationId !== undefined && applicationId !== null ){
      this.appsvc.getApplicationById(applicationId).then((app)=>{
        if( app ){
          this.currentApplication = app;
          this.gettestcasegroups(app.guid);
          this.gettestcases(app.guid);
          this.gettestcaseruns();
        }
      });
    }
  }

  ngOnInit(): void {
  }

  gettestcaseruns(){
    this.testCaseService.getTestCaseRunsByMilestone(this.currentMilestone.guid).subscribe(
      (resp:Array<TestRun>) => {
        if( resp ){
          let trrows = [];
          for( let tcr of resp ){
            let row = {
              "link" : "/test-run/" + tcr.guid,
              "params" : {"milestone":this.currentMilestone.guid},
              "columns" : [{"body":tcr.name},{"body":tcr.testCases.length},{"body":tcr.dateExecuted},{"body":"-"}]
            };
            trrows.push(row);
          }
          this.testRunsTable["rows"] = trrows;
        }
      }
    )
  }

  gettestcases(applicationid:string){
    this.testCaseService.getTestCases(applicationid).subscribe(
      (resp:Array<TestCase>) => {
        if( resp ){
          let tcrows = [];
          for( let tc of resp ){
            let row = {
              "link" : "/test-case/" + tc.guid,
              "params" : {"milestone":this.currentMilestone.guid},
              "columns" : [{"body":tc.testCaseId},{"body":tc.name},{"body":tc.timeToComplete},{"body":tc.businessRequirement}]
            };
            tcrows.push(row);
          }
          this.testCasesTable["rows"] = tcrows;
        }
      }
    )
  }

  gettestcasegroups(applicationid:string){
    this.testCaseService.getTestCaseGroups(applicationid).subscribe((tcgroups : Array<TestCaseGroup>)=>{
      this.testcasegroups_all = tcgroups;
      this.testcasegroups = this.testCaseService.buildgroupchildren(tcgroups);
    });
  }

  savemilestone(){
    this.projectsvc.saveProject(this.currentMilestone).subscribe(
      (milestone:any)=>{
        //app : Application
        this.router.navigateByUrl("/milestone/".concat(milestone.guid))
    });
  }

  calculateTestCaseCount(testrun:any){

  }

  saveGroup(){
    this.formerrors["tcgroup"] = "";
    this.currentGroup.applicationId = this.currentApplication.guid;
    if(this.currentGroup.name === undefined || this.currentGroup.name.trim() == "" ){
      this.formerrors["tcgroup"] = "The name is required";
    }
    else{
      this.groupsaving = true;
      this.testCaseService.saveTestCaseGroup(this.currentGroup).subscribe((tcg:TestCaseGroup)=>{
        this.gettestcasegroups(this.currentApplication.guid);
        setTimeout(()=>{
          this.editgroup(undefined,tcg);
          this.groupsaving = false;
        },500);
      });
    }
  }

  editgroup(content:any,_group:any) {
    this.currentGroup = _group;
    if( _group.guid ){
      for( let k in this.testcasegroups_all ){
        if( this.testcasegroups_all[k].guid == _group.guid ){
          this.currentGroup = this.testcasegroups_all[k];
        }
      }
    }
    if( content ){
      this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
        this.closeResult = `Closed with: ${result}`;
      }, (reason) => {
        this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      });
    }
  }

  printMilestone(){
    return JSON.stringify(this.currentMilestone,null,2);
  }


  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }


}
