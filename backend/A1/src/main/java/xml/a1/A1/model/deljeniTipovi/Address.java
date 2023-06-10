package xml.a1.A1.model.deljeniTipovi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String ulica;
    private String broj;
    private String mesto;
    private String postanski_broj;

    public Address(Node item) {
        NodeList podaci = item.getChildNodes();
        this.mesto = podaci.item(0).getTextContent();
        this.postanski_broj = podaci.item(1).getTextContent();
        this.ulica = podaci.item(2).getTextContent();
        this.broj = podaci.item(3).getTextContent();

    }
}
