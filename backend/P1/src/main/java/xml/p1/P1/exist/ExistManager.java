package xml.p1.P1.exist;

import org.exist.xmldb.EXistResource;
import org.exist.xmldb.RemoteXMLResource;
import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;
import org.xmldb.api.modules.XUpdateQueryService;

import javax.xml.transform.OutputKeys;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExistManager {
    private final static String TARGET_NAMESPACE = "http://localhost:3030/tipovi";
    public static final String APPEND = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
            + "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:append select=\"%1$s\" child=\"last()\">%2$s</xu:append>"
            + "</xu:modifications>";
    public static final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
            + "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:update select=\"%1$s\">%2$s</xu:update>"
            + "</xu:modifications>";

    @Autowired
    private AuthenticationManager authManager;

    private static final String COLLECTION_URI = "db/p1";

    public void createConnection() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, XMLDBException {
        Class<?> cl = Class.forName(authManager.getDriver());

        Database db = (Database) cl.getDeclaredConstructor().newInstance();
        db.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(db);

    }

    public void closeConnection(Collection col, XMLResource res) throws XMLDBException {
        if(col != null){
            col.close();
        }
        if (res!=null){
            ((EXistResource) res).freeResources();
        }

    }

    public Collection getOrCreateCollection(String collectionUri, int pathOffset) throws XMLDBException {
        // URI MORA IMATI PREFIX db/ INACE EXIST VRISTI
        Collection col = DatabaseManager.getCollection(authManager.getUri()+collectionUri,authManager.getUsername(),authManager.getPassword());

        if(col == null){
            if(collectionUri.startsWith("/")){
                collectionUri = collectionUri.substring(1);
            }
            String pathSegments[]= collectionUri.split("/");
            if (pathSegments.length>0){
                StringBuilder path = new StringBuilder();
                for(int i=0;i<=pathOffset;i++){
                    path.append("/"+pathSegments[i]);
                }
                Collection startCol = DatabaseManager.getCollection(authManager.getUri()+path,
                        authManager.getUsername(), authManager.getPassword());
                if (startCol == null){
                    String parentPath = path.substring(0, path.lastIndexOf("/"));
                    Collection parentCol = DatabaseManager.getCollection(authManager.getUri()+parentPath,
                            authManager.getUsername(), authManager.getPassword());
                    CollectionManagementService service = (CollectionManagementService) parentCol.getService(
                            "CollectionManagementService", "1.0");
                    col = service.createCollection(pathSegments[pathOffset]);
                    col.close();
                    parentCol.close();
                } else {
                    startCol.close();
                }
            }
            return getOrCreateCollection(collectionUri,++pathOffset);
        } else{
            return col;
        }
    }

    public void store(String collenctionId, String documentId, String filePath) throws XMLDBException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        createConnection();
        Collection col = null;
        XMLResource res = null;
        try {
            col = getOrCreateCollection(collenctionId,0);
            res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);
            File f = new File(filePath);

            if(!f.canRead()){
                return;
            }
            res.setContent(f);
            col.storeResource(res);
        } finally {
            closeConnection(col,res);
        }
    }

    public void storeFromText(String collenctionId, String documentId, String XMLString) throws XMLDBException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        createConnection();
        Collection col = null;
        XMLResource res = null;
        try {
            col = getOrCreateCollection(collenctionId,0);
            res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);
            res.setContent(XMLString);
            col.storeResource(res);
        } finally {
            closeConnection(col,res);
        }
    }

    public XMLResource load(String collectionUri, String documentId) throws XMLDBException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        createConnection();
        Collection col = null;
        XMLResource res = null;
        try{
            col = DatabaseManager.getCollection(authManager.getUri()+collectionUri,
                    authManager.getUsername(), authManager.getPassword());
            col.setProperty(OutputKeys.INDENT, "yes");
            res = (XMLResource) col.getResource(documentId);
            return res;
        } finally {
            if (col!=null){
                col.close();
            }
        }
    }

    public ResourceSet retrieve(String collectionUri, String xpathExp) throws XMLDBException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        createConnection();
        Collection col = null;
        ResourceSet result = null;
        try{
            col = DatabaseManager.getCollection(authManager.getUri()+collectionUri,
                    authManager.getUsername(), authManager.getPassword());
            col.setProperty(OutputKeys.INDENT, "yes");
            XPathQueryService xpathService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xpathService.setProperty("indent", "yes");
            xpathService.setProperty("",TARGET_NAMESPACE);
            result =xpathService.query(xpathExp);
        } finally {
            if (col!=null){
                col.close();
            }
        }
        return result;
    }

    public void update(String collectionUri, String document, String contextXPath, String patch) throws XMLDBException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        createConnection();
        Collection col = null;
        String chosenTemplate = UPDATE;
        try{
            col = DatabaseManager.getCollection(authManager.getUri()+collectionUri,
                    authManager.getUsername(), authManager.getPassword());
            XUpdateQueryService service = (XUpdateQueryService) col.getService("XUpdateQueryService", "1.0");
            service.setProperty("indent", "yes");
            service.updateResource(document, String.format(chosenTemplate,contextXPath,patch));
        } finally {
            if (col!=null){
                col.close();
            }
        }

    }

    public void append(String collectionUri, String document, String contextXPath, String patch) throws XMLDBException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        createConnection();
        Collection col = null;
        String chosenTemplate = APPEND;
        try{
            col = DatabaseManager.getCollection(authManager.getUri()+collectionUri,
                    authManager.getUsername(), authManager.getPassword());
            XUpdateQueryService service = (XUpdateQueryService) col.getService("XUpdateQueryService", "1.0");
            service.setProperty("indent", "yes");
            service.updateResource(document, String.format(chosenTemplate,contextXPath,patch));
        } finally {
            if (col!=null){
                col.close();
            }
        }

    }

    public List<XMLResource> searchForText(String searchText) throws XMLDBException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, UnsupportedEncodingException {
        // Search for XML files in the collection
        createConnection();
        List<XMLResource> resources = new ArrayList<>();
        String xPath = "/*[contains(.,\"" + searchText + "\")]";
        String uri = authManager.getUri() + "db/p1";

        Collection col = DatabaseManager.getCollection(uri,authManager.getUsername(),authManager.getPassword());

        XPathQueryService xPathQueryService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
        xPathQueryService.setProperty("indent", "yes");
        ResourceIterator iter = xPathQueryService.query(xPath).getIterator();
        while (iter.hasMoreResources()) {
            Resource res = iter.nextResource();
            System.out.println(res.getContent());
            RemoteXMLResource resxml = (RemoteXMLResource) res;
            resources.add(resxml);
        }
        return resources;
    }

    private static String createXPathExpressionForTextSearch(List<String> words, boolean matchCase) {
        int wordsDone = 0;
        String xpath = "/*[";

        for (String word : words) {
            xpath = xpath.concat("contains(");

            if (!matchCase) {
                xpath = xpath.concat("lower-case(.)");
                word = word.toLowerCase();
            } else {
                xpath = xpath.concat(".");
            }

            xpath = xpath.concat(", ").concat("\"").concat(word).concat("\"");
            xpath = xpath.concat(")");

            wordsDone++;
            if (wordsDone != words.size()) {
                xpath = xpath.concat(" and ");
            }
        }

        xpath = xpath.concat("]");
//        for (String word : words)
//            xpath = xpath.concat(" | //pat:Naziv_pronalaska[contains(@Naziv, '" + word + "')]");
        return xpath;
    }

}
