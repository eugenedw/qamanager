import { Component, OnInit } from '@angular/core';
import { Application } from 'src/shared/model/application';
import { Automation } from '../../shared/model/automation';
import { RRFService } from '../../shared/service/rrf/rrf.service';

@Component({
  selector: 'app-automation-list',
  templateUrl: './automation-list.component.html',
  styleUrls: ['./automation-list.component.scss']
})
export class AutomationListComponent implements OnInit {

  public automation:Array<Automation> = [];

  constructor(public rrfservice:RRFService) { }

  ngOnInit() {
  }

  appinfo(appid:string){
    return new Application();
  }

}
