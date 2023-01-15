package com.kts.service;

import com.kts.fuseki.FusekiReader;
import com.kts.fuseki.FusekiWriter;
import com.kts.fuseki.MetadataExtractor;
import org.springframework.stereotype.Service;

import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Service
public class XMLService {


    private final MetadataExtractor metadataExtractor;



    public XMLService(MetadataExtractor metadataExtractor ) {
        this.metadataExtractor = metadataExtractor;
    }

    public ArrayList<String> storeXML(InputStream input_xml, OutputStream output_rdf) throws IOException, TransformerException {
        metadataExtractor.extractMetadata(input_xml, output_rdf);
        FusekiWriter.saveRDF();
        Map<String, String> params = new HashMap<>();
        params.put("<%naziv_dela>", "\"How to scam people\"");
        return FusekiReader.executeQuery(params);
    }

}
