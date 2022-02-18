import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-save-button',
  templateUrl: './save-button.component.html',
  styleUrls: ['./save-button.component.scss']
})
export class SaveButtonComponent implements OnInit {
  
  @Output("saveaction") saveaction: EventEmitter<any> = new EventEmitter();
  @Output("deleteaction") deleteaction: EventEmitter<any> = new EventEmitter();
  @Input() savestate : boolean = false;
  @Input() indicator : string = "ellipsis";

  constructor() { }

  ngOnInit(): void {

  }

  save(){
    this.saveaction.emit();
  }

  delete(){
    this.deleteaction.emit();
  }

}
