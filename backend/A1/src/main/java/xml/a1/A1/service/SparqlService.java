package xml.a1.A1.service;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xml.a1.A1.dto.MetadataType;
import xml.a1.A1.fuseki.FusekiReader;
import xml.a1.A1.fuseki.FusekiWriter;
import xml.a1.A1.fuseki.MetadataExtractor;

import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

@Service
public class SparqlService {
    @Autowired
    MetadataExtractor metadataExtractor;
    @Autowired
    FusekiReader fusekiReader;

    public void saveRDF(String xml, String output_rdf) throws IOException, TransformerException {
        InputStream fis = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
        FileOutputStream fos = new FileOutputStream(output_rdf);
        metadataExtractor.extractMetadata(fis, fos);
        FusekiWriter.saveRDF(output_rdf);
    }

    public String loadRDF(String rdf_name) throws IOException {
        InputStream in = new FileInputStream(rdf_name);
        return IOUtils.toString(in, StandardCharsets.UTF_8);
    }

    public List<String> search(String searchParam, MetadataType metadataType) throws IOException {
        HashMap<String, String> params = new HashMap<>();
        params.put("<%param>", searchParam);
        List<String> results = FusekiReader.executeQuery(params, metadataType.getValue());
        // element liste izgleda ovako: prijave: P-2023-1234
        // slicujem prvih 9 elemenata, da bi dobio samo broj prijave nazad.
        results = results.stream()
                .map(e -> e.substring(7)).toList();
        return results;
    }
}
