package xml.p1.P1.model.deljeniTipovi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
