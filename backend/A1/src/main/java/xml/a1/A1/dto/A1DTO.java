package xml.a1.A1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xml.a1.A1.model.deljeniTipovi.Address;
import xml.a1.A1.model.deljeniTipovi.Lice;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class A1DTO {

    private String srpskiNazivPronalaska;
    private String engleskiNazivPronalaska;

    private Boolean podnosilacJePronalazac;
    private Lice podnosilacPrijave;
    private Boolean ostaliPodnosioci;

    private Lice pronalazac;
    //    private String ostaliPronalazaci;
    private Boolean pronalazacZeliBitiNaveden;
    private String vrstaPosrednika; // MAYBE MAKE IT ENUM
    private Lice posrednik;
    private Address adresaZaDostavljanje;
    private String nacinDostavljanja; // MAYBE ENUM

    private String vrstaPrijave; //MAYBE ENUm
    private String brojPrvobitnePrijave;
    private String datumPodnosenjaPrvobitnePrijave;

    private Boolean imaDodatnogLista;
    private String dodatniList;
}
