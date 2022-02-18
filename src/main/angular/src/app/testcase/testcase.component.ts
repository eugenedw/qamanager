import { Location } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Milestone } from 'src/shared/model/milestone';
import { TestCaseGroup } from 'src/shared/model/tcgroup';
import { TestCase } from 'src/shared/model/testcase';
import { PreviousRouteService } from 'src/shared/service/previousroute/previousroute.service';
import { TestCaseService } from 'src/shared/service/testcase/testcase.service';
import { ModalConfirmComponent } from '../modal-confirm/modal-confirm.component';

@Component({
  selector: 'app-testcase',
  templateUrl: './testcase.component.html',
  styleUrls: ['./testcase.component.scss']
})
export class TestcaseComponent implements OnInit {

  public currentTestCase : TestCase = new TestCase();
  public testcasegroups : Array<TestCaseGroup> = [];
  public saving : boolean = false;
  public previousUrl !: string;
  public milestoneId : string = "";

  @ViewChild("modalConfirm") modalConfirm !: ModalConfirmComponent;

  public deleteConfirmModalConfig : any = {
    "title"   : "Delete Test Case",
    "message" : "Continuing will remove this test case and cannot be restored. Is this what you'd like to do?"
  }

  constructor(public testCaseService:TestCaseService, public previousRouteService:PreviousRouteService, public route:ActivatedRoute, public router:Router, public _location : Location) { 
    this.milestoneId = this.route.snapshot.queryParams["milestone"];
    const id : any = this.route.snapshot.paramMap.get('tcid');
    setTimeout(()=>{
      this.previousUrl = this.previousRouteService.getPreviousUrl();
    },1000);
    this.loadtestcase(id);
  }

  ngOnInit(): void {
  }

  back(){
    this._location.back();
  }

  _estimateKeyRestrict(event: any) {
    const pattern1 = /[0-9]+[hmd]$/gm;
    const pattern2 = /[0-9]+$/gm;
    let inputChar = event.key;
    let newval = event.target.value + inputChar;
    if (!pattern1.test(newval) && !pattern2.test(newval)) {
      // invalid character, prevent input
      event.preventDefault();
    }
  }

  loadtestcase(id:any){
    if( id.indexOf("new") < 0 ){
      this.testCaseService.getTestCase(id).subscribe((resp)=>{
        this.currentTestCase = resp;
        this.loadtestcasegroups();
      })
    }
    else{
      this.currentTestCase.applicationId = id.split(":")[1];
    }
  }

  loadtestcasegroups(){
    this.testCaseService.getTestCaseGroups(this.currentTestCase.applicationId).subscribe(
      (resp) => {
        this.testcasegroups = this.testCaseService.buildgroupchildren(resp);
      }
    )
  }

  saveTestCase(){
    if( this.currentTestCase.name == undefined || this.currentTestCase.name == null || this.currentTestCase.name.trim() == "" ){
      alert("Name for test case is required");
      return;
    }
    this.saving = true;
    this.testCaseService.saveTestCase(this.currentTestCase).subscribe(
      (resp:TestCase) => { 
        setTimeout(()=>{
          this.saving = false;
          this.router.navigateByUrl("/test-case/".concat(resp.guid));
        },1000);
      }
    )
  }

  deleteTestCase(confirm:boolean){
    if( confirm ){
      this.testCaseService.deleteTestCase(this.currentTestCase.guid).subscribe(
        (resp) =>{
          this.previousUrl = this.previousUrl.split("?")[0];
          if( this.milestoneId != null && this.milestoneId !== undefined ){
            this.previousUrl = "/milestone/".concat(this.milestoneId).concat("?tab=app-tab-cases")
          }
          this.router.navigateByUrl(this.previousUrl);
        }
      )
    }
    else{
      this.modalConfirm.show();
    }
  }

  cancelDelete(){
    //do nothing
  }

}
