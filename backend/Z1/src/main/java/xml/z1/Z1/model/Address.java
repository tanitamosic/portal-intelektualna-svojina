package xml.z1.Z1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String ulica;
    private String broj;
    private String mesto;
    private String postanskiBroj;
}
