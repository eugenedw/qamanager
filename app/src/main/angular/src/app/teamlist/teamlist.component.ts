import { Component, OnInit } from '@angular/core';
import { PersonService } from '../../shared/service/person/person.service';
import { Person } from '../../shared/model/person';

@Component({
  selector: 'app-teamlist',
  templateUrl: './teamlist.component.html',
  styleUrls: ['./teamlist.component.scss']
})
export class TeamlistComponent implements OnInit {

  public personlist:Array<Person> = [];

  constructor(public personsvc:PersonService) { }

  ngOnInit() {
    this.personsvc.getPeople(1,10).then((res:any)=>{
      this.personlist = res;
    });
  }

}
