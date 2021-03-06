import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ApplicationListComponent } from './application-list/application-list.component';
import { ApplicationComponent } from './application/application.component';
import { AutomationListComponent } from './automation-list/automation-list.component';
import { PersonComponent } from './person/person.component';
import { ProgramListComponent } from './program-list/program-list.component';
import { RRFComponent } from './rrf/rrf.component';
import { RRFListComponent } from './rrflist/rrflist.component';
import { TeamlistComponent } from './teamlist/teamlist.component';
import { MilestoneComponent } from './milestone/milestone.component';
import { TestcaseComponent } from './testcase/testcase.component';
import { TestRunComponent } from './test-run/test-run.component';

const routes: Routes = [
  {
    path: '', redirectTo: '/home', pathMatch: "full"
  },
  { path: 'home', component: HomeComponent, data: { title: 'Home' } },
  { path: 'rrf/:rrfid', component: RRFComponent, data: { title: 'Viewing Release' } },
  { path: 'rrf-list', component: RRFListComponent, data: { title: 'Releases' } },
  { path: 'team/list', component: TeamlistComponent, data: { title: 'Team' } },
  { path: 'person/:personid', component: PersonComponent, data: { title: 'Person' } },
  { path: 'application/list', component: ApplicationListComponent, data: { title: 'Applications' } },
  { path: 'application/view/:appid', component: ApplicationComponent, data: { title: 'Viewing Application' } },
  { path: 'automation/list', component: AutomationListComponent, data: { title: 'Automation' } },
  { path: 'program/list', component: ProgramListComponent, data: { title: 'Programs' } },
  { path: 'milestone/:milestoneid', component: MilestoneComponent, data: { title: 'Viewing Milestone' } },
  { path: 'test-case/:tcid', component: TestcaseComponent, data: { title: 'Viewing Test Case' } },
  { path: 'test-run/:trid', component: TestRunComponent, data: { title: 'Viewing Test Run' } }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true, relativeLinkResolution: 'legacy', anchorScrolling: 'enabled' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
