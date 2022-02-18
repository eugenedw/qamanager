import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-modal-confirm',
  templateUrl: './modal-confirm.component.html',
  styleUrls: ['./modal-confirm.component.scss']
})
export class ModalConfirmComponent implements OnInit {

  @Output("dismissaction") dismissaction: EventEmitter<any> = new EventEmitter();
  @Output("continueaction") continueaction: EventEmitter<any> = new EventEmitter();
  @Input() config : any = {};
  @ViewChild("modalConfirmContent") modalConfirmContent : any;
  private closeResult:any;

  constructor(private modalService:NgbModal) { }

  ngOnInit(): void {
  }

  dismiss(){
    this.dismissaction.emit();
    this.modalService.dismissAll();
  }

  continue(){
    this.continueaction.emit();
    this.modalService.dismissAll();
  }

  show(){
    this.modalService.open(this.modalConfirmContent, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
    });
  }

}
