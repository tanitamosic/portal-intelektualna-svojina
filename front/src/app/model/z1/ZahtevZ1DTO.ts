import { Lice } from "./Lice";

export class NeededAttachments {
  neededAttachments = [];

  public NeededAttachments(){
    this.neededAttachments = [];
  }
}

export class ZahtevZ1DTO {
  podnosilac: Lice = new Lice();
  punomocnik: Lice = new Lice();
  predstavnik: Lice = new Lice();

  vrstaZiga = "";
  formatZiga = "";
  izgledZiga = "";
  boje = "";
  opis = "";
  prevod = "";
  transliteracija = "";
  klase = ""
  pravoPrvenstva = "";

  osnovnaTaksa: number = 0;
  takseZaKlase: number = 0;
  takseZaGrafRes: number = 0;

  primerakZnaka: boolean = false;
  spisak: boolean = false;
  punomocje: boolean = false;
  ranije: boolean = false;
  naknadno: boolean = false;
  opstiAkt: boolean = false;
  dokazPrvenstvo: boolean = false;
  dokazTaksa: boolean = false;

  //statusPrilogPunomocje = "";
  //prilozi = "";

  public ZahtevZaPriznanjeZigaDTO(){
    this.podnosilac = new Lice();
    this.punomocnik = new Lice();
    this.predstavnik = new Lice();

    this.vrstaZiga = "";
    this.formatZiga = "";
    this.izgledZiga = "";
    this.boje = "";
    this.opis = "";
    this.prevod = "";
    this.transliteracija = "";

    this.pravoPrvenstva = "";
        
    this.primerakZnaka= false;
    this.spisak= false;
    this.punomocje= false;
    this.ranije= false;
    this.naknadno= false;
    this.opstiAkt= false;
    this.dokazPrvenstvo= false;
    this.dokazTaksa= false;
    this.klase = "";
    //this.prilozi = "";
  }
}
