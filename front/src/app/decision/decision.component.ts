import { Component } from '@angular/core';
import {UserService} from "../services/user.service";
import {User} from "../model/User";
import {DecisionService} from "../services/decision.service";
import {HttpHeaders} from "@angular/common/http";

@Component({
  selector: 'app-decision',
  templateUrl: './decision.component.html',
  styleUrls: ['./decision.component.css']
})
export class DecisionComponent {

  date: String = this.formatDate(new Date())
  user: User;
  odluka: string = 'Prihvacen';
  decision: boolean = true;
  rejectionReason: string = '';
  zahtev: string = '';

  endpointPrefix: { [key: string]: string } = {
    'A1': '/a1/resenje-postoji/', // TODO: DODAJ TVOJU PUTANJU
    'P1': '/p1/resenje-postoji/',
    'Z1': '/z1/' // TODO: DODAJ TVOJU PUTANJU
  }

  constructor(private userService: UserService, private decisionService: DecisionService) {
    this.user = userService.user
    this.zahtev = decisionService.getDocumentForDecision()
  }


  formatDate(date: Date) {
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');

    return `${year}-${month}-${day}`;
  }

  back() {
    location.reload()
  }

  decisionTrue() {
    this.decision = true;
  }
  decisionFalse() {
    this.decision = false;
  }

  confirmDecision() {
    if (!this.decision && this.rejectionReason === '') {
      alert("Morate navesti razlog odbijanja")
      return
    }
    // @ts-ignore
    let endpointPrefix = this.zahtev.at(0).toLowerCase() + '1'
    let obj = {
      brojPrijave: this.zahtev,
      imeSluzbenika: this.user.name,
      prezimeSluzbenika: this.user.surname,
      emailSluzbenika: this.user.email,
      datumObrade: this.date,
      razlogOdbijanja: this.rejectionReason,
      odbijen: !this.decision
    }
    const request = this.decisionService.postDecision(obj, endpointPrefix)
    request.subscribe((res) =>{
      alert("Resenje je doneto")
      location.reload()
    })
  }

}
