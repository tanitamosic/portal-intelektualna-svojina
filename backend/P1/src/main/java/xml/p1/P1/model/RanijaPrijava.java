package xml.p1.P1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.NodeList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RanijaPrijava {

    private String datum;
    private String brojPrijave;
    private String drzavaIliOrganizacija;

    public RanijaPrijava(NodeList podaciRanijePrijave) {
        this.datum = podaciRanijePrijave.item(0).getTextContent();
        this.brojPrijave = podaciRanijePrijave.item(1).getTextContent();
        this.drzavaIliOrganizacija = podaciRanijePrijave.item(2).getTextContent();
    }
}
