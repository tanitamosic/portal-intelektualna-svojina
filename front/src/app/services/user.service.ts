import { Injectable } from '@angular/core';
import {User} from "../model/User";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  user: User
  constructor(private router: Router) {
    this.user = new User({'id': 0, 'name': '', 'surname': '', 'email': '', 'role': '', 'password': ''})
  }

  setUser(u: any) {
    this.user = u
  }

  isLoggedIn(): boolean {
    return this.user.id != 0
  }

  onLoad(): void {
    if (localStorage.getItem("user") !== null) {
      // @ts-ignore
      this.user = JSON.parse(localStorage.getItem("user"));
      console.log(this.user);

      this.router.navigateByUrl(this.user.role + '/home').then(r => {});
    }
  }
}
