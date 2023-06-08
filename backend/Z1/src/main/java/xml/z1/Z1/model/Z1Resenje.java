package xml.z1.Z1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Z1Resenje {

    private String brojPrijave;
    private String imeSluzbenika;
    private String prezimeSluzbenika;
    private String emailSluzbenika;
    private String datumObrade;
    private String razlogOdbijanja = "";
    private String sifra;
    private Boolean odbijen;

    public String getTitle() {
        return "Resenje-".concat(brojPrijave.replace('\\', '-'));
    }

}
