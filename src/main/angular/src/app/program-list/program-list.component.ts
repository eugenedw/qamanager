import { Component, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ProgramArea } from 'src/shared/model/program.area';
import { ProgramService } from 'src/shared/service/program/program.service';

@Component({
  selector: 'app-program-list',
  templateUrl: './program-list.component.html',
  styleUrls: ['./program-list.component.scss']
})
export class ProgramListComponent implements OnInit {

  public closeResult = '';
  public currentProgram:ProgramArea = new ProgramArea();
  public programs:Array<ProgramArea> = []
  public filter : any = {
    "program_name" : ""
  }

  constructor(private programsvc:ProgramService, private modalService: NgbModal) { }

  ngOnInit() {
    this.loadprograms();
  }

  loadprograms(){
    this.programsvc.getPrograms().then((resp:Array<ProgramArea>)=>{
      this.programs = resp;
    });
  }

  saveProgram(){
    this.programsvc.saveProgram(this.currentProgram).then((prog:any)=>{
      this.currentProgram = prog;
      this.loadprograms();
    });
  }

  editProgram(content:any,_program:any) {
    this.currentProgram = _program;
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

}
