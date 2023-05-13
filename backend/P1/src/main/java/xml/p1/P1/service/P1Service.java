package xml.p1.P1.service;

import org.apache.xerces.dom.DeferredElementNSImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import xml.p1.P1.dom.DOMParser;
import xml.p1.P1.dom.P1toXMLConverter;
import xml.p1.P1.dto.P1DTO;
import xml.p1.P1.exist.ExistManager;
import xml.p1.P1.model.P1Zahtev;
import xml.p1.P1.transformer.XmlTransformer;

import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Service
public class P1Service {

    private final String PDF_XSL = "src/main/resources/data/xsl/p1-pdf.xsl";
    private final String XHTML_XSL = "src/main/resources/data/xsl/p1-xhtml.xsl";
    @Autowired
    P1toXMLConverter converter;
    @Autowired
    ExistManager existManager;
    @Autowired
    SparqlService sparqlService;
    @Autowired
    DOMParser domParser;
    public void createP1Zahtev(P1DTO dto)
            throws TransformerException, XMLDBException, ClassNotFoundException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException, IOException, SAXException {
        P1Zahtev zahtev = new P1Zahtev(dto);
        String title = zahtev.getNameForCollection();
        Document document = converter.generateP1(zahtev);
        String xml = converter.documentToString(document);

        sparqlService.saveRDF(xml, "src/main/resources/data/rdf/" + title + ".rdf");
        existManager.storeFromText("db/p1", title, xml);

        String xmlLocation = "src/main/resources/data/xml/" + title + ".xml";
        converter.writeDocumentToPath(document, xmlLocation);

        String outputPDFLocation = "src/main/resources/static/pdf/" + title + ".pdf";
        XmlTransformer.convertToPdf(PDF_XSL, xmlLocation, outputPDFLocation);

        String outputXHTMLLocation = "src/main/resources/static/xhtml/" + title + ".xhtml";
        XmlTransformer.convertToXhtml(XHTML_XSL, xmlLocation, outputXHTMLLocation);
    }


    public List<P1Zahtev> conductTextBasedSearch(String rawText) {
        try {
            List<XMLResource> resources = existManager.searchForText(rawText);
            List<P1Zahtev> zahtevi = new ArrayList<>();
            for (XMLResource xml: resources) {
                DeferredElementNSImpl document = (DeferredElementNSImpl) xml.getContentAsDOM();
                P1Zahtev zahtev = new P1Zahtev(document);
                zahtevi.add(zahtev);
            }
            return zahtevi;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


}
