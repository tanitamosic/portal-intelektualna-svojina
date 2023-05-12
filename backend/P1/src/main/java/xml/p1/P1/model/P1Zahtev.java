package xml.p1.P1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.xerces.dom.DeferredElementNSImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import xml.p1.P1.dto.P1DTO;
import xml.p1.P1.model.deljeniTipovi.Address;
import xml.p1.P1.model.deljeniTipovi.FizickoLice;
import xml.p1.P1.model.deljeniTipovi.Lice;
import xml.p1.P1.model.deljeniTipovi.PravnoLice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class P1Zahtev {

    private String brojPrijave;
    private String datumPrijema;
    private String datumPodnosenja;

    private String srpskiNazivPronalaska;
    private String engleskiNazivPronalaska;
    private Boolean podnosilacJePronalazac;
    private Lice podnosilacPrijave;
//    private Boolean ostaliPodnosioci;
    private FizickoLice pronalazac;
//    private String ostaliPronalazaci;
    private Boolean pronalazacZeliBitiNaveden;
    private String vrstaPosrednika; // MAYBE MAKE IT ENUM
    private Lice posrednik;
    private Address adresaZaDostavljanje;
    private String nacinDostavljanja; // MAYBE ENUM
    private String vrstaPrijave; //MAYBE ENUm
    private String brojPrvobitnePrijave;
    private String datumPodnosenjaPrvobitnePrijave;
    private List<RanijaPrijava> ranijePrijave;
    private Boolean imaDodatnogLista;
    private String dodatniList;

    public P1Zahtev(P1DTO dto) {
        this.brojPrijave = generateBrojPrijave();
        this.datumPrijema = extractDate(new Date());
        this.datumPodnosenja = extractDate(new Date());

        this.srpskiNazivPronalaska = dto.getSrpskiNazivPronalaska();
        this.engleskiNazivPronalaska = dto.getEngleskiNazivPronalaska();
        this.podnosilacJePronalazac = dto.getPodnosilacJePronalazac();
        this.podnosilacPrijave = dto.getPodnosilacPrijave();
//        this.ostaliPodnosioci = dto.getOstaliPodnosioci();
        this.pronalazac = (FizickoLice) dto.getPronalazac();
//        this.ostaliPronalazaci = dto.getOstaliPronalazaci();
        this.pronalazacZeliBitiNaveden = dto.getPronalazacZeliBitiNaveden();
        this.vrstaPosrednika = dto.getVrstaPosrednika();
        this.posrednik = dto.getPosrednik();
        this.adresaZaDostavljanje = dto.getAdresaZaDostavljanje();
        this.nacinDostavljanja = dto.getNacinDostavljanja();
        this.vrstaPrijave = dto.getVrstaPrijave();
        this.brojPrvobitnePrijave = dto.getBrojPrvobitnePrijave();
        this.datumPodnosenjaPrvobitnePrijave = dto.getDatumPodnosenjaPrvobitnePrijave();
        this.ranijePrijave = dto.getRanijePrijave();
        this.imaDodatnogLista = dto.getImaDodatnogLista();
        this.dodatniList = dto.getDodatniList();
    }

    public P1Zahtev(DeferredElementNSImpl document) {
        this.brojPrijave = document.getElementsByTagName("brojPrijave").item(0).getTextContent();
        this.datumPrijema = document.getElementsByTagName("datumPrijema").item(0).getTextContent();
        this.datumPodnosenja = document.getElementsByTagName("datumPodnosenja").item(0).getTextContent();

        this.srpskiNazivPronalaska = document.getElementsByTagName("srpski").item(0).getTextContent();
        this.engleskiNazivPronalaska = document.getElementsByTagName("engleski").item(0).getTextContent();
        this.podnosilacJePronalazac = Boolean.valueOf(document.getElementsByTagName("jePronalazac").item(0).getTextContent());


        this.podnosilacPrijave = null;
        Node podnosilacPrijave = document.getElementsByTagName("podnosilacPrijave").item(0);
        if (podnosilacPrijave.getAttributes().item(1).getTextContent().equals("proj:TFizickoLice")) {
            this.podnosilacPrijave = new FizickoLice(podnosilacPrijave);
        } else {
            this.podnosilacPrijave = new PravnoLice(podnosilacPrijave);
        }


//        this.ostaliPodnosioci = dto.getOstaliPodnosioci();
        this.pronalazac = new FizickoLice(document.getElementsByTagName("pronalazac").item(0));
//        this.ostaliPronalazaci = dto.getOstaliPronalazaci();
        this.pronalazacZeliBitiNaveden = Boolean.valueOf(document.getElementsByTagName("zeliBitiNaveden").item(0).getTextContent());
        this.vrstaPosrednika = document.getElementsByTagName("vrstaPosrednika").item(0).getTextContent();

        this.posrednik = null;
        Node posrednik = document.getElementsByTagName("posrednik").item(0);
        if (posrednik.getAttributes().item(1).getTextContent().equals("proj:TFizickoLice")) {
            this.podnosilacPrijave = new FizickoLice(posrednik);
        } else {
            this.podnosilacPrijave = new PravnoLice(posrednik);
        }
        this.adresaZaDostavljanje = new Address(document.getElementsByTagName("adresaZaDostavljanje").item(0).getChildNodes().item(0));
        this.nacinDostavljanja = document.getElementsByTagName("nacinDostavljanja").item(0).getTextContent();
        this.vrstaPrijave = document.getElementsByTagName("vrstaPrijave").item(0).getTextContent();
        this.brojPrvobitnePrijave = document.getElementsByTagName("brojPrvobitnePrijave").item(0).getTextContent();
        this.datumPodnosenjaPrvobitnePrijave = document.getElementsByTagName("datumPodnosenjaPrvobitnePrijave").item(0).getTextContent();
        this.ranijePrijave = new ArrayList<>();
        NodeList prijave = document.getElementsByTagName("ranijaPrijava");
        for (int idx = 0; idx < prijave.getLength(); idx++) {
            Node prijava = prijave.item(idx);
            NodeList podaciRanijePrijave = prijava.getChildNodes();
            RanijaPrijava rp = new RanijaPrijava(podaciRanijePrijave);
            this.ranijePrijave.add(rp);
        }
        this.imaDodatnogLista = Boolean.valueOf(document.getElementsByTagName("podaciOOstalimPravimaNaListu2").item(0).getTextContent());
        this.dodatniList = document.getElementsByTagName("dodatniList2").item(0).getTextContent();
    }

    private String generateBrojPrijave() {
        String retval = "P-2023\\";
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

    public String getNameForCollection() {
        return "P" + brojPrijave.substring(2, 6) + "-" + brojPrijave.substring(7, 11);
    }

}
