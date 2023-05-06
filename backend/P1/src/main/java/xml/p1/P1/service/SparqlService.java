package xml.p1.P1.service;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xml.p1.P1.fuseki.FusekiWriter;
import xml.p1.P1.fuseki.MetadataExtractor;

import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
public class SparqlService {

    @Autowired
    MetadataExtractor metadataExtractor;

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

    public void search() {

    }
}
