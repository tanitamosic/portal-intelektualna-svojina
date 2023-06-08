package xml.a1.A1.model.deljeniTipovi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.Node;

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

    public PravnoLice(Node podnosilacPrijave) {
        super(podnosilacPrijave.getChildNodes());
        this.pib = podnosilacPrijave.getChildNodes().item(2).getTextContent();
        this.naziv_preduzeca = podnosilacPrijave.getChildNodes().item(3).getTextContent();
    }

}
