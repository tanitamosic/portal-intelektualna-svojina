package xml.a1.A1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xml.a1.A1.model.Autor;
import xml.a1.A1.model.deljeniTipovi.Address;
import xml.a1.A1.model.deljeniTipovi.Lice;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class A1DTO {


    private Lice podnosilac_zahteva;

    private String pseudonim_podnosioca;

    private Lice punomocnik;

    private String naslov_dela;

    private String podaci_o_naslovu_izvonog_dela_naslov;

    private ArrayList<Autor> podaci_o_naslovu_izvonog_dela_autori;

    private String vrsta_dela;

    private String forma_dela;

    private ArrayList<Autor> autori;

    private Boolean delo_stvoreno_u_radnom_odnosu;

    private String nacin_koriscenja_dela;

    private String prilozi_uz_zahtev_opis_dela;

    private String prilozi_uz_zahtev_format_primera;

    private String prilozi_uz_zahtev_naziv_fajla;

//    private String datum_podnosenja_zahteva;

//    private String sifra;


}
