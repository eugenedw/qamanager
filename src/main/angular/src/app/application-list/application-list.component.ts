import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Application } from '../../shared/model/application';
import { ProgramArea } from '../../shared/model/program.area';
import { ApplicationService } from '../../shared/service/application/application.service';
import { ProgramService } from '../../shared/service/program/program.service';

@Component({
  selector: 'app-application-list',
  templateUrl: './application-list.component.html',
  styleUrls: ['./application-list.component.scss']
})
export class ApplicationListComponent implements OnInit {

  public applications:Array<Application> = [];
  public programs: Array<ProgramArea> = [];
  public programMap:any = {};
  public selectedFile:any;
  public applicationUpload = {
    "ready" : false,
    "applications" : new Array<any>(),
    "editselect" : { "name" : "", "program" : new ProgramArea() },
    "editselect_str" : "",
    "response" : undefined,
    "error" : undefined,
    "status" : "init"
  }
  public filter : any = {
    "app_name" : "",
    "program_name" : ""
  }

  constructor(public appsvc:ApplicationService, public programsvc:ProgramService, public modalService:NgbModal) { }

  ngOnInit() {
    this.appsvc.getApplicationList()!.then((resp:Array<Application>)=>{
      if( resp == null ){
        resp = [];
      }
      this.applications = resp;
    });
    this.programsvc.getPrograms().then((resp:Array<ProgramArea>)=>{
      this.programs = resp;
      for(let p in this.programs){
        this.programMap[this.programs[p].shortName] = this.programs[p]; 
      }
    });
  }

  printApplications(){
    return JSON.stringify(this.applications,null,2);
  }

  onFileChanged(event:any) {
    this.applicationUpload.ready = false;
    this.applicationUpload.applications = [];
    this.selectedFile = event.target.files[0];
    if( this.selectedFile.type != "application/json" ){
      alert("The file must be in JSON format");
      return false;
    }
    const fileReader = new FileReader();
    fileReader.readAsText(this.selectedFile, "UTF-8");
    fileReader.onload = () => {
      this.processApplicationUpload(fileReader.result!.toString());
    }
    fileReader.onerror = (error) => {
      console.log(error);
    }
    return true;
  }

  processApplicationUpload(appdata:any){
    let _applicationlist = JSON.parse(appdata);
    let applicationlist = new Array<Application>();
    for( let a in _applicationlist ){
      let app = _applicationlist[a] as Application;
      if( app.program && app.program.name ){
        let pset = false;
        for( let [k,v] of Object.entries(this.programMap) ){
          if( app.program.name.indexOf(k) > -1 ){
            app.programId = (v as ProgramArea).guid;
            app.program = v as ProgramArea;
            pset = true;
          }
        }
        if( !pset ){
          app.program != undefined;
          app.programId != undefined;
        }
      }
      applicationlist.push(app);
    }
    this.applicationUpload.ready = true;
    this.applicationUpload.applications = applicationlist;
  }

  previewApplicationUpload(modal:any){
    this.modalService.open(modal, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
    }, (reason) => {
    });
  }

  completeApplicationUpload(){
    if( this.applicationUpload.applications && this.applicationUpload.applications.length > 0 ){
      this.appsvc.uploadApplications(this.applicationUpload.applications).then((resp:any)=>{
        this.applicationUpload.status = "complete";
        if( resp && resp.length > 0 ){
          this.applicationUpload.response != "Error During Upload";
          this.applicationUpload.error != JSON.stringify(resp);
        }
        else if( resp ){
          this.applicationUpload.response != "Upload Complete";
          this.applicationUpload.error = undefined;
        }
      });
    }
  }

  applicationPreviewSelect (event: any) {
    this.applicationUpload.editselect_str = event.target.value;
    this.applicationUpload.editselect = JSON.parse(event.target.value);
  }

}
