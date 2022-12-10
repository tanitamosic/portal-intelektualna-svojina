package com.xmlprojekat.service;

import com.xmlprojekat.dom.DOMParser;
import com.xmlprojekat.dto.XMLDto;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Random;


@Service
public class P1Service extends XMLService {
    private Integer id;
    private final DOMParser domParser;

    public P1Service(DOMParser domParser) {
        this.domParser = domParser;
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

    @Override
    protected String generateApplicationId() {
        Random r = new Random();
        String year = String.valueOf(r.nextInt(30) + 1990);
        return "П-" + year + "/" + id;
    }
}
