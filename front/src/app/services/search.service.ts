import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import * as JsonToXML from "js2xmlparser";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  serverPrefix: { [key: string]: string } = {
    'A1': '/a1/',
    'P1': '/p1/',
    'Z1': '/z1/'
  }

  constructor(private http: HttpClient) { }

  get(textParam: string, doctype: string) {
    let prefix: string = this.getUrlPrefix(doctype);
    return this.http.get(prefix + 'text-search/' + textParam, this.getOptions());
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

  post(searchParam: string, tipMetapodatka: string, doctype: string): Observable<Object> {
    let body = {
      searchParam,
      tipMetapodatka
    }
    let prefix: string = this.getUrlPrefix(doctype);
    const xmlZahtev = JsonToXML.parse("advancedSearch", body);
    return this.http.post(prefix + 'advanced-search', xmlZahtev, this.getOptions());
  }

  getUrlPrefix(doctype: string) {
    return this.serverPrefix[doctype]
  }

}
