import { Component, OnInit } from '@angular/core';
import { Person } from '../../shared/model/person';
import { ActivatedRoute } from '@angular/router';
import { UtilityService } from '../../shared/service/util.service';
import { PersonService } from '../../shared/service/person/person.service';
import { Application } from '../../shared/model/application';
import { ApplicationService } from '../../shared/service/application/application.service';

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.scss']
})
export class PersonComponent implements OnInit {

  public currentPerson : Person;

  constructor(public route:ActivatedRoute, public util:UtilityService, 
              public personsvc:PersonService, public appsvc:ApplicationService) { }
 
  public personlist : Array<Person> = [];
  public applications : Array<Application> = [];

  private people = ["Kenisha Brasier",
                    "Danita Caudle",
                    "Emery Said",
                    "Signe Poll",
                    "Edyth Reinbold",
                    "Soila Markham",
                    "Deidra Trezza",
                    "Theressa Tsang",
                    "Vannessa Schmitmeyer",
                    "Deb Desanti",
                    "Mellie Strey",
                    "Jordan Smart",
                    "Leigh Groover",
                    "Elba Crupi",
                    "Jules Carini",
                    "Micheal Rinker",
                    "Antonio Bartkowski",
                    "Tamekia Lachance",
                    "Eloy Bosque",
                    "Alia Penrod",
                    "Jeanie Mcentee",
                    "Slyvia Bialaszewski",
                    "Pauletta Denis",
                    "Monty Smotherman",
                    "Myong Kealey",
                    "Tressa Earls",
                    "Damian Sobotka",
                    "Cristi Probst",
                    "Larhonda Koppes",
                    "Adelia Guerriero",
                    "Stacie Lumsden",
                    "Wendell Laning",
                    "Marc Johnsen",
                    "Jeanetta Wheless",
                    "Alexandria Legaspi",
                    "Leta Lindsey",
                    "Sook Kinsman",
                    "Leonel Dollard",
                    "Vern Nugent",
                    "Johana Staggers",
                    "Ewa Stgelais",
                    "Dennise Schiro",
                    "Spring Laberge",
                    "Jae Treadway",
                    "Buck Landman",
                    "Dustin Ahl",
                    "Maryetta Wagner",
                    "Marilu Finkel",
                    "Maxima Crochet",
                    "Lang Hanger"]

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('personid');
    if( id == 'new' ){
      this.currentPerson = new Person(null,null,null);
      this.currentPerson.guid = UtilityService.uuid();
    }
    else {
      this.personsvc.get(id).then((resp:Person)=> {
        this.currentPerson = resp;
      });
    }
    this.appsvc.getApplicationList().then(resp=>{
      this.applications = resp;
    });
    this.randompeople();
  }

  addapplication(){
    if( this.currentPerson.applications === undefined ){
      this.currentPerson.applications = [];
    }
    this.currentPerson.applications.push("");
  }

  removeapplication(appid){
    let _apps = [];
    for( let a in this.currentPerson.applications ){
      if( this.currentPerson.applications[a] != appid ){
        _apps.push(this.currentPerson.applications[a]);
      }
    }
    this.currentPerson.applications = _apps;
  }

  printapplication(guid:string){
    return this.applications.find(app => app.guid === guid);
  }

  randompeople(){
    for(let p in this.people ){
      let _p = new Person(null,null,null);
      _p.firstname = this.people[p].split(" ")[0];
      _p.lastname = this.people[p].split(" ")[1];
      _p.guid = UtilityService.uuid();
      _p.email = _p.firstname.toLocaleLowerCase().concat(".").concat(_p.lastname.toLowerCase()).concat("@mail.mil");
      _p.phone = this.randomphone();
      _p.roles = this.randomroles();
      _p.dt_created = new Date();
      this.personlist.push(_p);
    }
  }

  randomroles(){
    let roles = {
      "ANALYST" : false,
      "ENGINEER" : false,
      "MANAGER" : false,
      "DATA_STEWARD" : false,
      "ARCHITECT" : false
    }
    for( let k in roles ){
      roles[k] = (Math.floor(Math.random() * 2) == 0);
    }
    return roles;
  }

  randomphone(){
    let phone = "";
    for( let i = 0; i < 10; i++ ){
      if( i == 0 ){
        phone += "(";
      }
      else if( i == 3 ){
        phone += ") ";
      }
      else if( i == 6 ){
        phone += "-";
      }
      phone += Math.floor(Math.random()*10);
    }
    return phone;
  }

  printPerson(){
    return JSON.stringify(this.currentPerson, null, 2);
  }

  printPersonList(){
    return JSON.stringify(this.personlist, null, 2);
  }
  

}
