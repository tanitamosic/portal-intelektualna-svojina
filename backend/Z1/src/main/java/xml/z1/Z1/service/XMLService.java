package xml.z1.Z1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xml.z1.Z1.dom.DOMParser;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import xml.z1.Z1.fuseki.FusekiReader;
import xml.z1.Z1.fuseki.FusekiWriter;
import xml.z1.Z1.fuseki.MetadataExtractor;
import xml.z1.Z1.repository.ZahtevRepository;

import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public abstract class XMLService {

    @Autowired
    private DOMParser domParser;
    @Autowired
    private ZahtevRepository repo;
    @Autowired
    private MetadataExtractor metadataExtractor;



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

    protected abstract String generateApplicationId();

    public void saveFileFromString(String text){
        try {
            repo.saveZahtev(text);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<String> storeXML(InputStream input_xml, OutputStream output_rdf) throws IOException, TransformerException {
        metadataExtractor.extractMetadata(input_xml, output_rdf);
        FusekiWriter.saveRDF();
        Map<String, String> params = new HashMap<>();
        params.put("<%naziv_dela>", "\"How to scam people\"");
        return FusekiReader.executeQuery(params);
    }


}
