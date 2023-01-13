package main;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.xml.sax.SAXException;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class XmlTransformer {

    public void createXHTML(String xsl, String xmlInput, String xhtmlOutput) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        // Use the factory to create a template containing the xsl file
        Templates template = factory.newTemplates(new StreamSource(xsl));

        // Use the template to create a transformer
        Transformer xformer = template.newTransformer();

        // Prepare the input and output files
        Source source = new StreamSource(xmlInput);
        Result result = new StreamResult(xhtmlOutput);

        // Apply the xsl file to the source file and write the result to the output file
        xformer.transform(source, result);
    }

    public void convertToPdf(String xsl, String inputXml, String outputPdf) throws IOException, SAXException, TransformerException {
        // Construct a FopFactory
        FopFactory fopFactory = FopFactory.newInstance(new File("src/fop.xconf"));

        // Set up output stream.
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // Setup JAXP using identity transformer
        TransformerFactory factory = TransformerFactory.newInstance();

        // Point to the XSL-FO file
        // Create transformation source
        StreamSource transformSource = new StreamSource(new File(xsl));
        // Initialize user agent needed for the transformation
        FOUserAgent userAgent = fopFactory.newFOUserAgent();

        // Initialize the XSL-FO transformer object
        Transformer xslFoTransformer = factory.newTransformer(transformSource);

        // Construct FOP instance with desired output format
        Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, out);

        // Resulting SAX events (the generated FO) must be piped through to FOP
        Result res = new SAXResult(fop.getDefaultHandler());
        StreamSource source = new StreamSource(new File(inputXml));
        // Start XSLT transformation and FOP processing
        xslFoTransformer.transform(source, res);

        FileOutputStream fos = new FileOutputStream(outputPdf);
        fos.write(out.toByteArray());
        fos.close();
    }

    public static void main(String[] args) {
        XmlTransformer transformer = new XmlTransformer();
        try {
//            transformer.createXHTML("/home/mihajlo/XML-projekat/sheme/p-1.xsl", "/home/mihajlo/XML-projekat/sheme/instance-p11.xml", "./result.xhtml");
            transformer.convertToPdf("/home/mihajlo/XML-projekat/sheme/p1-pdf.xsl", "/home/mihajlo/XML-projekat/sheme/instance-p11.xml", "./result.pdf");
        } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (SAXException ex) {
            throw new RuntimeException(ex);
        }
    }
}