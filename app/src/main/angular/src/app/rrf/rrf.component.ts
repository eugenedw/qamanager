import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ActivityLog } from '../../shared/model/activitylog';
import { ActivityLogType } from '../../shared/model/activitylogtype';
import { Application } from '../../shared/model/application';
import { Person } from '../../shared/model/person';
import { ReleaseRRF } from '../../shared/model/releaserrf';
import { RRFStatus } from '../../shared/model/rrfstatus';
import { ApplicationService } from '../../shared/service/application/application.service';
import { PersonService } from '../../shared/service/person/person.service';
import { ProgramService } from '../../shared/service/program/program.service';
import { RRFService } from '../../shared/service/rrf/rrf.service';
import { UtilityService } from '../../shared/service/util.service';

@Component({
  selector: 'app-rrf',
  templateUrl: './rrf.component.html',
  styleUrls: ['./rrf.component.scss']
})
export class RRFComponent implements OnInit {

  public rrfstatus !: string;
  public applicationList !: Array<any>;
  public currentRRF !: ReleaseRRF;
  public rrfappid = undefined;
  public programs = undefined;
  public current_tab = 'rrf-tab-general';
  public rrf_search = undefined;
  public team_select = "";
  public personlist !: Array<Person>;
  public activity_log_entry !: string;
  public activity_log_entry_problem !: boolean;
  public prior_status !: RRFStatus;

  constructor(public route:ActivatedRoute, 
              public router:Router,
              public rrfservice:RRFService,
              public applicationService:ApplicationService,
              public programService:ProgramService,
              public personsvc:PersonService,
              public util:UtilityService) {
  }

  ngOnInit() {

    const id = this.route.snapshot.paramMap.get('rrfid');
    const app_id = this.route.snapshot.queryParamMap.get('app_id');
    
    if( id == 'new' ){
      this.rrfstatus = 'new'
      this.currentRRF = new ReleaseRRF();
      this.currentRRF.guid = UtilityService.uuid();
      this.currentRRF.dt_created = new Date();
      this.currentRRF.app_id != app_id;
    }
    else if( id == 'find' ){
      this.rrfstatus = "find";
      return;
    }
    else if( id !== null ){
      this.rrfservice.get(id).then((resp:any)=> {
        this.currentRRF = resp;
      });
    }
    this.applicationService.getApplicationList()!.then((resp)=>{
      this.applicationList = resp;
      if( this.currentRRF.app_id !== undefined && this.currentRRF.app_id != null && this.currentRRF.app_id != "" ){
        this.updateRRFProgram(this.currentRRF.app_id);
      }
    },(rej)=>{
      //alert(JSON.stringify(rej))
    }).catch(err=>{
      //alert(JSON.stringify(err))
    });
    this.programService.getPrograms().then((resp)=>{
      this.programs = resp;
    })
    this.personsvc.getPeople(1,1).then((resp:any)=>{
      if( this.currentRRF.team_list === undefined ){
        this.currentRRF.team_list = [];
      }
      this.personlist = resp;
    });
  }

  updateRRFProgram(application_id:string){
    this.currentRRF.app_id = application_id;
    const _app : Application = this.applicationList.find(app => app.guid === application_id)
    this.currentRRF.program_id = _app.programId;
  }

  findrelease(){
    if( this.rrf_search ){
      this.router.navigate(['/rrf/'+this.rrf_search])
      .then(() => {
        window.location.reload();
      });
    }
  }

  recommendedResources(){
    let recommend:Array<any> = [];
    if( this.currentRRF.app_id !== undefined && this.currentRRF.app_id != "" ){
      recommend = this.personlist.filter(p => p.applications && p.applications.indexOf(this.currentRRF.app_id) > -1 );
    }
    return recommend;
  }

  writeProgram(programId:string){
    if( programId !== undefined && programId != null && this.programs !== undefined ){
      return (this.programs as any).find((prog: { guid: string; }) => prog.guid === programId );
    }
  }

  addlog(){
    if( this.currentRRF.activity_log === undefined ){
      this.currentRRF.activity_log = [];
    }
    let logtype = ActivityLogType.COMMENT;
    if( this.activity_log_entry_problem ){
      logtype = ActivityLogType.PROBLEM;
    }
    this.currentRRF.activity_log.push(new ActivityLog(this.activity_log_entry,new Person("John","Euser","john.euser.ctr@mail.mil"),logtype));
  }

  addPersonToRelease(){
    if( this.team_select == "" ){
      return;
    }
    if( this.currentRRF.team_list.indexOf(this.team_select) < 0 ){
      this.currentRRF.team_list.push(this.team_select);
      this.team_select='';
    }
  }

  setrisk(){
    if( this.prior_status === undefined ){
      this.prior_status = this.currentRRF.status;
    }
    setTimeout(()=>{
      if( this.currentRRF.at_risk ){
        this.currentRRF.status = RRFStatus.ATRISK;
      }
      else{
        this.currentRRF.status = this.prior_status;
      }
    },500);
  }

  person(id:any){
    return this.personlist.find(person => person.guid === id); 
  }

  printRRF(){
    return JSON.stringify(this.currentRRF, null, 2);
  }

}
