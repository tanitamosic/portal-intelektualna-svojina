package xml.p1.P1.model.deljeniTipovi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.Node;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FizickoLice extends Lice {

    private String ime;
    private String prezime;

    public FizickoLice(Address a, Kontakt k, String ime, String prezime) {
        super(a, k);
        this.ime = ime;
        this.prezime = prezime;
    }

    public FizickoLice(Node lice) {
        super(lice.getChildNodes());
        this.ime = lice.getChildNodes().item(0).getTextContent();
        this.prezime = lice.getChildNodes().item(1).getTextContent();
    }
}
