import {Component, OnInit} from '@angular/core';
import {forkJoin, Observable} from "rxjs";
import { ZahtevZ1DTO } from 'src/app/model/z1/ZahtevZ1DTO';
import { Z1Service } from 'src/app/services/z1.service';


@Component({
  selector: 'app-z1',
  templateUrl: './z1.component.html',
  styleUrls: ['./z1.component.scss']
})
export class Z1Component implements OnInit  {
  zahtevDTO: ZahtevZ1DTO = new ZahtevZ1DTO();
  chosenKlasas: string[] = [];
  izabraneBoje: string[] = [];
  neededPrilogs: string[] = [];
  statusPunomocja: string = "";

  valid: boolean = true;
  // moguceBoje: string[] = ['BELA', 'CRNA', 'PLAVA', 'ZELENA', 'CRVENA', 'ZUTA', 'BRAON', 'ROZE', 'LJUBICASTA', 'SIVA', 'KREM', 'NARANDZASTA'];
  moguceKlase: string[] = ['1 - Hemijski proizvodi u industriji, nauci i poljoprivredi',
                            '2 - Boje, firnajzi, lakovi',
                            '3 - Nemedicinska kozmetika i toaletni preparati',
    '4 - Industrijska ulja i masti, vosak, maziva',
    '5 - Farmaceutski, medicinski i veterinarski proizvodi',
    '6 - Obični metali i njihove legure, rude',
    '7 - Mašine, mašinski alati, električni alati',
    '8 - Ručni alati i sprave kojima se ručno upravlja',
    '9 - Aparati',
    '10 - Hirurški, medicinski, zubarski i veterinarski instrumenti',
    '11 - Instalacije za osvetljenje, grejanje, hlađenje',
    '12 - Vozila za kretanje po zemlji, vazduhu, vodi',
    '13 - Vatreno oružje',
    '14 - Dragoceni metali i njihove rude',
    '15 - Muzički instrumenti',
    '16 - Hartija i karton',
    '17 - Guma',
    '18 - Koža i imitacija kože',
    '19 - Građevniski materijal',
    '20 - Nameštaj, ogledala, okviri za slike',
    '21 - Kućne ili kuhinjske sprave i posude',
    '22 - Konopci i uzice',
    '23 - Pređa i konac',
    '24 - Tekstil i zamena za tekstil',
    '25 - Odeća, obuća, pokrivala za glavu',
    '26 - Čipka, uzice i vez',
    '27 - Tepisi, asure, prostirke i otirači',
    '28 - Igre, igračke, predmeti za igru',
    '29 - Meso, riba, živina i divljač',
    '30 - Kafa, čaj, kakao, i zamene',
    '31 - Sirovi poljoprivredni i šumarski proizvodi',
    '32 - Pivo, bezalkoholni napici, mineralne vode, sokovi',
    '33 - Alkoholna pića izuzev piva',
    '34 - Duvan i zamene',
    '35 - Oglašavanje',
    '36 - Finansijke, monetarne i bankarske usluge',
    '37 - Građevinske usluge',
    '38 - Usluge telekomunikacija',
    '39 - Transportne usluge',
    '40 - Obrađivanje materijala',
    '41 - Obrazovne usluge' ,
    '42 - Naučne i tehnološke usluge, usluge istraživanja',
    '43 - Obezbeđivanje hrane i pića',
    '44 - Medicinske usluge',
    '45 - Pravne usluge' ]
  etipPrilogaPRIMERAK_ZNAKA = "Primerak znaka";
  etipPrilogaSPISAK_ROBE_I_USLUGA = "Spisak robe i usluga";
  etipPrilogaDOKAZ_O_UPLATI_TAKSE = "Punomocje";
  etipPrilogaOPSTI_AKT_O_ZIGU = "Opsti akt o kolektivnom zigu/zigu garancije";
  etipPrilogaDOKAZ_O_PRAVU_PRVENSTVA = "Dokaz o pravu prvenstva";
  prilogPRIMERAK_ZNAKA: any;
  prilogSPISAK_ROBE_I_USLUGA: any;
  prilogDOKAZ_O_UPLATI_TAKSE: any;
  prilogOPSTI_AKT_O_ZIGU: any;
  prilogDOKAZ_O_PRAVU_PRVENSTVA: any;
  prilogPUNOMOCJE: any;


  constructor(private servis: Z1Service) {
  }

  ngOnInit(){
    this.resetEverything();

  }

  resetEverything(){
    this.zahtevDTO = new ZahtevZ1DTO();
    this.chosenKlasas = [];
    this.izabraneBoje = [];
    this.neededPrilogs = [];
    this.resetUploads();
  }

  resetUploads(){
    this.prilogPRIMERAK_ZNAKA = [];
    this.prilogSPISAK_ROBE_I_USLUGA = [];
    this.prilogDOKAZ_O_UPLATI_TAKSE = [];
    this.prilogOPSTI_AKT_O_ZIGU = [];
    this.prilogDOKAZ_O_PRAVU_PRVENSTVA = [];
    this.prilogPUNOMOCJE = [];
  }

  addNeededPrilogType(prilogType:string){
    this.neededPrilogs.push(prilogType);
  }

  addAlwaysNeededPrilogTypes(){
    this.addNeededPrilogType(this.etipPrilogaPRIMERAK_ZNAKA);
    this.addNeededPrilogType( this.etipPrilogaSPISAK_ROBE_I_USLUGA);
    this.addNeededPrilogType( this.etipPrilogaDOKAZ_O_UPLATI_TAKSE);
  }

  removedNeededPrilogType(prilogType:string){
    this.neededPrilogs = this.neededPrilogs.filter(type => type !== prilogType);
  }

  onTipZigSelectionChange(type:string){
    if (type === "INDIVIDUALNI_ZIG"){
      this.removedNeededPrilogType(this.etipPrilogaOPSTI_AKT_O_ZIGU);
    } else {
      this.addNeededPrilogType( this.etipPrilogaOPSTI_AKT_O_ZIGU);
    }
  }

  onPravoPrvenstvaSelectionChange(type:string){
    if (type === ""){
      this.removedNeededPrilogType( this.etipPrilogaDOKAZ_O_PRAVU_PRVENSTVA);
    } else {
      this.addNeededPrilogType( this.etipPrilogaDOKAZ_O_PRAVU_PRVENSTVA);
    }
  }

  concatenateKlase(){
    return this.concatenate(this.chosenKlasas);
  }

  concatenateBoje(){
    return this.concatenate(this.izabraneBoje);
  }

  markAttachments(){
    
    if(!this.prilogPRIMERAK_ZNAKA.empty){
      this.zahtevDTO.primerakZnaka=true;
    }
    if(!this.prilogSPISAK_ROBE_I_USLUGA.empty){
      this.zahtevDTO.spisak=true;
    }
    if(!this.prilogDOKAZ_O_UPLATI_TAKSE.empty){
      this.zahtevDTO.dokazTaksa=true;
    }
    if(!this.prilogOPSTI_AKT_O_ZIGU.empty){
      this.zahtevDTO.opstiAkt=true;
    }
    if(!this.prilogDOKAZ_O_PRAVU_PRVENSTVA.empty){
      this.zahtevDTO.dokazPrvenstvo=true;
    }
    if(!this.prilogPUNOMOCJE.empty){
      this.zahtevDTO.punomocje=true;
    }
  }

  concatenate(elemsToConcatenate:string[]){
    let concatenated = "";

    for (let word of elemsToConcatenate){
      concatenated += "," + word;
    }

    while (concatenated.at(0) === ","){
      console.log(concatenated)
      concatenated = concatenated.slice(1);
    }

    return concatenated;
  }

  podnesiZahtev() {

    // let zahtev = this.servis.createTestZahtev();
    let zahtev = this.zahtevDTO;
    // this.addAlwaysNeededPrilogTypes();
    
    //zahtev.prilozi = this.concatenateNeededPrilogs();
    //zahtev.boje = this.concatenateBoje();

    this.markAttachments();

    this.servis.postZahtev(zahtev).subscribe(data => {
      let brojPrijaveZiga = data.getElementsByTagName("brojPrijaveZiga")[0].textContent;
      console.log(brojPrijaveZiga);
      console.log(this.prilogPUNOMOCJE);
      this.uploadPrilogsForkJoin(brojPrijaveZiga);
    });
  }

  
  uploadPrilogsForkJoin(brojPrijaveZiga:string){
    forkJoin(
      this.uploadPunomocje(brojPrijaveZiga),
      this.uploadPrimerZnaka(brojPrijaveZiga),
      this.uploadOpstiAkt(brojPrijaveZiga),
      this.uploadSpisakRobe(brojPrijaveZiga),
      this.uploadDokazOPrvenstvu(brojPrijaveZiga),
      this.uploadDokazTakse(brojPrijaveZiga)
    ).subscribe(data => {
      console.log("sad jos samo da se sacuva");
      this.servis.saveAfterPrilogAddition(brojPrijaveZiga).subscribe(data => {
        console.log("sacuvali smo");
      });
    });
  }

  uploadPunomocje(brojPrijaveZiga:string){
    let isStatusOkay:boolean = this.statusPunomocja === "NIJE_PREDATO";
    return this.uploadPrilogNoSub("PUNOMOCJE", brojPrijaveZiga, isStatusOkay, this.prilogPUNOMOCJE);
  }

  uploadOpstiAkt(brojPrijaveZiga:string){
    let isStatusOkay:boolean = this.statusPunomocja === "KOLEKTIVNI_ZIG" || this.statusPunomocja === "ZIG_GARANCIJE";
    return  this.uploadPrilogNoSub("OPSTI_AKT_O_ZIGU", brojPrijaveZiga, isStatusOkay, this.prilogOPSTI_AKT_O_ZIGU);
  }

  uploadSpisakRobe(brojPrijaveZiga:string){
    let isStatusOkay:boolean = true;
    return this.uploadPrilogNoSub("SPISAK_ROBE_I_USLUGA", brojPrijaveZiga, isStatusOkay, this.prilogSPISAK_ROBE_I_USLUGA);
  }

  uploadDokazOPrvenstvu(brojPrijaveZiga:string){
    let isStatusOkay:boolean = this.zahtevDTO.pravoPrvenstva == 'KONVENCIJSKO' || this.zahtevDTO.pravoPrvenstva == 'SAJAMSKO';
    return this.uploadPrilogNoSub("DOKAZ_O_PRAVU_PRVENSTVA", brojPrijaveZiga, isStatusOkay, this.prilogDOKAZ_O_PRAVU_PRVENSTVA);
  }

  uploadDokazTakse(brojPrijaveZiga:string){
    let isStatusOkay:boolean = true;
    return  this.uploadPrilogNoSub("DOKAZ_O_UPLATI_TAKSE", brojPrijaveZiga, isStatusOkay, this.prilogDOKAZ_O_UPLATI_TAKSE);
  }

  uploadPrimerZnaka(brojPrijaveZiga:string){
    let isStatusOkay:boolean = true;
    return this.uploadPrilogNoSub("PRIMERAK_ZNAKA", brojPrijaveZiga, isStatusOkay, this.prilogPRIMERAK_ZNAKA);
  }

  uploadPrilog(prilogType:string, brojPrijaveZiga:string, isStatusOkay:boolean, prilogUploadRef:any){
    if ((isStatusOkay) && prilogUploadRef !== null){
      this.servis.postPrilog(brojPrijaveZiga, prilogType, prilogUploadRef).subscribe(data => {
        console.log(data);
        // this.resetEverything();
        prilogUploadRef = null;
      });
    }
  }

  uploadPrilogNoSub(prilogType:string, brojPrijaveZiga:string, isStatusOkay:boolean, prilogUploadRef:any){
    if ((isStatusOkay) && prilogUploadRef !== null){
      return this.servis.postPrilog(brojPrijaveZiga, prilogType, prilogUploadRef);
    } else {
      return this.servis.empty();
    }
  }

  selectPunomocjeUpload(event:any){
    this.prilogPUNOMOCJE = event.target.files[0];
  }

  selectOpstiAktUpload(event:any){
    this.prilogOPSTI_AKT_O_ZIGU = event.target.files[0];
  }

  selectSpisakRobeIUslugaUpload(event:any){
    this.prilogSPISAK_ROBE_I_USLUGA = event.target.files[0];
  }

  selectDokazOPravuPrvenstvaUpload(event:any){
    this.prilogDOKAZ_O_PRAVU_PRVENSTVA = event.target.files[0];
  }

  selectDokazOUplatiTakseUpload(event:any){
    this.prilogDOKAZ_O_UPLATI_TAKSE = event.target.files[0];
  }

  selectPrimerakZnakaUpload(event:any){
    this.prilogPRIMERAK_ZNAKA = event.target.files[0];
  }
}
