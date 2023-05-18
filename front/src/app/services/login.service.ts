import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import * as JsonToXML from "js2xmlparser";

@Injectable({
  providedIn: 'root'
})
export class LoginService  {

  constructor(private http: HttpClient) { }

  post(email: String, password: String) {
    let body = {
      "email": email,
      "password": password
    }
    const xmlZahtev = JsonToXML.parse("loginRequest", body);
    return this.http.post('/api/login', xmlZahtev, this.getOptions());
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
