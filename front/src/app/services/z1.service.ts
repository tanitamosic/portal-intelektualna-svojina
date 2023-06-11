import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from "rxjs";
//import {AuthService} from "./auth.service";
import * as JsonToXML from "js2xmlparser";
import {ZahtevZ1DTO} from "../model/z1/ZahtevZ1DTO";
import {Lice} from "../model/z1/Lice";
import {Adresa} from "../model/z1/Adresa";
import {Kontakt} from "../model/z1/Kontakt";


@Injectable({
  providedIn: 'root'
})
export class Z1Service {

  private readonly zigUrl: string;
  letters = /^[A-Za-z\s]+$/;
  phonenumber = /^[0-9\+-\\]+$/;
  email = /.+\@.+\..+/;

  constructor(private http: HttpClient) {
    this.zigUrl = '/z1';
  }

  public postZahtev(zahtev: any): Observable<any> {
    const xmlZahtev = JsonToXML.parse("zahtev", zahtev);
    console.log(xmlZahtev)
    console.log("evo saljem zahtev")
    return this.http.post<any>(this.zigUrl+"/post-z1", xmlZahtev, this.getOptions());
  }

  public getZahtev(brojPrijave: string): Observable<any> {
    const xmlZahtev = JsonToXML.parse("NazivPrijaveDTO", brojPrijave);
    console.log(xmlZahtev)
    return this.http.get<any>(this.zigUrl + "/" + brojPrijave, this.getOptions());
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

  private getFileOptions() {
    return {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin': '*',
      }),
      responseType: 'document' as 'json'
    };
  }


  public postImage(brojPrijaveZiga: string, file: any){
    let formData = new FormData();
    let brojPrijaveZigaParts: string[] = brojPrijaveZiga.split("/");
    formData.append("file", file);

    return this.http.post<Object>(this.zigUrl + "/upload-image/" + brojPrijaveZiga, formData, this.getOptions());
  }
  public postPrilog(brojPrijaveZiga: string, tipPrilog: string, file: any) {
    let formData = new FormData();
    let brojPrijaveZigaParts: string[] = brojPrijaveZiga.split("/");
    formData.append("file", file);

    return this.http.post<Object>(this.zigUrl + "/upload-file/" + brojPrijaveZiga + "-" + tipPrilog, formData, this.getFileOptions());
  }

  public saveAfterPrilogAddition(brojPrijaveZiga: string) {
    let brojPrijaveZigaParts: string[] = brojPrijaveZiga.split("/");

    return this.http.get<Object>(this.zigUrl + "/save/" + brojPrijaveZigaParts[0] + "-" + brojPrijaveZigaParts[1],this.getFileOptions());
  }

  public empty() {
    return this.http.get<Object>(this.zigUrl + "/empty", this.getOptions());
  }

}
