package xml.z1.Z1.dto;

import xml.z1.Z1.model.Address;
import xml.z1.Z1.model.Lice;
import xml.z1.Z1.model.RanijaPrijava;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Z1DTO {

    private String srpskiNazivPronalaska;
    private String engleskiNazivPronalaska;

    private Boolean jePronalazac;
    private Lice podnosilacPrijave;
    private Boolean ostaliPodnosioci;

    private Lice pronalazac;
    private String ostaliPronalazaci;

    private String vrstaPosrednika; // MAYBE MAKE IT ENUM
    private Lice posrednik;
    private Address adresaZaDostavljanje;
    private String nacinDostavljanja; // MAYBE ENUM

    private String vrstaPrijave; //MAYBE ENUm
    private String brojPrvobitnePrijave;
    private String datumPodnosenjaPrvobitnePrijave;

    private List<RanijaPrijava> ranijePrijave;

    private String dodatniList;
}
