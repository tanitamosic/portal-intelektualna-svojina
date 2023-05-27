import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import * as JsonToXML from "js2xmlparser";

@Injectable({
  providedIn: 'root'
})
export class DecisionService {

  documentForDecision: string = ''

  constructor(private http: HttpClient) { }


  getRequestDecision(name: string, endpointPrefix: string) {
    return this.http.get(endpointPrefix + '/resenje-postoji/' + name, this.getOptions())
  }

  postDecision(resenje: any, endpointPrefix: string) {
    const xmlZahtev = JsonToXML.parse("loginRequest", resenje);
    return this.http.post(endpointPrefix + '/resi', xmlZahtev, this.getOptions())
  }

  setDocumentForDecision(name: string) {
    this.documentForDecision = name;
  }

  getDocumentForDecision() { return this.documentForDecision; }

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
