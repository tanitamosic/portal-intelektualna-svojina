import { Component } from '@angular/core';
import {RegistrationService} from "../services/registration.service";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.sass']
})
export class RegistrationComponent {
  email: string = '';
  password: string = '';
  name: string = '';
  surname: string = '';


  constructor(private regService: RegistrationService) {
  }

  register($event: MouseEvent) {
    const request = this.regService.post(this.email, this.password, this.name, this.surname);
    request.subscribe((res) => {
      alert('Registracija uspesna. Mozete se ulogovati');
    }, (err) => {
      alert('Email vec u upotrebi')
    })
  }
}
