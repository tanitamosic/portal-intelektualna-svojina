import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

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

  getUrlPrefix(doctype: string) {
    return this.serverPrefix[doctype]
  }

}
