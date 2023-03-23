package xml.z1.Z1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xml.z1.Z1.dom.DOMParser;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import xml.z1.Z1.repository.ZahtevRepository;

import java.time.LocalDateTime;
@Service
public abstract class XMLService {

    @Autowired
    private DOMParser domParser;
    @Autowired
    private ZahtevRepository repo;


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

}
