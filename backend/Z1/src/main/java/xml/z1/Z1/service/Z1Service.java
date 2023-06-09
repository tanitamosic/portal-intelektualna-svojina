package xml.z1.Z1.service;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFLanguages;
import org.apache.jena.riot.RDFParser;
import org.apache.xerces.dom.DeferredElementNSImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import xml.z1.Z1.dom.DOMParser;
import xml.z1.Z1.dom.DOMWriter;
import xml.z1.Z1.dto.Z1DTO;
import xml.z1.Z1.exist.ExistManager;
import xml.z1.Z1.model.Z1Resenje;
import xml.z1.Z1.model.Z1Zahtev;
import xml.z1.Z1.transformer.XmlTransformer;

import javax.xml.transform.TransformerException;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Service
public class Z1Service {

    private final String PDF_XSL = "src/main/resources/data/xsl/z1-pdf.xsl";
    private final String PDF_RESENJE_XSL = "src/main/resources/data/xsl/z1-resenje-pdf.xsl";
    private final String XHTML_RESENJE_XSL = "src/main/resources/data/xsl/z1-resenje-xhtml.xsl";
    private final String XHTML_XSL = "src/main/resources/data/xsl/z1-xhtml.xsl";
    @Autowired
    DOMWriter converter;
    @Autowired
    ExistManager existManager;
    @Autowired
    SparqlService sparqlService;
    @Autowired
    DOMParser domParser;
    public void createZ1Zahtev(Z1DTO dto)
            throws TransformerException, IOException, SAXException, XMLDBException, ClassNotFoundException,
            InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Z1Zahtev zahtev = new Z1Zahtev(dto);
        String title = zahtev.getNameForCollection();
        Document document = converter.generateZ1(zahtev);
        String xml = converter.documentToString(document);

        String rdf_path = "src/main/resources/data/rdf/" + title + ".rdf";
        String json_path = "src/main/resources/data/rdf/json/" + title + ".json";
        sparqlService.saveRDF(xml, rdf_path, title);
        saveJsonLD(rdf_path, json_path);
        existManager.storeFromText("db/z1", title, xml);

        String xmlLocation = "src/main/resources/data/xml/" + title + ".xml";
        converter.writeDocumentToPath(xml, xmlLocation);


        String outputPDFLocation = "src/main/resources/static/pdf/" + title + ".pdf";
        XmlTransformer.convertToPdf(PDF_XSL, xmlLocation, outputPDFLocation);

        String outputXHTMLLocation = "src/main/resources/static/xhtml/" + title + ".xhtml";
        XmlTransformer.convertToXhtml(XHTML_XSL, xmlLocation, outputXHTMLLocation);
    }

    public void createZ1Resenje(Z1Resenje resenje) throws TransformerException, XMLDBException, ClassNotFoundException,
            InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException,
            IOException, SAXException {
        String title = resenje.getTitle();
        Document document = converter.generateZ1Resenje(resenje);
        String xml = converter.documentToString(document);

        existManager.storeFromText("db/z1", title, xml);
        String xmlLocation = "src/main/resources/data/xml/" + title + ".xml";
        converter.writeDocumentToPath(xml, xmlLocation);

        String outputPDFLocation = "src/main/resources/static/pdf/" + title + ".pdf";
        XmlTransformer.convertToPdf(PDF_RESENJE_XSL, xmlLocation, outputPDFLocation);

        String outputXHTMLLocation = "src/main/resources/static/xhtml/" + title + ".xhtml";
        XmlTransformer.convertToXhtml(XHTML_RESENJE_XSL, xmlLocation, outputXHTMLLocation);
    }


    public List<String> conductTextBasedSearch(String rawText) {
        try {
            List<XMLResource> resources = existManager.searchForText(rawText);
            List<String> zahtevi = new ArrayList<>();
            for (XMLResource xml: resources) {
                DeferredElementNSImpl document = (DeferredElementNSImpl) xml.getContentAsDOM();
                Z1Zahtev zahtev = new Z1Zahtev(document);
                zahtevi.add(zahtev.getBrojPrijave());
            }
            return zahtevi;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void saveJsonLD(String in_rdf, String out_json) throws IOException {
        // Create a Jena model and read in the input RDF file
        Model model = ModelFactory.createDefaultModel();
        InputStream inputStream = new FileInputStream(in_rdf);
        RDFParser.source(inputStream).lang(RDFLanguages.RDFXML).parse(model);

        // Create a OutputStream to hold the output
        OutputStream outputStream = new FileOutputStream(out_json);

        // Use the JSON-LD writer to write the model as JSON-LD to the output stream
        RDFDataMgr.write(outputStream, model, Lang.JSONLD);

        // Clean up resources
        inputStream.close();
        outputStream.flush();
        outputStream.close();
    }
}
