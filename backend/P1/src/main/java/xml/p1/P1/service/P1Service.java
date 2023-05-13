package xml.p1.P1.service;

import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFWriter;
import org.apache.jena.riot.*;
import org.apache.jena.riot.system.StreamRDF;
import org.apache.jena.riot.system.StreamRDFLib;
import org.apache.jena.riot.writer.JsonLDWriter;
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
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
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
            throws TransformerException, IOException, SAXException {
        P1Zahtev zahtev = new P1Zahtev(dto);
        String title = zahtev.getNameForCollection();
        Document document = converter.generateP1(zahtev);
        String xml = converter.documentToString(document);

        String rdf_path = "src/main/resources/data/rdf/" + title + ".rdf";
        String json_path = "src/main/resources/data/rdf/json/" + title + ".json";
        sparqlService.saveRDF(xml, rdf_path);
        saveJsonLD(rdf_path, json_path);
//        existManager.storeFromText("db/p1", title, xml);

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
