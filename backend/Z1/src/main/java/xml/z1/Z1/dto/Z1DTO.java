package xml.z1.Z1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xml.z1.Z1.model.deljeniTipovi.Lice;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Z1DTO {


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

    //private String prilozi;

    private Boolean primerakZnaka;
    private Boolean spisak; // spisak robe i usluga
    private Boolean punomocje;
    private Boolean ranije; // generalno punomocje ranije prilozeno
    private Boolean naknadno; // punomocje ce biti naknadno dosavljeno
    private Boolean opstiAkt; // opsti akto o kolektivnom zigu/zigu garancije
    private Boolean dokazPrvenstvo; // dokaz o pravu prvenstva
    private Boolean dokazTaksa; // dokaz o uplati takse

}
