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
public class Kontakt {

    private String telefon;
    private String email;
    private String faks;

    public Kontakt(Node item) {
        NodeList podaci = item.getChildNodes();
        this.email = podaci.item(0).getTextContent();
        this.telefon = podaci.item(1).getTextContent();
        this.faks = podaci.item(2).getTextContent();
    }
}
