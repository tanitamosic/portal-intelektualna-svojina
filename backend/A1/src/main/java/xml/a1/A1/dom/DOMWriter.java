package xml.a1.A1.dom;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import xml.a1.A1.model.A1Resenje;
import xml.a1.A1.model.A1Zahtev;
import xml.a1.A1.model.deljeniTipovi.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

@Component
public class DOMWriter {

    private DocumentBuilderFactory factory;

    private TransformerFactory transformerFactory;

    private static final String XSI_NAMESPACE = "http://www.w3.org/2001/XMLSchema-instance";
    private static final String IMPORT_NAMESPACE = "http://localhost:3030/tipovi";

    private Document document;

    private static final String A1_NAMESPACE = "https://localhost:3030/a-1";

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

    public Document generateA1(A1Zahtev p1) {
        createDocument();

        Element zahtev = document.createElement("zahtev");
        document.appendChild(zahtev);
        zahtev.setAttribute("xmlns:proj", IMPORT_NAMESPACE);
        zahtev.setAttribute("xmlns:p-1", A1_NAMESPACE);
        zahtev.setAttribute("xmlns:xsi", XSI_NAMESPACE);
        zahtev.setAttribute("xmlns:pred", "http://www.xmlsux.com/predicate/");
        zahtev.setAttribute("xsi:noNamespaceSchemaLocation", "file:./xsd/p-1.xsd");

        // ZAVOD
        Element zavod = document.createElement("zavod");
        zavod.setAttribute("about", "pred:zavod");
        zahtev.appendChild(zavod);

        // // FORMA ZA ZAVOD
        Element formaZaZavod = document.createElement("formaZaZavod");
        formaZaZavod.setAttribute("about", "pred:formaZaZavod");
        zavod.appendChild(formaZaZavod);

        Element brojPrijave = document.createElement("brojPrijave");
        brojPrijave.setAttribute("property", "pred:brojPrijave");
        formaZaZavod.appendChild(brojPrijave);
        brojPrijave.appendChild(document.createTextNode(p1.getBrojPrijave()));
        Element datumPrijema = document.createElement("datumPrijema");
        datumPrijema.setAttribute("property", "pred:datumPrijema");
        formaZaZavod.appendChild(datumPrijema);
        datumPrijema.appendChild(document.createTextNode(p1.getDatumPrijema()));
        Element datumPodnosenja = document.createElement("datumPodnosenja");
        datumPodnosenja.setAttribute("property", "pred:datumPodnosenja");
        formaZaZavod.appendChild(datumPodnosenja);
        datumPodnosenja.appendChild(document.createTextNode(p1.getDatumPodnosenja()));

        // // PODACI O ZAVODU
        Element podaciOZavodu = document.createElement( "podaciOZavodu");
        podaciOZavodu.setAttribute("about", "pred:podaciOZavodu");
        zavod.appendChild(podaciOZavodu);

        Element institucija = document.createElement( "institucija");
        podaciOZavodu.appendChild(institucija);
        institucija.appendChild(document.createTextNode("Zavod za intelektualnu svojinu"));

        addAddress(podaciOZavodu, new Address("Beograd", "5", "Knjeginje Ljubice", "11000"));

        // FORMA PODNOSIOCA
        Element formaPodnosioca = document.createElement( "formaPodnosioca");
        formaPodnosioca.setAttribute("about", "pred:formaPodnosioca");
        zahtev.appendChild(formaPodnosioca);

        // // NAZIV PRONALASKA
        Element nazivPronalaska = document.createElement( "nazivPronalaska");
        nazivPronalaska.setAttribute("about", "pred:nazivPronalaska");
        formaPodnosioca.appendChild(nazivPronalaska);
        Element srpski = document.createElement( "srpski");
        srpski.setAttribute("property", "pred:srpski");
        nazivPronalaska.appendChild(srpski);
        srpski.appendChild(document.createTextNode(p1.getSrpskiNazivPronalaska()));
        Element engleski = document.createElement( "engleski");
        engleski.setAttribute("property", "pred:engleski");
        nazivPronalaska.appendChild(engleski);
        engleski.appendChild(document.createTextNode(p1.getEngleskiNazivPronalaska()));

        // // PODNOSILAC PRIJAVE

        Element podnosilacPrijave = document.createElement( "podnosilacPrijave");
        podnosilacPrijave.setAttribute("about", "pred:podnosilacPrijave");
        if (p1.getPodnosilacPrijave() instanceof FizickoLice) {
            podnosilacPrijave.setAttribute("xsi:type", "proj:TFizickoLice");

            FizickoLice fizickiPodnosilac = (FizickoLice) p1.getPodnosilacPrijave();
            Element imePodnosioca = document.createElement("proj:ime");
            imePodnosioca.appendChild(document.createTextNode(fizickiPodnosilac.getIme()));
            imePodnosioca.setAttribute("property", "pred:ime");
            Element prezimePodnosioca = document.createElement("proj:prezime");
            prezimePodnosioca.appendChild(document.createTextNode(fizickiPodnosilac.getPrezime()));
            prezimePodnosioca.setAttribute("property", "pred:prezime");

            podnosilacPrijave.appendChild(imePodnosioca);
            podnosilacPrijave.appendChild(prezimePodnosioca);
        } else {
            podnosilacPrijave.setAttribute("xsi:type", "proj:TPravnoLice");

            Element pibPodnosioca = document.createElement("proj:pib");
            pibPodnosioca.setAttribute("property", "pred:pib");
            Element naziv_preduzecaPodnosioca = document.createElement("proj:naziv_preduzeca");
            naziv_preduzecaPodnosioca.setAttribute("property", "pred:naziv_preduzeca");

            PravnoLice pl = (PravnoLice) p1.getPodnosilacPrijave();
            pibPodnosioca.appendChild(document.createTextNode(pl.getPib()));
            naziv_preduzecaPodnosioca.appendChild(document.createTextNode(pl.getNaziv_preduzeca()));

            podnosilacPrijave.appendChild(pibPodnosioca);
            podnosilacPrijave.appendChild(naziv_preduzecaPodnosioca);
        }

        addAddress(podnosilacPrijave, p1.getPodnosilacPrijave().getAdresa());
        addContact(podnosilacPrijave, p1.getPodnosilacPrijave().getKontakt());
        Element jePronalazac = document.createElement( "jePronalazac");

        jePronalazac.setAttribute("property", "pred:jePronalazac");
        jePronalazac.appendChild(document.createTextNode(p1.getPodnosilacJePronalazac().toString()));
        podnosilacPrijave.appendChild(jePronalazac);

        formaPodnosioca.appendChild(podnosilacPrijave);

        // // PRONALAZAC - pronalazac je sigurno fizicko lice (ne moze biti pravno)
        FizickoLice Pronalazac = (FizickoLice) p1.getPronalazac();
        Element pronalazac = document.createElement( "pronalazac");
        pronalazac.setAttribute("xsi:type", "proj:TFizickoLice");
        pronalazac.setAttribute("about", "pred:pronalazac");

        if (p1.getPronalazacZeliBitiNaveden()) {
            Element imePronalazaca = document.createElement( "proj:ime");
            imePronalazaca.setAttribute("property", "pred:ime");
            Element prezimePronalazaca = document.createElement( "proj:prezime");
            prezimePronalazaca.setAttribute("property", "pred:prezime");

            imePronalazaca.appendChild(document.createTextNode(Pronalazac.getIme()));
            prezimePronalazaca.appendChild(document.createTextNode(Pronalazac.getPrezime()));

            pronalazac.appendChild(imePronalazaca);
            pronalazac.appendChild(prezimePronalazaca);
            addAddress(pronalazac, p1.getPronalazac().getAdresa());
            addContact(pronalazac, p1.getPronalazac().getKontakt());

        }
        Element zeliBitiNaveden = document.createElement( "zeliBitiNaveden");
        zeliBitiNaveden.setAttribute("property", "pred:zeliBitiNaveden");
        zeliBitiNaveden.appendChild(document.createTextNode(p1.getPronalazacZeliBitiNaveden().toString()));
        pronalazac.appendChild(zeliBitiNaveden);
        formaPodnosioca.appendChild(pronalazac);


        // // POSREDNIK
        Lice PosrednikOBJ = p1.getPosrednik();
        Element posrednik = document.createElement( "posrednik");
        posrednik.setAttribute("about", "pred:posrednik");
        if (PosrednikOBJ instanceof FizickoLice) {
            posrednik.setAttribute("xsi:type", "proj:TFizickoLice");
        } else {
            posrednik.setAttribute("xsi:type", "proj:TPravnoLice");
        }
        formaPodnosioca.appendChild(posrednik);


        if (PosrednikOBJ instanceof FizickoLice) {
            Element imePosrednika = document.createElement("proj:ime");
            imePosrednika.setAttribute("property", "pred:ime");
            Element prezimePosrednika = document.createElement("proj:prezime");
            prezimePosrednika.setAttribute("property", "pred:prezime");

            FizickoLice fl = (FizickoLice) PosrednikOBJ;
            imePosrednika.appendChild(document.createTextNode(fl.getIme()));
            prezimePosrednika.appendChild(document.createTextNode(fl.getPrezime()));

            posrednik.appendChild(imePosrednika);
            posrednik.appendChild(prezimePosrednika);

        } else {
            Element pibPosrednika = document.createElement("proj:pib");
            pibPosrednika.setAttribute("property", "pred:pib");
            Element naziv_preduzecaPosrednika = document.createElement("proj:naziv_preduzeca");
            naziv_preduzecaPosrednika.setAttribute("property", "pred:naziv_preduzeca");

            PravnoLice pl = (PravnoLice) PosrednikOBJ;
            pibPosrednika.appendChild(document.createTextNode(pl.getPib()));
            naziv_preduzecaPosrednika.appendChild(document.createTextNode(pl.getNaziv_preduzeca()));

            posrednik.appendChild(pibPosrednika);
            posrednik.appendChild(naziv_preduzecaPosrednika);
        }
        addAddress(posrednik, p1.getPosrednik().getAdresa());
        addContact(posrednik, p1.getPosrednik().getKontakt());
        Element vrstaPosrednika = document.createElement( "vrstaPosrednika");
        vrstaPosrednika.setAttribute("property","pred:vrstaPosrednika");
        vrstaPosrednika.appendChild(document.createTextNode(p1.getVrstaPosrednika()));
        posrednik.appendChild(vrstaPosrednika);

        // // ADRESA ZA DOSTAVLJANJE
        Element adresaZaDostavljanje = document.createElement( "adresaZaDostavljanje");
        adresaZaDostavljanje.setAttribute("about", "pred:adresaZaDostavljanje");
        formaPodnosioca.appendChild(adresaZaDostavljanje);
        addAddress(adresaZaDostavljanje, p1.getAdresaZaDostavljanje());

        // // NACIN DOSTAVLJANJA
        Element nacinDostavljanja = document.createElement( "nacinDostavljanja");
        nacinDostavljanja.setAttribute("property", "pred:nacinDostavljanja");
        formaPodnosioca.appendChild(nacinDostavljanja);
        nacinDostavljanja.appendChild(document.createTextNode(p1.getNacinDostavljanja()));

        // // PRIJAVA
        Element prijava = document.createElement( "prijava");
        formaPodnosioca.appendChild(prijava);
        Element vrstaPrijave = document.createElement( "vrstaPrijave");
        vrstaPrijave.setAttribute("property", "pred:vrstaPrijave");
        prijava.appendChild(vrstaPrijave);
        vrstaPrijave.appendChild(document.createTextNode(p1.getVrstaPrijave()));
        Element brojPrvobitnePrijave = document.createElement( "brojPrvobitnePrijave");
        brojPrvobitnePrijave.setAttribute("property", "pred:brojPrvobitnePrijave");
        prijava.appendChild(brojPrvobitnePrijave);
        brojPrvobitnePrijave.appendChild(document.createTextNode(p1.getBrojPrvobitnePrijave()));
        Element datumPodnosenjaPrvobitnePrijave = document.createElement( "datumPodnosenjaPrvobitnePrijave");
        datumPodnosenjaPrvobitnePrijave.setAttribute("property", "pred:datumPodnosenjaPrvobitnePrijave");
        prijava.appendChild(datumPodnosenjaPrvobitnePrijave);
        datumPodnosenjaPrvobitnePrijave.appendChild(document.createTextNode(p1.getDatumPodnosenjaPrvobitnePrijave()));

        // // ZAHTEV ZA PRIZNANJE PRAVA
        Element zahtevZaPriznanjePrava = document.createElement( "zahtevZaPriznanjePrava");
        zahtevZaPriznanjePrava.setAttribute("about", "pred:zahtevZaPriznanjePrava");
        formaPodnosioca.appendChild(zahtevZaPriznanjePrava);
        Element prijave = document.createElement( "prijave");
        zahtevZaPriznanjePrava.appendChild(prijave);

        for (int i = 0; i < p1.getRanijePrijave().size(); i++) {


            Element ranijaPrijava1 = document.createElement( "ranijaPrijava");
            ranijaPrijava1.setAttribute("broj", String.valueOf(i+1));
            ranijaPrijava1.setAttribute("about", "pred:ranijaPrijava".concat(String.valueOf(i+1)));
            prijave.appendChild(ranijaPrijava1);
            Element datum1 = document.createElement("datum");
            datum1.setAttribute("about", " pred:datum");
            datum1.setAttribute("datatype", "xs:date");
            Element brojPrijave1 = document.createElement( "brojPrijave");
            brojPrijave1.setAttribute("about", " pred:brojPrijave");
            Element drzavaOrg1 = document.createElement( "drzavaIliOrganizacija");
            drzavaOrg1.setAttribute("about", " pred:drzavaIliOrganizacija");

            ranijaPrijava1.appendChild(datum1);
            ranijaPrijava1.appendChild(brojPrijave1);
            ranijaPrijava1.appendChild(drzavaOrg1);
        }

        Element podaciOOstalimPravimaNaListu2 = document.createElement( "podaciOOstalimPravimaNaListu2");
        podaciOOstalimPravimaNaListu2.setAttribute("about", " pred:podaciOOstalimPravimaNaListu2");
        podaciOOstalimPravimaNaListu2.appendChild(document.createTextNode(p1.getImaDodatnogLista().toString()));
        zahtevZaPriznanjePrava.appendChild(podaciOOstalimPravimaNaListu2);

        if (p1.getImaDodatnogLista()) {
            Element dodatniList = document.createElement( "dodatniList2");
            dodatniList.setAttribute("property", "pred:dodatniList2");
            dodatniList.appendChild(document.createTextNode(p1.getDodatniList()));
            formaPodnosioca.appendChild(dodatniList);
        }
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

    public Document generateA1Resenje(A1Resenje dto) {
        createDocument();

        Element resenje = document.createElement("resenje_zahteva");
        document.appendChild(resenje);
        resenje.setAttribute("xmlns:proj", IMPORT_NAMESPACE);
        resenje.setAttribute("xmlns:p-1-r", "http://localhost:3030/resenje_za_priznanje_patenta");
        resenje.setAttribute("xmlns:xsi", XSI_NAMESPACE);
        resenje.setAttribute("xmlns:pred", "http://www.xmlsux.com/predicate/");
        resenje.setAttribute("xsi:noNamespaceSchemaLocation", "file:./xsd/p1-resenje.xsd");

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

        resenje.appendChild(broj_prijave);
        resenje.appendChild(datum_obrade);
        resenje.appendChild(odbijen);
        resenje.appendChild(ime_sluzbenika);
        resenje.appendChild(prezime_sluzbenika);
        resenje.appendChild(email_sluzbenika);
        resenje.appendChild(razlog_odbijanja);
        return document;
    }


    public void writeDocumentToPath(Document document, String filePath) {
        try {
            // Kreiranje instance objekta zaduzenog za serijalizaciju DOM modela
            Transformer transformer = transformerFactory.newTransformer();

            // Indentacija serijalizovanog izlaza
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Nad "source" objektom (DOM stablo) vrši se transformacija
            DOMSource source = new DOMSource(document);

            // Rezultujući stream (argument metode)
            StreamResult result = new StreamResult(new File(filePath));

            // Poziv metode koja vrši opisanu transformaciju
            transformer.transform(source, result);
            result.getOutputStream().flush();

        } catch (TransformerFactoryConfigurationError | TransformerException | IOException e) {
            e.printStackTrace();
        }
    }

    public String documentToString(Document doc) throws TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));
        return writer.getBuffer().toString();
    }
}
