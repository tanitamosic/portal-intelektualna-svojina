import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import * as JsonToXML from "js2xmlparser";
import {isObject} from "js2xmlparser/lib/utils";

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  postEndpoint: { [key: string]: string } = {
    'A1': '/a1/', // TODO: DODAJ TVOJU PUTANJU
    'P1': '/p1/post-p1',
    'Z1': '/z1/post-z1'
  }

  constructor(private http: HttpClient) { }

  postRequest(data: any, docType: string) {
    if (isObject(data)) {
      data = JsonToXML.parse("zahtev", data);
    }
    let endpoint: string = this.getUrlEndpoint(docType);
    return this.http.post(endpoint, data, this.getOptions());

  }

  private getOptions() {
    return {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin': '*',
        'Accept': 'application/xml',
        'Content-Type': 'application/xml',
      }),
      responseType: 'document' as 'json'
    };
  }

  getUrlEndpoint(doctype: string) {
    return this.postEndpoint[doctype]
  }
}
