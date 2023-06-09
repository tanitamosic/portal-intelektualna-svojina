package xml.p1.P1.service;

import org.apache.commons.io.IOUtils;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xml.p1.P1.dto.MetadataType;
import xml.p1.P1.fuseki.FusekiReader;
import xml.p1.P1.fuseki.FusekiWriter;
import xml.p1.P1.fuseki.MetadataExtractor;

import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SparqlService {

    private static final HashMap<MetadataType, String> rqParams = new HashMap<>() {{
        put(MetadataType.VRSTA_PRIJAVE, "<%vrsta_prijave>");
        put(MetadataType.EMAIL_PRONALAZACA, "<%email>");
        put(MetadataType.EMAIL_PODNOSIOCA, "<%email>");
        put(MetadataType.EMAIL_POSREDNIKA, "<%email>");
    }};
    @Autowired
    MetadataExtractor metadataExtractor;
    @Autowired
    FusekiReader fusekiReader;

    public void saveRDF(String xml, String output_rdf, String name) throws IOException, TransformerException {
        InputStream fis = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
        FileOutputStream fos = new FileOutputStream(output_rdf);
        metadataExtractor.extractMetadata(fis, fos);
        FusekiWriter.saveRDF(output_rdf, name);
    }

    public String loadRDF(String rdf_name) throws IOException {
        InputStream in = new FileInputStream(rdf_name);
        return IOUtils.toString(in, StandardCharsets.UTF_8);
    }

    public List<String> search(String searchParam, MetadataType metadataType) throws IOException {
        HashMap<String, String> params = new HashMap<>();
        params.put(rqParams.get(metadataType), searchParam);
        List<String> results = FusekiReader.executeQuery(params, metadataType.getValue());
        // element liste izgleda ovako: prijave: P-2023-1234
        // slicujem prvih 9 elemenata, da bi dobio samo broj prijave nazad.
        results = results.stream()
                .map(e -> e.substring(9)).toList();
        return results;
    }
}
