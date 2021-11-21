import { Component, OnInit } from '@angular/core';
import { Application } from '../../shared/model/application';
import { ActivatedRoute } from '@angular/router';
import { ApplicationService } from '../../shared/service/application/application.service';
import { ProgramService } from '../../shared/service/program/program.service';
import { ProgramArea } from '../../shared/model/program.area';
import { RRFService } from '../../shared/service/rrf/rrf.service';
import { ReleaseRRF } from '../../shared/model/releaserrf';

@Component({
  selector: 'app-application',
  templateUrl: './application.component.html',
  styleUrls: ['./application.component.scss']
})
export class ApplicationComponent implements OnInit {

  public currentApplication:Application;
  public programlist:Array<ProgramArea>;
  public releases:Array<ReleaseRRF>;

  public current_tab:string = 'app-tab-general';

  constructor(public route:ActivatedRoute, public appsvc:ApplicationService, 
              public programsvc:ProgramService, public rrfsvc:RRFService) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('appid');
    if( id != "new" ){
      this.appsvc.getApplicationList().then((resp:Array<Application>)=>{
        this.currentApplication = resp.find(app => app.guid === id);
        this.rrfsvc.getByApp(this.currentApplication.guid).then((resp:Array<ReleaseRRF>)=>{
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

}
