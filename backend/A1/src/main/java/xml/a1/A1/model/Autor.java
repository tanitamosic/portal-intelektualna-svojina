package xml.a1.A1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.Node;
import xml.a1.A1.model.deljeniTipovi.Address;
import xml.a1.A1.model.deljeniTipovi.FizickoLice;
import xml.a1.A1.model.deljeniTipovi.Kontakt;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Autor extends FizickoLice {

    private Integer godina_smrti;

    public Autor(Address a, Kontakt k, String ime, String prezime, int godina_smrti) {
        super(a, k, ime, prezime);
        this.godina_smrti = godina_smrti;
    }

    public Autor(Node lice) {
        super((Node) lice.getChildNodes());
        this.godina_smrti = Integer.parseInt(lice.getChildNodes().item(2).getTextContent());
    }

}
