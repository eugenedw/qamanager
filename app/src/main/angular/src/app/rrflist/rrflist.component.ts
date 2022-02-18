import { Component, OnInit } from '@angular/core';
import { RRFService } from '../../shared/service/rrf/rrf.service';
import { ReleaseRRF } from '../../shared/model/releaserrf';
import { ApplicationService } from '../../shared/service/application/application.service';
import { Application } from '../../shared/model/application';
import { RRFStatus } from '../../shared/model/rrfstatus';
import { ActivatedRoute } from '@angular/router';
import { ActivityLogType } from 'src/shared/model/activitylogtype';

@Component({
  selector: 'app-rrflist',
  templateUrl: './rrflist.component.html',
  styleUrls: ['./rrflist.component.scss']
})
export class RRFListComponent implements OnInit {

  rrflist !: Array<ReleaseRRF>;
  applist !: Array<Application>;
  rrf_filter : string = 'all';
  query_filter !: string;

  constructor(public rrfservice:RRFService, public route:ActivatedRoute, public appsvc:ApplicationService) {
    const at_risk = this.route.snapshot.queryParamMap.get('atrisk');
    if( at_risk ){
      this.rrf_filter = "atrisk";
      this.query_filter = "atrisk";
    }
  }

  ngOnInit() {
    this.list();
    (this.appsvc as any).getApplicationList().then((resp:any)=>{
      this.applist = resp;
    })
  }

  public list(){
    this.rrfservice.list(1,20).then((resp:any)=>{
      this.rrflist = resp;
      this.rrflist = this.rrflist.filter((el:ReleaseRRF,idx,arr)=>{
        if( this.rrf_filter == 'all' ){
          return true;
        }
        //let rrfstatus = Object(RRFStatus)[Object.values(RRFStatus).indexOf(this.rrf_filter.toUpperCase())];
        let rrfstatus = Object(RRFStatus)[this.rrf_filter.toUpperCase()];
        return el.status == rrfstatus;
      });
    },(rej)=>{
      alert("ERROR: "+rej)
    });
  }

  public filter(tag:string){
    this.rrf_filter = tag;
    this.list();
  }

  public appinfo(appid:string){
    if( this.applist !== undefined ){
      let _app : Application = this.applist.find(app => app.guid === appid)!;
      return _app;
    }
    return new Application();
  }

  public checkLogType(tp:ActivityLogType,key:string) {
    return Object(ActivityLogType)[key] == tp;
  }

}
