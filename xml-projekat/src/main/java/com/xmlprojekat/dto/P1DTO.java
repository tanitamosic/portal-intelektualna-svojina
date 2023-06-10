package com.xmlprojekat.dto;

import com.xmlprojekat.model.Address;
import com.xmlprojekat.model.Lice;
import com.xmlprojekat.model.RanijaPrijava;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class P1DTO {

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
