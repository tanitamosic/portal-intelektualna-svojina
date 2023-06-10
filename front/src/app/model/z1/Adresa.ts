export class Adresa {
  ulica: string  = "";
  broj: string = ""; // 
  postanskiBroj: string = "";
  mesto: string  = "";

  public isValid(): boolean {
    return this.ulica.length > 0 &&
      this.broj.length > 0 &&
      this.postanskiBroj.length > 0 &&
      this.mesto.length > 0;
  }

  public Adresa(){
    this.ulica = "";
    this.broj = "";
    this.postanskiBroj = "";
    this.mesto = "";
  }
}
