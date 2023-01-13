package com.kts.service;

import com.kts.fuseki.FusekiWriter;
import com.kts.fuseki.MetadataExtractor;
import org.springframework.stereotype.Service;

import javax.xml.transform.TransformerException;
import java.io.*;


@Service
public class XMLService {


    private final MetadataExtractor metadataExtractor;



    public XMLService(MetadataExtractor metadataExtractor ) {
        this.metadataExtractor = metadataExtractor;
    }

    public void storeXML(InputStream input_xml, OutputStream output_rdf) throws IOException, TransformerException {
        metadataExtractor.extractMetadata(input_xml, output_rdf);
        FusekiWriter.saveRDF();

    }

}
