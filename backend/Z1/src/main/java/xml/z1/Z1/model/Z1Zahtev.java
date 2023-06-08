package xml.z1.Z1.model;

import lombok.*;
import org.apache.xerces.dom.DeferredElementNSImpl;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import xml.z1.Z1.dto.Z1DTO;
import xml.z1.Z1.model.deljeniTipovi.FizickoLice;
import xml.z1.Z1.model.deljeniTipovi.Lice;
import xml.z1.Z1.model.deljeniTipovi.PravnoLice;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Z1Zahtev {

    private String brojPrijave;
//     private String datumPrijema;
    private String datumPodnosenja;


    private Lice podnosilac;
    private Lice punomocnik;
    private Lice zajednickiPredstavnik;

    private String vrstaZiga; // MAYBE MAKE THEM ENUM
    private String formatZiga;
    private String izgledZiga;
    private String boje;
    private String opis;
    private String prevod;
    private String transliteracija;
    private String klase;
    private String pravoPrvenstva;


    private Double osnovnaTaksa;
    private Double takseZaKlase;
    private Double takseZaGrafRes;

    private Boolean primerakZnaka;
    private Boolean spisak; // spisak robe i usluga
    private Boolean punomocje;
    private Boolean ranije; // generalno punomocje ranije prilozeno
    private Boolean naknadno; // punomocje ce biti naknadno dosavljeno
    private Boolean opstiAkt; // opsti akto o kolektivnom zigu/zigu garancije
    private Boolean dokazPrvenstvo; // dokaz o pravu prvenstva
    private Boolean dokazTaksa; // dokaz o uplati takse

    public Z1Zahtev(Z1DTO dto){
        this.brojPrijave = generateBrojPrijave();
        this.podnosilac = dto.getPodnosilac();
        this.punomocnik = dto.getPunomocnik();
        this.zajednickiPredstavnik = dto.getZajednickiPredstavnik();

        this.vrstaZiga = dto.getVrstaZiga();  // MAYBE MAKE THEM ENUM
        this.formatZiga = dto.getFormatZiga();
        this.izgledZiga = dto.getIzgledZiga();
        this.boje = dto.getBoje();
        this.opis = dto.getOpis();
        this.prevod = dto.getPrevod();
        this.transliteracija = dto.getTransliteracija();
        this.klase = dto.getKlase();
        this.pravoPrvenstva = dto.getPravoPrvenstva();


        this.osnovnaTaksa = dto.getOsnovnaTaksa();
        this.takseZaKlase = dto.getTakseZaKlase();
        this.takseZaGrafRes = dto.getTakseZaGrafRes();

        this.primerakZnaka = dto.getPrimerakZnaka();
        this.spisak = dto.getSpisak();  // spisak robe i usluga
        this.punomocje = dto.getPunomocje();
        this.ranije = dto.getRanije();  // generalno punomocje ranije prilozeno
        this.naknadno = dto.getNaknadno();  // punomocje ce biti naknadno dosavljeno
        this.opstiAkt = dto.getOpstiAkt();  // opsti akto o kolektivnom zigu/zigu garancije
        this.dokazPrvenstvo = dto.getDokazPrvenstvo();  // dokaz o pravu prvenstva
        this.dokazTaksa = dto.getDokazTaksa();  // dokaz o uplati takse
    }

    public Z1Zahtev(DeferredElementNSImpl document) {
        this.brojPrijave = document.getElementsByTagName("brojPrijave").item(0).getTextContent();
        //this.datumPrijema = document.getElementsByTagName("datumPrijema").item(0).getTextContent();
        this.datumPodnosenja = document.getElementsByTagName("datumPodnosenja").item(0).getTextContent();

        this.podnosilac = null;
        Node podnosilacPrijave = document.getElementsByTagName("podnosilacPrijave").item(0);
        if (podnosilacPrijave.getAttributes().item(1).getTextContent().equals("proj:TFizickoLice")) {
            this.podnosilac = new FizickoLice(podnosilacPrijave);
        } else {
            this.podnosilac = new PravnoLice(podnosilacPrijave);
        }

        Node posrednik = document.getElementsByTagName("posrednik").item(0);
        if (posrednik.getAttributes().item(1).getTextContent().equals("proj:TFizickoLice")) {
            this.podnosilac = new FizickoLice(posrednik);
        } else {
            this.podnosilac = new PravnoLice(posrednik);
        }

        NodeList prijave = document.getElementsByTagName("ranijaPrijava");
        for (int idx = 0; idx < prijave.getLength(); idx++) {
            Node prijava = prijave.item(idx);
            NodeList podaciRanijePrijave = prijava.getChildNodes();
            RanijaPrijava rp = new RanijaPrijava(podaciRanijePrijave);
            //this.ranijePrijave.add(rp);
        }

    }

    private String generateBrojPrijave() {
        String retval = "Ž-2023-";
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
        return "Ž-" + brojPrijave.substring(2, 6) + "-" + brojPrijave.substring(7, 11);
    }

}
