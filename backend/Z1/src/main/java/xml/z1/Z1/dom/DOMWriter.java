package xml.z1.Z1.dom;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import xml.z1.Z1.model.Z1Resenje;
import xml.z1.Z1.model.Z1Zahtev;
import xml.z1.Z1.model.deljeniTipovi.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class DOMWriter {

    private DocumentBuilderFactory factory;

    private TransformerFactory transformerFactory;

    private static final String XSI_NAMESPACE = "http://www.w3.org/2001/XMLSchema-instance";
    private static final String IMPORT_NAMESPACE = "http://localhost:3030/tipovi";

    private Document document;

    // TODO promeni
    private static final String Z1_NAMESPACE = "http://localhost:3030/z1";

    public DOMWriter() {
        factory = DocumentBuilderFactory.newInstance();

        transformerFactory = TransformerFactory.newInstance();
    }

    private void createDocument() {

        try {

            DocumentBuilder builder = factory.newDocumentBuilder();

            // Kreiranje novog dokumenta
            document = builder.newDocument();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Document generateZ1(Z1Zahtev z1) {
        createDocument();

        Element zahtev = document.createElement("zahtev");
        document.appendChild(zahtev);
        zahtev.setAttribute("xmlns:proj", IMPORT_NAMESPACE);
        zahtev.setAttribute("xmlns:z1", Z1_NAMESPACE);
        zahtev.setAttribute("xmlns:xsi", XSI_NAMESPACE);
        zahtev.setAttribute("xmlns:pred", "http://www.xmlsux.com/predicate/");
        zahtev.setAttribute("xsi:noNamespaceSchemaLocation", "file:./xsd/z-1.xsd");

        // ZAVOD
        Element zavod = document.createElement("zavod");
        zavod.setAttribute("about", "pred:zavod");
        zahtev.appendChild(zavod);

        Element brojPrijave = document.createElement("brojPrijave");
        brojPrijave.setAttribute("property", "pred:brojPrijave");
        zavod.appendChild(brojPrijave);
        brojPrijave.appendChild(document.createTextNode(z1.getBrojPrijave()));

        Element datumPodnosenja = document.createElement("datumPodnosenja");
        datumPodnosenja.setAttribute("property", "pred:datumPodnosenja");
        zavod.appendChild(datumPodnosenja);
        datumPodnosenja.appendChild(document.createTextNode(z1.getDatumPodnosenja()));

        // // PODACI O ZAVODU
        Element podaciOZavodu = document.createElement( "podaciOZavodu");
        podaciOZavodu.setAttribute("about", "pred:podaciOZavodu");
        zavod.appendChild(podaciOZavodu);

        Element institucija = document.createElement( "institucija");
        podaciOZavodu.appendChild(institucija);
        institucija.appendChild(document.createTextNode("Zavod za intelektualnu svojinu"));

        addAddress(podaciOZavodu, new Address("Knjeginje Ljubice", "5", "Beograd", "11000"));

        // ZAVOD -> PRILOZI
        Element prilozi = document.createElement("prilozi");
        zavod.appendChild(prilozi);

        for(String p : z1.getPrilozi().split(",")){
            Element prilog = document.createElement("prilog");
            prilog.appendChild(document.createTextNode(p));
            prilozi.appendChild(prilog);
        }

//        if(z1.getPrimerakZnaka()){
//            Element prilog = document.createElement("prilog");
//            prilog.appendChild(document.createTextNode("Primerak znaka"));
//            prilozi.appendChild(prilog);
//        }
//        if(z1.getSpisak()){
//            Element prilog = document.createElement("prilog");
//            prilog.appendChild(document.createTextNode("Spisak robe i usluga"));
//            prilozi.appendChild(prilog);
//        }
//        if(z1.getPunomocje()){
//            Element prilog = document.createElement("prilog");
//            prilog.appendChild(document.createTextNode("Punomocje"));
//            prilozi.appendChild(prilog);
//        }
//        if(z1.getRanije()){
//            Element prilog = document.createElement("prilog");
//            prilog.appendChild(document.createTextNode("Generalno punomocje ranije prilozeno"));
//            prilozi.appendChild(prilog);
//        }
//        if(z1.getNaknadno()){
//            Element prilog = document.createElement("prilog");
//            prilog.appendChild(document.createTextNode("Punomocje ce biti naknadno dostavljeno"));
//            prilozi.appendChild(prilog);
//        }
//        if(z1.getOpstiAkt()){
//            Element prilog = document.createElement("prilog");
//            prilog.appendChild(document.createTextNode("Opsti akt o kolektivnom zigu/zigu garancije"));
//            prilozi.appendChild(prilog);
//        }
//        if(z1.getDokazPrvenstvo()){
//            Element prilog = document.createElement("prilog");
//            prilog.appendChild(document.createTextNode("Dokaz o pravu prvenstva"));
//            prilozi.appendChild(prilog);
//        }
//        if(z1.getDokazTaksa()){
//            Element prilog = document.createElement("prilog");
//            prilog.appendChild(document.createTextNode("Dokaz o uplati takse"));
//            prilozi.appendChild(prilog);
//        }

        // PODNOSILAC

        Element podnosilac = document.createElement( "podnosilac");
        podnosilac.setAttribute("about", "pred:podnosilac");
        zahtev.appendChild(podnosilac);
        if (z1.getPodnosilac() instanceof FizickoLice) {
            podnosilac.setAttribute("xsi:type", "proj:TFizickoLice");

            FizickoLice fizickiPodnosilac = (FizickoLice) z1.getPodnosilac();
            Element imePodnosioca = document.createElement("proj:ime");
            imePodnosioca.appendChild(document.createTextNode(fizickiPodnosilac.getIme()));
            imePodnosioca.setAttribute("property", "pred:ime");
            Element prezimePodnosioca = document.createElement("proj:prezime");
            prezimePodnosioca.appendChild(document.createTextNode(fizickiPodnosilac.getPrezime()));
            prezimePodnosioca.setAttribute("property", "pred:prezime");

            podnosilac.appendChild(imePodnosioca);
            podnosilac.appendChild(prezimePodnosioca);
        } else {
            podnosilac.setAttribute("xsi:type", "proj:TPravnoLice");

            Element pibPodnosioca = document.createElement("proj:pib");
            pibPodnosioca.setAttribute("property", "pred:pib");
            Element naziv_preduzecaPodnosioca = document.createElement("proj:naziv_preduzeca");
            naziv_preduzecaPodnosioca.setAttribute("property", "pred:naziv_preduzeca");

            PravnoLice pl = (PravnoLice) z1.getPodnosilac();
            pibPodnosioca.appendChild(document.createTextNode(pl.getPib()));
            naziv_preduzecaPodnosioca.appendChild(document.createTextNode(pl.getNaziv_preduzeca()));

            podnosilac.appendChild(pibPodnosioca);
            podnosilac.appendChild(naziv_preduzecaPodnosioca);
        }

        addAddress(podnosilac, z1.getPodnosilac().getAdresa());
        addContact(podnosilac, z1.getPodnosilac().getKontakt());

        // PODACI O ZIGU
        Element zig = document.createElement("podaci_o_zigu");
        zig.setAttribute("about", "pred:podaci_o_zigu");
        zahtev.appendChild(zig);

        Element vrsta = document.createElement("vrsta_ziga");
        vrsta.setAttribute("property", "vrsta_ziga");
        vrsta.appendChild(document.createTextNode(z1.getVrstaZiga()));
        zig.appendChild(vrsta);

        Element format = document.createElement("format_ziga");
        format.setAttribute("property", "format_ziga");
        format.appendChild(document.createTextNode(z1.getFormatZiga()));
        zig.appendChild(format);

        Element izgled = document.createElement("izgled_ziga");
        izgled.appendChild(document.createTextNode(z1.getIzgledZiga())); // TODO impl slike
        zig.appendChild(izgled);

        Element boja = document.createElement("boja");
        boja.setAttribute("property", "boja");
        boja.appendChild(document.createTextNode(z1.getBoje()));
        zig.appendChild(boja);

        Element opis = document.createElement("opis");
        opis.appendChild(document.createTextNode(z1.getOpis()));
        zig.appendChild(opis);

        Element prevod = document.createElement("prevod");
        prevod.appendChild(document.createTextNode(z1.getPrevod()));
        zig.appendChild(prevod);

        Element transliteracija = document.createElement("transliteracija");
        transliteracija.appendChild(document.createTextNode(z1.getTransliteracija()));
        zig.appendChild(transliteracija);

        for(String i:z1.getKlase().replaceAll("\\s+","").split(",")) {
            Element k = document.createElement("klasa");
            k.setAttribute("property", "klasa");
            k.appendChild(document.createTextNode(i));
            zig.appendChild(k);
        }

        Element prv = document.createElement("pravo_prvenstva");
        prv.appendChild(document.createTextNode(z1.getPravoPrvenstva()));
        zig.appendChild(prv);


        // PUNOMOCNIK

        Element punomocnik = document.createElement( "punomocnik");
        punomocnik.setAttribute("about", "pred:punomocnik");
        zahtev.appendChild(punomocnik);

        if (z1.getPodnosilac() instanceof FizickoLice) {
            punomocnik.setAttribute("xsi:type", "proj:TFizickoLice");

            FizickoLice fizickiPodnosilac = (FizickoLice) z1.getPodnosilac();
            Element imePodnosioca = document.createElement("proj:ime");
            imePodnosioca.appendChild(document.createTextNode(fizickiPodnosilac.getIme()));
            imePodnosioca.setAttribute("property", "pred:ime");
            Element prezimePodnosioca = document.createElement("proj:prezime");
            prezimePodnosioca.appendChild(document.createTextNode(fizickiPodnosilac.getPrezime()));
            prezimePodnosioca.setAttribute("property", "pred:prezime");

            punomocnik.appendChild(imePodnosioca);
            punomocnik.appendChild(prezimePodnosioca);
        } else {
            punomocnik.setAttribute("xsi:type", "proj:TPravnoLice");

            Element pibPodnosioca = document.createElement("proj:pib");
            pibPodnosioca.setAttribute("property", "pred:pib");
            Element naziv_preduzecaPodnosioca = document.createElement("proj:naziv_preduzeca");
            naziv_preduzecaPodnosioca.setAttribute("property", "pred:naziv_preduzeca");

            PravnoLice pl = (PravnoLice) z1.getPodnosilac();
            pibPodnosioca.appendChild(document.createTextNode(pl.getPib()));
            naziv_preduzecaPodnosioca.appendChild(document.createTextNode(pl.getNaziv_preduzeca()));

            punomocnik.appendChild(pibPodnosioca);
            punomocnik.appendChild(naziv_preduzecaPodnosioca);
        }

        addAddress(punomocnik, z1.getPodnosilac().getAdresa());
        addContact(punomocnik, z1.getPodnosilac().getKontakt());

        // ZAJEDNICKI PREDSTAVNIK
        Lice zPredstavnikObj = z1.getPredstavnik();
        Element zajednickiPredstavnik = document.createElement( "zajednickiPredstavnik");
        zajednickiPredstavnik.setAttribute("about", "pred:zajednickiPredstavnik");
        zahtev.appendChild(zajednickiPredstavnik);
        if (zPredstavnikObj instanceof FizickoLice) {
            zajednickiPredstavnik.setAttribute("xsi:type", "proj:TFizickoLice");
        } else {
            zajednickiPredstavnik.setAttribute("xsi:type", "proj:TPravnoLice");
        }

        if (zPredstavnikObj instanceof FizickoLice) {
            Element imePosrednika = document.createElement("proj:ime");
            imePosrednika.setAttribute("property", "pred:ime");
            Element prezimePosrednika = document.createElement("proj:prezime");
            prezimePosrednika.setAttribute("property", "pred:prezime");

            FizickoLice fl = (FizickoLice) zPredstavnikObj;
            imePosrednika.appendChild(document.createTextNode(fl.getIme()));
            prezimePosrednika.appendChild(document.createTextNode(fl.getPrezime()));

            zajednickiPredstavnik.appendChild(imePosrednika);
            zajednickiPredstavnik.appendChild(prezimePosrednika);

        } else {
            Element pibPosrednika = document.createElement("proj:pib");
            pibPosrednika.setAttribute("property", "pred:pib");
            Element naziv_preduzecaPosrednika = document.createElement("proj:naziv_preduzeca");
            naziv_preduzecaPosrednika.setAttribute("property", "pred:naziv_preduzeca");

            PravnoLice pl = (PravnoLice) zPredstavnikObj;
            pibPosrednika.appendChild(document.createTextNode(pl.getPib()));
            naziv_preduzecaPosrednika.appendChild(document.createTextNode(pl.getNaziv_preduzeca()));

            zajednickiPredstavnik.appendChild(pibPosrednika);
            zajednickiPredstavnik.appendChild(naziv_preduzecaPosrednika);
        }
        addAddress(zajednickiPredstavnik, z1.getPredstavnik().getAdresa());
        addContact(zajednickiPredstavnik, z1.getPredstavnik().getKontakt());
        Element vrstaPosrednika = document.createElement( "vrstaPosrednika");
        vrstaPosrednika.setAttribute("property","pred:vrstaPosrednika");

        // TAKSE
        Element takse  = document.createElement("takse");
        zahtev.appendChild(takse);

        Element osnovnaTaksa = document.createElement("osnovna_taksa");
        takse.appendChild(osnovnaTaksa);
        osnovnaTaksa.appendChild(document.createTextNode(String.valueOf(z1.getOsnovnaTaksa())));

        Element zaKlase = document.createElement("za_klase");
        takse.appendChild(zaKlase);
        zaKlase.appendChild(document.createTextNode(String.valueOf(z1.getTakseZaKlase())));

        Element grafRes = document.createElement("za_graficko_resenje");
        takse.appendChild(grafRes);
        grafRes.appendChild(document.createTextNode(String.valueOf(z1.getTakseZaGrafRes())));

        return document;
    }

    private void addAddress(Element parentNode, Address a) {
        Element adresa = document.createElement("proj:adresa");
        parentNode.appendChild(adresa);

        Element mesto = document.createElement("proj:mesto");
        mesto.setAttribute("property", "pred:mesto");
        adresa.appendChild(mesto);
        mesto.appendChild(document.createTextNode(a.getMesto()));

        Element postanskiBroj = document.createElement("proj:postanski_broj");
        postanskiBroj.setAttribute("property", "pred:postanski_broj");
        adresa.appendChild(postanskiBroj);
        postanskiBroj.appendChild(document.createTextNode(a.getPostanskiBroj()));

        Element ulica = document.createElement("proj:ulica");
        ulica.setAttribute("property", "pred:ulica");
        adresa.appendChild(ulica);
        ulica.appendChild(document.createTextNode(a.getUlica()));

        Element broj = document.createElement("proj:broj");
        broj.setAttribute("property", "pred:broj");
        adresa.appendChild(broj);
        broj.appendChild(document.createTextNode(a.getBroj()));
    }

    private void addContact(Element parentNode, Kontakt contact) {
        Element kontakt = document.createElement("proj:kontakt");
        parentNode.appendChild(kontakt);

        if (null != contact.getEmail()) {
            Element email = document.createElement("proj:email");
            email.setAttribute("property", "pred:email");
            email.appendChild(document.createTextNode(contact.getEmail()));
            kontakt.appendChild(email);
        }
        if (null != contact.getTelefon()) {
            Element telefon = document.createElement("proj:telefon");
            telefon.setAttribute("property", "pred:telefon");
            telefon.appendChild(document.createTextNode(contact.getTelefon()));
            kontakt.appendChild(telefon);
        }
        if (null != contact.getFaks()) {
            Element faks = document.createElement("proj:faks");
            faks.setAttribute("property", "pred:faks");
            faks.appendChild(document.createTextNode(contact.getFaks()));
            kontakt.appendChild(faks);
        }
    }

    public Document generateZ1Resenje(Z1Resenje dto) {
        createDocument();

        Element resenje = document.createElement("resenje_zahteva");
        document.appendChild(resenje);
        resenje.setAttribute("xmlns:proj", IMPORT_NAMESPACE);
        resenje.setAttribute("xmlns:p-1-r", "http://localhost:3030/resenje_za_priznanje_patenta");
        resenje.setAttribute("xmlns:xsi", XSI_NAMESPACE);
        resenje.setAttribute("xmlns:pred", "http://www.xmlsux.com/predicate/");
        resenje.setAttribute("xsi:noNamespaceSchemaLocation", "file:./xsd/z1-resenje.xsd");

        Element broj_prijave = document.createElement("broj_prijave");
        broj_prijave.appendChild(document.createTextNode(dto.getBrojPrijave()));
        Element datum_obrade = document.createElement("datum_obrade");
        datum_obrade.appendChild(document.createTextNode(dto.getDatumObrade()));
        Element odbijen = document.createElement("odbijen");
        odbijen.appendChild(document.createTextNode(String.valueOf(dto.getOdbijen())));
        Element ime_sluzbenika = document.createElement("ime_sluzbenika");
        ime_sluzbenika.appendChild(document.createTextNode(dto.getImeSluzbenika()));
        Element prezime_sluzbenika = document.createElement("prezime_sluzbenika");
        prezime_sluzbenika.appendChild(document.createTextNode(dto.getPrezimeSluzbenika()));
        Element email_sluzbenika = document.createElement("email_sluzbenika");
        email_sluzbenika.appendChild(document.createTextNode(dto.getEmailSluzbenika()));
        Element razlog_odbijanja = document.createElement("razlog_odbijanja");
        razlog_odbijanja.appendChild(document.createTextNode(dto.getRazlogOdbijanja()));
        Element sifra = document.createElement("sifra");
        sifra.appendChild(document.createTextNode(dto.getSifra()));

        resenje.appendChild(broj_prijave);
        resenje.appendChild(datum_obrade);
        resenje.appendChild(odbijen);
        resenje.appendChild(ime_sluzbenika);
        resenje.appendChild(prezime_sluzbenika);
        resenje.appendChild(email_sluzbenika);
        resenje.appendChild(razlog_odbijanja);
        resenje.appendChild(sifra);
        return document;
    }


    public void writeDocumentToPath(String xml, String filePath) {
        try {
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(xml);
            myWriter.close();

        } catch (TransformerFactoryConfigurationError | IOException e) {
            e.printStackTrace();
        }
    }

    public String documentToString(Document doc) throws TransformerException {
        DOMImplementationLS domImplementation = (DOMImplementationLS) doc.getImplementation();
        LSSerializer lsSerializer = domImplementation.createLSSerializer();
        String xml = lsSerializer.writeToString(doc);
        xml = xml.replace("UTF-16", "UTF-8");
        return xml;
    }
}
