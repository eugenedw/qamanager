import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Application } from '../../shared/model/application';
import { ProgramArea } from '../../shared/model/program.area';
import { ReleaseRRF } from '../../shared/model/releaserrf';
import { ApplicationService } from '../../shared/service/application/application.service';
import { ProgramService } from '../../shared/service/program/program.service';
import { RRFService } from '../../shared/service/rrf/rrf.service';

@Component({
  selector: 'app-application',
  templateUrl: './application.component.html',
  styleUrls: ['./application.component.scss']
})
export class ApplicationComponent implements OnInit {

  public currentApplication!:Application;
  public programlist!:Array<ProgramArea>;
  public releases!:Array<ReleaseRRF>;

  public current_tab:string = 'app-tab-general';

  constructor(public route:ActivatedRoute, public appsvc:ApplicationService, 
              public programsvc:ProgramService, public rrfsvc:RRFService, public router:Router) { 
  }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('appid');
    if( id != "new" && id != null ){
      this.appsvc.getApplicationById(id).then((resp:any)=>{
        this.currentApplication = resp;
        //resp.find(app => app.guid === id);
        this.rrfsvc.getByApp(this.currentApplication.guid).then((resp:any)=>{
          //resp : Array<ReleaseRRF>
          this.releases = resp;
        });
      });
    }
    else{
      this.currentApplication = new Application();
    }
    this.programsvc.getPrograms().then((resp:Array<ProgramArea>)=>{
      this.programlist = resp;
    });
  }

  printReleases(){
    return JSON.stringify(this.releases,null,2);
  }

  printApplication(){
    return JSON.stringify(this.currentApplication,null,2);
  }

  saveapplication(){
    this.appsvc.saveApplication(this.currentApplication)
      .then((app:any)=>{
        //app : Application
        this.router.navigateByUrl("/application/view/".concat(app.guid))
    });
  }

}
