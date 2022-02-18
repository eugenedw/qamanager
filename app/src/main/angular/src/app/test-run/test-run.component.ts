import { Location } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Milestone } from 'src/shared/model/milestone';
import { TestCaseGroup } from 'src/shared/model/tcgroup';
import { TestCase } from 'src/shared/model/testcase';
import { TestRun } from 'src/shared/model/testrun';
import { PreviousRouteService } from 'src/shared/service/previousroute/previousroute.service';
import { TestCaseService } from 'src/shared/service/testcase/testcase.service';
import { ModalConfirmComponent } from '../modal-confirm/modal-confirm.component';

@Component({
  selector: 'app-test-run',
  templateUrl: './test-run.component.html',
  styleUrls: ['./test-run.component.scss']
})
export class TestRunComponent implements OnInit {

  public currentTestRun:TestRun = new TestRun("");
  public milestoneId:string;
  public applicationId:string;
  public saving:boolean = false;
  public previousUrl !:string;
  public closeResult : any;
  public testcases !: Array<TestCase>;
  public testcasegroups !: Array<TestCaseGroup>;
  public testCaseGroupMap : any = {};
  public selectedCases !: Array<TestCase>;
  public selectedGroup !: string;
  public testGroupSelect : any = {}
  public testCaseSelect : any = {}

  @ViewChild("modalConfirm") modalConfirm !: ModalConfirmComponent;

  public deleteConfirmModalConfig : any = {
    "title"   : "Delete Test Run",
    "message" : "Continuing will remove this test run and cannot be restored. Is this what you'd like to do?"
  }

  constructor(public testCaseService:TestCaseService, public route:ActivatedRoute, public modalService:NgbModal, public previousRouteService:PreviousRouteService, public router:Router, public _location:Location) { 

    this.milestoneId = this.route.snapshot.queryParams["milestone"];
    this.applicationId = this.route.snapshot.queryParams["application"];
    const id : any = this.route.snapshot.paramMap.get('trid');
    setTimeout(()=>{
      this.previousUrl = this.previousRouteService.getPreviousUrl();
    },1000);
    this.loadtestrun(id);
    this.gettestcases();

  }

  ngOnInit(): void {
  }

  back(){
    this._location.back();
  }

  loadtestrun(id:any){
    if( id.indexOf("new") < 0 ){
      this.testCaseService.getTestCaseRun(id).subscribe((resp)=>{
        this.currentTestRun = resp;
      })
    }
    else{
      this.currentTestRun.applicationId = this.applicationId;
      this.currentTestRun.milestoneId = this.milestoneId;
    }
  }

  deleteTestRun(confirm:boolean){
    if( confirm ){
      this.testCaseService.deleteTestCase(this.currentTestRun.guid).subscribe(
        (resp) =>{
          this.previousUrl = this.previousUrl.split("?")[0];
          if( this.milestoneId != null && this.milestoneId !== undefined ){
            this.previousUrl = "/milestone/".concat(this.milestoneId).concat("?tab=app-tab-runs")
          }
          this.router.navigateByUrl(this.previousUrl);
        }
      )
    }
    else{
      this.modalConfirm.show();
    }
  }

  saveTestRun(){

    if( this.currentTestRun.name == undefined || this.currentTestRun.name == null || this.currentTestRun.name.trim() == "" ){
      alert("Name for test run is required");
      return;
    }
    let selectedCases = this.getSelectedTestCases();
    this.currentTestRun.testCases = [];
    for( let idx in selectedCases ){
      let tc = new TestCase();
      tc.guid = selectedCases[idx];
      this.currentTestRun.testCases.push(tc);
    }
    this.saving = true;
    this.testCaseService.saveTestRun(this.currentTestRun).subscribe(
      (resp:TestRun) => { 
        setTimeout(()=>{
          this.saving = false;
          this.router.navigateByUrl("/test-run/".concat(resp.guid));
        },1000);
      }
    )
    
  }

  selectTestCases(content:any){
      this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title', size: 'lg'}).result.then((result) => {
        this.closeResult = `Closed with: ${result}`;
      }, (reason) => {
        this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      });
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

  }

  showGroupCases(groupId:string){
    setTimeout(()=>{
      if( this.testGroupSelect[groupId] ){
        this.selectedGroup = groupId;
      }
    },200);
  }

  cancelDelete(){
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

  public addTestCase(guid:string){
    
  }

  public getSelectedTestCases(){
    let tcarr : Array<string> = [];
     for( let idx in this.testCaseSelect ){
        if( this.testCaseSelect[idx] ){
          tcarr.push(idx);
        }
    }
    return tcarr;
  }


}
