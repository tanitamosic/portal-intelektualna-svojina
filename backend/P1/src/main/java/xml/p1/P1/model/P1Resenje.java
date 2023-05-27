package xml.p1.P1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class P1Resenje {

    private String brojPrijave;
    private String imeSluzbenika;
    private String prezimeSluzbenika;
    private String emailSluzbenika;
    private String datumObrade;
    private String razlogOdbijanja = "";
    private Boolean odbijen;

    public String getTitle() {
        return "Resenje-".concat(brojPrijave.replace('\\', '-'));
    }

}
