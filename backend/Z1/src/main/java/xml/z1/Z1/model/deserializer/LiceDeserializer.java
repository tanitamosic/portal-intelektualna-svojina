package xml.z1.Z1.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import xml.z1.Z1.model.deljeniTipovi.*;

import java.io.IOException;

public class LiceDeserializer extends JsonDeserializer<Lice> {

    @Override
    public Lice deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);

        ObjectMapper objectMapper = new ObjectMapper();
        String adresa = node.get("adresa").toString();
        String kontakt = node.get("kontakt").toString();
        Address address = objectMapper.readValue(adresa, Address.class);
        Kontakt contact = objectMapper.readValue(kontakt, Kontakt.class);

        // Check if the node contains the "naziv_preduzeca" field
        JsonNode nazivNode = node.get("naziv_preduzeca");
        if (nazivNode != null) {
            String naziv = nazivNode.asText();
            String pib = node.get("pib").asText();
            return new PravnoLice(address, contact, naziv, pib);
        }

        // Check if the node contains the "ime" field
        JsonNode imeNode = node.get("ime");
        if (imeNode != null) {
            String ime = imeNode.asText();
            String prezime = node.get("prezime").asText();

            return new FizickoLice(address, contact, ime, prezime);
        }

        // If neither of the above fields are present, throw an exception
        throw new RuntimeException("Invalid JSON for Lice");
    }
}

