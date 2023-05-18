import { Component } from '@angular/core';
import {LoginService} from "../services/login.service";
import {UserService} from "../services/user.service";
import {User} from "../model/User";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  password: string = ''
  email: string = ''

  constructor( private userService: UserService, private loginService: LoginService) {
  }

  login(event: any) {
    let request = this.loginService.post(this.email, this.password);
    request.subscribe((res) =>{
      let u: User = new User(res);
      localStorage.setItem('user', JSON.stringify(u));
      this.userService.setUser(u);
    }, (_) => {
      alert("Login failed")
    })
  }

}
