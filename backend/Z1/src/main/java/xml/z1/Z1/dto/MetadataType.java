package xml.z1.Z1.dto;

public enum MetadataType {
    //TODO ubaci svoje abouts
    EMAIL_ZAJEDNICKOG_PREDSTAVNIKA("src/main/resources/data/sparql/email_zajednickog_predstavnika.rq"),
    EMAIL_PODNOSIOCA("src/main/resources/data/sparql/email_podnosioca.rq"),
    EMAIL_PUNOMOCNIKA("src/main/resources/data/sparql/email_punomocnika.rq");
    private final String value;

    MetadataType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
