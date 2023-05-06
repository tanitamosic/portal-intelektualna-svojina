package xml.p1.P1.model.deljeniTipovi;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xml.p1.P1.model.deserializer.LiceDeserializer;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = LiceDeserializer.class)
public abstract class Lice {

    private Address adresa;
    private Kontakt kontakt;
}
