import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import * as JsonToXML from "js2xmlparser";

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private http: HttpClient) { }

  post(email: string, password: string, name: string, surname: string) {
    let body = {
      "email": email,
      "password": password,
      "name": name,
      "surname": surname
    }
    const xmlZahtev = JsonToXML.parse("registrationRequest", body);
    return this.http.post('/entry/register', xmlZahtev, this.getOptions());
  }
  private getOptions() {
    return {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin': '*',
        'Content-Type': 'application/xml',
      }),
      responseType: 'document' as 'json'
    };
  }
}
