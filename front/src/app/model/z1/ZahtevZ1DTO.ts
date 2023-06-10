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

  statusPrilogPunomocje = "";
  prilozi = "";

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
    this.statusPrilogPunomocje = "";
    this.klase = "";
    this.prilozi = "";
  }
}
