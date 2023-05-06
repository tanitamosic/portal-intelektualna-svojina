package xml.p1.P1.model.deljeniTipovi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PravnoLice extends Lice {

    private String naziv_preduzeca;
    private String pib;

    public PravnoLice(Address a, Kontakt k, String naziv_preduzeca, String pib) {
        super(a, k);
        this.naziv_preduzeca = naziv_preduzeca;
        this.pib = pib;
    }

}
