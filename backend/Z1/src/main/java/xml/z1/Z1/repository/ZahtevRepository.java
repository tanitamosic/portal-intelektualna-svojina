package xml.z1.Z1.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;
import xml.z1.Z1.exist.ExistManager;

import java.lang.reflect.InvocationTargetException;

@Repository
public class ZahtevRepository {
    private String collectionId = "db/z1";
    @Autowired
    private ExistManager existManager;

    public void saveZahtev(String text) throws XMLDBException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        existManager.storeFromText(collectionId,"demo",text);
    }
}
