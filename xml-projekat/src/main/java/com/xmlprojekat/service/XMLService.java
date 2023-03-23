package com.xmlprojekat.service;

import com.xmlprojekat.dom.DOMParser;
import com.xmlprojekat.dto.XMLDto;
import com.xmlprojekat.fuseki.FusekiReader;
import com.xmlprojekat.fuseki.FusekiWriter;
import com.xmlprojekat.fuseki.MetadataExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class XMLService {

    private DOMParser domParser;

    private MetadataExtractor metadataExtractor;

    Integer id;

    public XMLService(DOMParser domParser){
        this.domParser = domParser;
    }
    public XMLService(MetadataExtractor metadataExtractor ) {
        this.metadataExtractor = metadataExtractor;
    }

    public XMLService(){}


    protected void setDateInDocument(Element datum) {
        NodeList datumParts = datum.getChildNodes();
        String[] dateStrings = getDate();
        for (int i = 0; i<datumParts.getLength(); ++i){
            Node item = datumParts.item(i);
            switch (item.getNodeName()){
                case "dan":
                    item.setTextContent(dateStrings[0]);
                    break;
                case "mesec":
                    item.setTextContent(dateStrings[1]);
                    break;
                case "godina":
                    item.setTextContent(dateStrings[2]);
                    break;
            }
        }
    }

    private String[] getDate() {
        String[] ret = new String[3];
        LocalDateTime date = LocalDateTime.now();
        ret[0] = String.valueOf(date.getDayOfMonth());
        ret[1] = String.valueOf(date.getMonthValue());
        ret[2] = String.valueOf(date.getYear());
        return ret;
    }
    
    public ArrayList<String> storeXML(InputStream input_xml, OutputStream output_rdf) throws IOException, TransformerException {
        metadataExtractor.extractMetadata(input_xml, output_rdf);
        FusekiWriter.saveRDF();
        Map<String, String> params = new HashMap<>();
        params.put("<%naziv_dela>", "\"How to scam people\"");
        return FusekiReader.executeQuery(params);
    }


    public String applyZavod(XMLDto dto) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        Document document = domParser.buildDocumentFromText(dto.getText());
        Element zavod = (Element) document.getElementsByTagName("forma_za_zavod").item(0);

        Element brojPrijave = (Element) zavod.getElementsByTagName("broj_prijave").item(0);
        brojPrijave.setTextContent(generateApplicationId());

        Element datum = (Element) zavod.getElementsByTagName("datum_podnosenja").item(0);
        setDateInDocument(datum);

        return domParser.getDocumentAsString(document);
    }


    protected String generateApplicationId() {
        Random r = new Random();
        String year = String.valueOf(r.nextInt(30) + 1990);
        return "П-" + year + "/" + id;
    }

}
