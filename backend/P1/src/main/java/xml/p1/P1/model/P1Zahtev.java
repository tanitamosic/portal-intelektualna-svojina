package xml.p1.P1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xml.p1.P1.dto.P1DTO;
import xml.p1.P1.model.deljeniTipovi.Address;
import xml.p1.P1.model.deljeniTipovi.FizickoLice;
import xml.p1.P1.model.deljeniTipovi.Lice;

import java.text.SimpleDateFormat;
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
