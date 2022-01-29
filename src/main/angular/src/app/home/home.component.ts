import { Component, OnInit } from '@angular/core';
import { Environment } from 'src/environments/environment-variables';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  public title = Environment['ANGULAR_APP_TITLE'];
  
  constructor() { }

  ngOnInit() {
  }

}
