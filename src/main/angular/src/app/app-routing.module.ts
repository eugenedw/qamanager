import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from '../app/home/home.component';
import { RRFComponent } from './rrf/rrf.component';
import { RRFListComponent } from './rrflist/rrflist.component';
import { TeamlistComponent } from './teamlist/teamlist.component';
import { PersonComponent } from './person/person.component';
import { ApplicationComponent } from './application/application.component';
import { ApplicationListComponent } from './application-list/application-list.component';
import { AutomationListComponent } from './automation-list/automation-list.component';

const routes: Routes = [
  {
    path: '', redirectTo: '/home', pathMatch: "full"
  },
  { path: 'home', component: HomeComponent },
  { path: 'rrf/:rrfid', component: RRFComponent },
  { path: 'rrf-list', component: RRFListComponent },
  { path: 'team/list', component: TeamlistComponent },
  { path: 'person/:personid', component: PersonComponent },
  { path: 'application/list', component: ApplicationListComponent },
  { path: 'application/view/:appid', component: ApplicationComponent },
  { path: 'automation/list', component: AutomationListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
