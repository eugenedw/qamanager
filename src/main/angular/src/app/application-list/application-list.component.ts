import { Component, OnInit } from '@angular/core';
import { ApplicationService } from '../../shared/service/application/application.service';
import { ProgramService } from '../../shared/service/program/program.service';
import { Application } from '../../shared/model/application';
import { ProgramArea } from '../../shared/model/program.area';

@Component({
  selector: 'app-application-list',
  templateUrl: './application-list.component.html',
  styleUrls: ['./application-list.component.scss']
})
export class ApplicationListComponent implements OnInit {

  public applications:Array<Application>;
  public programs:Array<ProgramArea>;
  public filter : any = {
    "app_name" : "",
    "program_name" : ""
  }

  constructor(public appsvc:ApplicationService, public programsvc:ProgramService) { }

  ngOnInit() {
    this.appsvc.getApplicationList().then((resp:Array<Application>)=>{
      this.applications = resp;
    });
    this.programsvc.getPrograms().then(resp=>{
      this.programs = resp;
      for( let a in this.applications ){
        this.applications[a].program = this.program(this.applications[a].program_id);
      }
    });
  }

  program(programid){
    if( this.programs && programid ){
      return this.programs.find(p => p.guid === programid);
    }
  }

  printApplications(){
    return JSON.stringify(this.applications,null,2);
  }

}
