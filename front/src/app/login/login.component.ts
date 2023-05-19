import { Component } from '@angular/core';
import {LoginService} from "../services/login.service";
import {UserService} from "../services/user.service";
import {User} from "../model/User";
import {XmlParser} from "@angular/compiler";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  password: string = ''
  email: string = ''

  constructor( private userService: UserService, private loginService: LoginService, private router: Router) {
  }

  login(event: any) {
    let request = this.loginService.post(this.email, this.password);
    request.subscribe((res: any) =>{
      let obj = {
        id: res.getElementsByTagName('id')[0].textContent,
        email: res.getElementsByTagName('email')[0].textContent,
        password: res.getElementsByTagName('password')[0].textContent,
        name: res.getElementsByTagName('name')[0].textContent,
        surname: res.getElementsByTagName('surname')[0].textContent,
        role: res.getElementsByTagName('role')[0].textContent,
      }
      let u: User = new User(obj);
      localStorage.setItem('user', JSON.stringify(u));
      this.userService.setUser(u);

      this.router.navigate(['/' + u.role + '/home']).then(r => {});
    }, (_) => {
      alert("Login failed")
    })
  }

}
