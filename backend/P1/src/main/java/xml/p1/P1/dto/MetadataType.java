package xml.p1.P1.dto;

public enum MetadataType {
    VRSTA_PRIJAVE("src/main/resources/data/sparql/vrsta_prijave.rq"),
    EMAIL_PRONALAZACA("src/main/resources/data/sparql/email_pronalazaca.rq"),
    EMAIL_PODNOSIOCA("src/main/resources/data/sparql/email_podnosioca.rq"),
    EMAIL_POSREDNIKA("src/main/resources/data/sparql/email_posrednika.rq");
    private final String value;

    MetadataType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
