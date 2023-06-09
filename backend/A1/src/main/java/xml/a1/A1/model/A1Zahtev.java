package xml.a1.A1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.xerces.dom.DeferredElementNSImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import xml.a1.A1.dto.A1DTO;
import xml.a1.A1.model.deljeniTipovi.Address;
import xml.a1.A1.model.deljeniTipovi.FizickoLice;
import xml.a1.A1.model.deljeniTipovi.Lice;
import xml.a1.A1.model.deljeniTipovi.PravnoLice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class A1Zahtev {



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

    private String datum_podnosenja_zahteva;

    private String sifra;

    public A1Zahtev(A1DTO dto) {
        this.podnosilac_zahteva = dto.getPodnosilac_zahteva();
        this.pseudonim_podnosioca = dto.getPseudonim_podnosioca();
        this.punomocnik = dto.getPunomocnik();
        this.naslov_dela = dto.getNaslov_dela();
        this.podaci_o_naslovu_izvonog_dela_naslov = dto.getPodaci_o_naslovu_izvonog_dela_naslov();
        this.podaci_o_naslovu_izvonog_dela_autori = dto.getPodaci_o_naslovu_izvonog_dela_autori();
        this.vrsta_dela = dto.getVrsta_dela();
        this.forma_dela = dto.getForma_dela();
        this.autori = dto.getAutori();
        this.delo_stvoreno_u_radnom_odnosu = dto.getDelo_stvoreno_u_radnom_odnosu();
        this.nacin_koriscenja_dela = dto.getNacin_koriscenja_dela();
        this.prilozi_uz_zahtev_opis_dela = dto.getPrilozi_uz_zahtev_opis_dela();
        this.prilozi_uz_zahtev_format_primera = dto.getPrilozi_uz_zahtev_format_primera();
        this.prilozi_uz_zahtev_naziv_fajla = dto.getPrilozi_uz_zahtev_naziv_fajla();
        this.datum_podnosenja_zahteva = dto.getDatum_podnosenja_zahteva();
        this.sifra = dto.getSifra();
    }

    public A1Zahtev(DeferredElementNSImpl document) {

        this.pseudonim_podnosioca = document.getElementsByTagName("pseudonim").item(0).getTextContent();
        this.naslov_dela = document.getElementsByTagName("naslov_dela").item(0).getTextContent();
        this.podaci_o_naslovu_izvonog_dela_naslov = document.getElementsByTagName("podaci_o_naslovu_izvonog_dela").item(0).getChildNodes().item(0).getTextContent();
        this.vrsta_dela = document.getElementsByTagName("vrsta_dela").item(0).getTextContent();
        this.forma_dela = document.getElementsByTagName("forma_dela").item(0).getTextContent();
        this.delo_stvoreno_u_radnom_odnosu = Boolean.valueOf(document.getElementsByTagName("delo_stvoreno_u_radnom_odnosu").item(0).getTextContent());
        this.nacin_koriscenja_dela = document.getElementsByTagName("nacin_koriscenja_dela").item(0).getTextContent();
        this.prilozi_uz_zahtev_opis_dela = document.getElementsByTagName("prilozi_uz_zahtev").item(0).getChildNodes().item(0).getTextContent();
        this.prilozi_uz_zahtev_format_primera = document.getElementsByTagName("prilozi_uz_zahtev").item(0).getChildNodes().item(1).getTextContent();
        this.prilozi_uz_zahtev_naziv_fajla = document.getElementsByTagName("prilozi_uz_zahtev").item(0).getChildNodes().item(2).getTextContent();
        this.datum_podnosenja_zahteva = document.getElementsByTagName("datum_podnosenja_zahteva").item(0).getTextContent();
        this.sifra = document.getElementsByTagName("sifra").item(0).getTextContent();


        this.podnosilac_zahteva = null;
        Node podnosilac_zahteva = document.getElementsByTagName("podnosilac_zahteva").item(0).getChildNodes().item(0);
        if (podnosilac_zahteva.getAttributes().item(1).getTextContent().equals("proj:TFizickoLice")) {
            this.podnosilac_zahteva = new FizickoLice(podnosilac_zahteva);
        } else {
            this.podnosilac_zahteva = new PravnoLice(podnosilac_zahteva);
        }

        this.punomocnik = null;
        Node punomocnik = document.getElementsByTagName("punomocnik").item(0).getChildNodes().item(0);
        if (punomocnik.getAttributes().item(1).getTextContent().equals("proj:TFizickoLice")) {
            this.punomocnik = new FizickoLice(punomocnik);
        } else {
            this.punomocnik = new PravnoLice(punomocnik);
        }

        this.autori = new ArrayList<>();
        NodeList podaci_o_autoru = document.getElementsByTagName("podaci_o_autoru").item(0).getChildNodes();
        for (int i = 0; i < podaci_o_autoru.getLength(); i++) {
            Node autor = podaci_o_autoru.item(i);
            autori.add(new Autor(autor));
        }

        this.podaci_o_naslovu_izvonog_dela_autori = new ArrayList<>();
        NodeList izvorno_delo_autori = document.getElementsByTagName("podaci_o_naslovu_izvonog_dela").item(0).getChildNodes();
        for (int i=1; i < izvorno_delo_autori.getLength(); i++) {
            Node autor = izvorno_delo_autori.item(i);
            podaci_o_naslovu_izvonog_dela_autori.add(new Autor(autor));
        }
    }

    private String generateBrojPrijave() {
        String retval = "A-2023-";
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int randomInt = rand.nextInt(10);
            sb.append(randomInt);
        }
        retval = retval.concat(sb.toString());
        return retval;
    }

    private String extractDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String dateString = dateFormat.format(date);
        return dateString;
    }



}
