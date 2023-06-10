package xml.z1.Z1.model.deljeniTipovi;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.NodeList;
import xml.z1.Z1.model.deserializer.LiceDeserializer;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = LiceDeserializer.class)
public abstract class Lice {

    private Address adresa;
    private Kontakt kontakt;

    public Lice(NodeList lice) {
        this.adresa = new Address(lice.item(2));
        this.kontakt = new Kontakt(lice.item(3));
    }
}
