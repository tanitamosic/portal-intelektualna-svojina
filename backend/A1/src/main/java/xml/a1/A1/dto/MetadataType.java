package xml.a1.A1.dto;

public enum MetadataType {
    VRSTA_DELA("src/main/resources/data/sparql/vrsta_dela.rq"),
    FORMA_DELA("src/main/resources/data/sparql/forma_dela.rq"),
    EMAIL_PODNOSIOCA("src/main/resources/data/sparql/email_podnosioca.rq"),
    PSEUDONIM("src/main/resources/data/sparql/pseudonim.rq");
    private final String value;

    MetadataType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
