import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule, Title } from '@angular/platform-browser';
import { NgbAlertModule } from '@ng-bootstrap/ng-bootstrap';
import { AbstractFilterPipe } from '../shared/pipe/abstractfilter.pipe';
import { ApplicationService } from '../shared/service/application/application.service';
import { ProgramService } from '../shared/service/program/program.service';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ApplicationListComponent } from './application-list/application-list.component';
import { ApplicationComponent } from './application/application.component';
import { AutomationListComponent } from './automation-list/automation-list.component';
import { HomeComponent } from './home/home.component';
import { PersonComponent } from './person/person.component';
import { ProgramListComponent } from './program-list/program-list.component';
import { RRFComponent } from './rrf/rrf.component';
import { RRFListComponent } from './rrflist/rrflist.component';
import { TeamlistComponent } from './teamlist/teamlist.component';
import { BackButtonDirective } from 'src/shared/directive/back-button';
import { MilestoneListComponent } from './milestone-list/milestone-list.component';
import { MilestoneComponent } from './milestone/milestone.component';
import { DataTableComponent } from './data-table/data-table.component';
import { TestcaseComponent } from './testcase/testcase.component';
import { SaveButtonComponent } from './save-button/save-button.component';
import { ModalConfirmComponent } from './modal-confirm/modal-confirm.component';
import { PreviousRouteService } from 'src/shared/service/previousroute/previousroute.service';
import { TestCaseListComponent } from './test-case-list/test-case-list.component';
import { TestRunComponent } from './test-run/test-run.component';

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
    AutomationListComponent,
    ProgramListComponent,
    MilestoneComponent,
    MilestoneListComponent,
    BackButtonDirective,
    DataTableComponent,
    TestcaseComponent,
    SaveButtonComponent,
    ModalConfirmComponent,
    TestCaseListComponent,
    TestRunComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    NgbAlertModule
  ],
  providers: [
    ApplicationService,
    ProgramService,
    PreviousRouteService,
    Title
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
