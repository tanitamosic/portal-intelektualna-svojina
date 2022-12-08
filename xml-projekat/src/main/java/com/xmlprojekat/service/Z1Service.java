package com.xmlprojekat.service;

import com.xmlprojekat.dom.DOMParser;
import com.xmlprojekat.dto.XMLDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDateTime;

@Service
public class Z1Service extends XMLService {

    private Integer id = 1;
    private final DOMParser domParser;

    public Z1Service(DOMParser domParser) {
        this.domParser = domParser;
    }

    public String applyZavod(XMLDto dto) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        Document document = domParser.buildDocumentFromText(dto.getText());
        Element zavod = (Element) document.getElementsByTagName("zavod").item(0);

        Element brojPrijave = (Element) zavod.getElementsByTagName("broj_prijave").item(0);
        brojPrijave.setTextContent(generateApplicationId());

        Element datum = (Element) zavod.getElementsByTagName("datum_podnosenja").item(0);
        setDateInDocument(datum);

        return domParser.getDocumentAsString(document);
    }


    protected String generateApplicationId() {
        String num = String.format("%04d",id);
        String year = String.valueOf(LocalDateTime.now().getYear());

        return "Ж " + year + "/" + num ;
    }
}
