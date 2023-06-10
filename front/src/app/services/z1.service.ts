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

  isValidFilled(zahtev: ZahtevZ1DTO) {
    let valid = true;
    valid &&= this.isValidLice(zahtev.podnosilac);
    valid &&= this.isValidLice(zahtev.punomocnik);

    valid &&= this.isValidLice(zahtev.predstavnik);

    // valid &&= this.isValidKlasa();

    return valid;
  }

  isValidConsoleLog(zahtev: ZahtevZ1DTO) {
    console.log(this.isValidLice(zahtev.podnosilac));
    console.log(this.isValidLice(zahtev.punomocnik));
    
    console.log(this.isValidLice(zahtev.predstavnik));
  }

  isValidLice(lice: Lice) {
    let valid = true;
    valid &&= this.isValidName(lice);
    valid &&= this.isValidAdresa(lice.adresa);
    valid &&= this.isValidKontakt(lice.kontakt);

    return valid;
  }

  isValidName(lice: Lice) {
    if (lice.tipLica === "fizickoLice") {
      return (this.doesContainOnlyLetters(lice.ime) && this.doesContainOnlyLetters(lice.prezime));
    } else {
      return this.doesContainOnlyLetters(lice.poslovnoIme);
    }
  }

  isValidAdresa(adresa: Adresa) {
    let valid = true;
    valid &&= this.doesContainOnlyLetters(adresa.ulica);
    // valid &&= adresa.postanskiBroj > 9999 && adresa.postanskiBroj < 100000;
    valid &&= this.doesContainOnlyLetters(adresa.mesto);


    return valid;
  }

  isValidKontakt(kontakt: Kontakt) {
    let valid = true;
    valid &&= this.phonenumber.test(kontakt.telefon);
    valid &&= this.email.test(kontakt.email);
    valid &&= this.phonenumber.test(kontakt.faks);

    return valid;
  }

  isValidKlasa(chosenKlasas: string[]) {
    let valid = chosenKlasas.length > 0;

    return valid;
  }

  doesContainOnlyLetters(word: string): boolean {
    word = word.trim();
    return this.letters.test(word);
  }

  createTestZahtev() {
    let zahtev = new ZahtevZ1DTO();
    zahtev.podnosilac = this.createTestLice();
    zahtev.punomocnik = this.createTestLice();
    zahtev.predstavnik = this.createTestLice();


    zahtev.klase = "1 - Oruzje|2 - Malkarasa";
    // zahtev.neededPrilogsConcatenated = "PRIMERAK_ZNAKA|SPISAK_ROBE_I_USLUGA|DOKAZ_O_UPLATI_TAKSE"
    zahtev.pravoPrvenstva = "SAJAMSKO";

    return zahtev;
  }

  createTestLice() {
    let lice = new Lice();
    lice.tipLica = "fizickoLice";
    lice.ime = "Ime";
    lice.prezime = "Prezime";
    lice.kontakt.faks = "12345";
    lice.kontakt.telefon = "12345";
    lice.kontakt.email = "12345@emal.com";
    lice.adresa.ulica = "Ulica";
    lice.adresa.broj = "23";
    lice.adresa.postanskiBroj = "11000";
    lice.adresa.mesto = "Mesto";

    return lice;
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

    return this.http.post<Object>(this.zigUrl + "/upload-image/" + brojPrijaveZiga + "-" + tipPrilog, formData, this.getOptions());
  }

  public saveAfterPrilogAddition(brojPrijaveZiga: string) {
    let brojPrijaveZigaParts: string[] = brojPrijaveZiga.split("/");

    return this.http.get<Object>(this.zigUrl + "/save/" + brojPrijaveZigaParts[0] + "-" + brojPrijaveZigaParts[1],this.getOptions());
  }

  public empty() {
    return this.http.get<Object>(this.zigUrl + "/empty", this.getOptions());
  }

}
