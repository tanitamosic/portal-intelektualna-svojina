import { Component } from '@angular/core';
import {UserService} from "./services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'L-XML';
  userService: UserService
  constructor(userService: UserService, private router: Router) {
    this.userService = userService
  }

  ngOnInit() {
    this.userService.onLoad();
  }

  login() {
    this.router.navigateByUrl('login').then(r => {});
  }
}
