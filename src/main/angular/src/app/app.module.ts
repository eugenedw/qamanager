import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { RRFComponent } from './rrf/rrf.component';
import { RRFListComponent } from './rrflist/rrflist.component';
import { ApplicationService } from '../shared/service/application/application.service';
import { HttpClientModule } from '@angular/common/http';
import { ProgramService } from '../shared/service/program/program.service';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { TeamlistComponent } from './teamlist/teamlist.component';
import { PersonComponent } from './person/person.component';
import { ApplicationListComponent } from './application-list/application-list.component';
import { AbstractFilterPipe } from '../shared/pipe/abstractfilter.pipe';
import { ApplicationComponent } from './application/application.component';
import { AutomationListComponent } from './automation-list/automation-list.component';
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RRFComponent,
    RRFListComponent,
    TeamlistComponent,
    PersonComponent,
    ApplicationListComponent,
    AbstractFilterPipe,
    ApplicationComponent,
    AutomationListComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    ApplicationService,
    ProgramService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
