package xml.z1.Z1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class XmlDto {
    private String text;

    public XmlDto(String text) {
        this.text = text;
    }

    public XmlDto() {
    }
}
