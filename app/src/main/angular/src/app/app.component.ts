import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Routes, RouterModule, Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { filter, map } from 'rxjs';
import { Environment } from 'src/environments/environment-variables';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  public title = Environment['ANGULAR_APP_TITLE'];

  constructor(public router:Router, public activatedRoute:ActivatedRoute, private titleService:Title){
    this.titleService.setTitle(this.title);
  }

  ngOnInit() {
    const appTitle = this.titleService.getTitle();
    this.router
      .events.pipe(
        filter(event => event instanceof NavigationEnd),
        map(() => {
          const child = this.activatedRoute.firstChild;
          if (child != null && child.snapshot.data && child.snapshot.data['title']) {
            return child.snapshot.data['title'];
          }
          return appTitle;
        })
      ).subscribe((ttl: string) => {
        this.titleService.setTitle(this.title.concat(" - ").concat(ttl));
      });
  }

}
