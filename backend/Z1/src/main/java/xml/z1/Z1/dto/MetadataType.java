package xml.z1.Z1.dto;

public enum MetadataType {
    //TODO ubaci svoje abouts
    EMAIL_ZAJEDNICKOG_PREDSTAVNIKA("src/main/resources/data/sparql/email_predstavnika.rq"),
    EMAIL_PODNOSIOCA("src/main/resources/data/sparql/email_podnosioca.rq"),
    EMAIL_PUNOMOCNIKA("src/main/resources/data/sparql/email_punomocnika.rq"),
    PODACI_O_ZIGU("src/main/resources/data/sparql/podaci_o_zigu.rq");
    private final String value;

    MetadataType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
