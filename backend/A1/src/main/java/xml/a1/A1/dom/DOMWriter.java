package xml.a1.A1.dom;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import xml.a1.A1.model.A1Resenje;
import xml.a1.A1.model.A1Zahtev;
import xml.a1.A1.model.Autor;
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

    public Document generateA1(A1Zahtev a1) {
        createDocument();

        Element zahtev = document.createElement("zahtev");
        document.appendChild(zahtev);
        zahtev.setAttribute("xmlns:proj", IMPORT_NAMESPACE);
        zahtev.setAttribute("xmlns:a-1", A1_NAMESPACE);
        zahtev.setAttribute("xmlns:xsi", XSI_NAMESPACE);
        zahtev.setAttribute("xmlns:pred", "http://www.xmlsux.com/predicate/");
        zahtev.setAttribute("xsi:noNamespaceSchemaLocation", "file:./xsd/a-1.xsd");
        zahtev.setAttribute("about", "pred:zahtev");
        Element podnosilac_zahteva = document.createElement("podnosilac_zahteva");
        podnosilac_zahteva.setAttribute("about", "pred:podnosilac");
        zahtev.setAttribute("redni_broj","1");
        Element podnosilac_zahteva_lice = document.createElement("lice");
        if (a1.getPodnosilac_zahteva() instanceof FizickoLice ){
            podnosilac_zahteva_lice.setAttribute("xsi:type","proj:FizickoLice");
        }
        else{
            podnosilac_zahteva_lice.setAttribute("xsi:type","proj:PravnoLice");
        }

        addAddress(podnosilac_zahteva_lice, a1.getPodnosilac_zahteva().getAdresa());
        addContact(podnosilac_zahteva_lice, a1.getPodnosilac_zahteva().getKontakt());

        if (a1.getPodnosilac_zahteva() instanceof FizickoLice ){
            Element ime = document.createElement("proj:ime");
            ime.setAttribute("property","pred:ime");
            ime.appendChild(document.createTextNode(((FizickoLice) a1.getPodnosilac_zahteva()).getIme()));
            Element prezime = document.createElement("proj:prezime");
            prezime.setAttribute("property","pred:prezime");
            prezime.appendChild(document.createTextNode(((FizickoLice) a1.getPodnosilac_zahteva()).getPrezime()));
            podnosilac_zahteva_lice.appendChild(ime);
            podnosilac_zahteva_lice.appendChild(prezime);
        }
        else{
            podnosilac_zahteva_lice.setAttribute("xsi:type","proj:PravnoLice");
            Element naziv_preduzeca = document.createElement("naziv_preduzeca");
            naziv_preduzeca.setAttribute("property","pred:naziv");
            naziv_preduzeca.appendChild(document.createTextNode(((PravnoLice) a1.getPodnosilac_zahteva()).getNaziv_preduzeca()));
            Element pib = document.createElement("pib");
            pib.appendChild(document.createTextNode(((PravnoLice) a1.getPodnosilac_zahteva()).getPib()));
            podnosilac_zahteva_lice.appendChild(naziv_preduzeca);
            podnosilac_zahteva_lice.appendChild(pib);
        }
        podnosilac_zahteva.appendChild(podnosilac_zahteva_lice);
        zahtev.appendChild(podnosilac_zahteva);

        Element pseudonim_podnosioca = document.createElement("pseudonim_podnosioca");
        pseudonim_podnosioca.setAttribute("property","pred:pseudonim");
        pseudonim_podnosioca.setAttribute("redni_broj","2");
        pseudonim_podnosioca.appendChild(document.createTextNode(a1.getPseudonim_podnosioca()));
        zahtev.appendChild(pseudonim_podnosioca);

        Element punomocnik = document.createElement("punomocnik");
        punomocnik.setAttribute("about", "pred:punomocnik");
        punomocnik.setAttribute("redni_broj","3");
        Element punomocnik_lice = document.createElement("lice");
        if (a1.getPunomocnik() instanceof FizickoLice ){
            punomocnik_lice.setAttribute("xsi:type","proj:FizickoLice");
        }
        else{
            punomocnik_lice.setAttribute("xsi:type","proj:PravnoLice");
        }
        addAddress(punomocnik_lice, a1.getPunomocnik().getAdresa());
        addContact(punomocnik_lice, a1.getPunomocnik().getKontakt());

        if (a1.getPunomocnik() instanceof FizickoLice ){
            Element ime = document.createElement("proj:ime");
            ime.setAttribute("property","pred:ime");
            ime.appendChild(document.createTextNode(((FizickoLice) a1.getPunomocnik()).getIme()));
            Element prezime = document.createElement("proj:prezime");
            prezime.setAttribute("property","pred:prezime");
            prezime.appendChild(document.createTextNode(((FizickoLice) a1.getPunomocnik()).getPrezime()));
            punomocnik_lice.appendChild(ime);
            punomocnik_lice.appendChild(prezime);
        }
        else {
            punomocnik_lice.setAttribute("xsi:type", "proj:PravnoLice");
            Element naziv_preduzeca = document.createElement("naziv_preduzeca");
            naziv_preduzeca.setAttribute("property", "pred:naziv");
            naziv_preduzeca.appendChild(document.createTextNode(((PravnoLice) a1.getPunomocnik()).getNaziv_preduzeca()));
            Element pib = document.createElement("pib");
            pib.appendChild(document.createTextNode(((PravnoLice) a1.getPunomocnik()).getPib()));
            punomocnik_lice.appendChild(naziv_preduzeca);
            punomocnik_lice.appendChild(pib);
        }
        punomocnik.appendChild(punomocnik_lice);
        zahtev.appendChild(punomocnik);

        Element naslov_dela = document.createElement("naslov_dela");
        naslov_dela.setAttribute("property","pred:naslov");
        naslov_dela.setAttribute("redni_broj","4");
        naslov_dela.appendChild(document.createTextNode(a1.getNaslov_dela()));
        zahtev.appendChild(naslov_dela);

        Element podaci_o_naslovu_izvornog_dela = document.createElement("podaci_o_naslovu_izvornog_dela");
        podaci_o_naslovu_izvornog_dela.setAttribute("redni_broj","5");
        Element podaci_o_naslovu_izvornog_dela_naslov = document.createElement("naslov");
        podaci_o_naslovu_izvornog_dela_naslov.appendChild(document.createTextNode(a1.getPodaci_o_naslovu_izvonog_dela_naslov()));
        podaci_o_naslovu_izvornog_dela.appendChild(podaci_o_naslovu_izvornog_dela_naslov);
        for(Autor a: a1.getPodaci_o_naslovu_izvonog_dela_autori()){
            Element autor = document.createElement("autor");
            autor.setAttribute("about","pred:autor");
            addAddress(autor, a.getAdresa());
            addContact(autor, a.getKontakt());
            Element ime = document.createElement("proj:ime");
            ime.setAttribute("property","pred:ime");
            ime.appendChild(document.createTextNode(a.getIme()));
            Element prezime = document.createElement("proj:prezime");
            prezime.setAttribute("property","pred:prezime");
            prezime.appendChild(document.createTextNode(a.getPrezime()));
            autor.appendChild(ime);
            autor.appendChild(prezime);
            Element godina_smrti = document.createElement("proj:godina_smrti");
            godina_smrti.appendChild(document.createTextNode(a.getGodina_smrti().toString()));
            autor.appendChild(godina_smrti);
            podaci_o_naslovu_izvornog_dela.appendChild(autor);

        }
        zahtev.appendChild(podaci_o_naslovu_izvornog_dela);



        Element vrsta_dela = document.createElement("vrsta_dela");
        vrsta_dela.setAttribute("property","pred:vrsta_dela");
        vrsta_dela.setAttribute("redni_broj","6");
        vrsta_dela.appendChild(document.createTextNode(a1.getVrsta_dela()));
        zahtev.appendChild(vrsta_dela);

        Element forma_dela = document.createElement("forma_dela");
        forma_dela.setAttribute("property","pred:forma_dela");
        forma_dela.setAttribute("redni_broj","7");
        forma_dela.appendChild(document.createTextNode(a1.getForma_dela()));
        zahtev.appendChild(forma_dela);

        Element podaci_o_autoru = document.createElement("podaci_o_autoru");
        podaci_o_autoru.setAttribute("redni_broj","8");
        for(Autor a :a1.getAutori()){
            Element autor = document.createElement("autor");
            autor.setAttribute("about","pred:autor");
            addAddress(autor, a.getAdresa());
            addContact(autor, a.getKontakt());
            Element ime = document.createElement("proj:ime");
            ime.setAttribute("property","pred:ime");
            ime.appendChild(document.createTextNode(a.getIme()));
            Element prezime = document.createElement("proj:prezime");
            prezime.setAttribute("property","pred:prezime");
            prezime.appendChild(document.createTextNode(a.getPrezime()));
            autor.appendChild(ime);
            autor.appendChild(prezime);
            Element godina_smrti = document.createElement("proj:godina_smrti");
            godina_smrti.appendChild(document.createTextNode(a.getGodina_smrti().toString()));
            autor.appendChild(godina_smrti);
            podaci_o_autoru.appendChild(autor);

        }
        zahtev.appendChild(podaci_o_autoru);

        Element delo_stvoreno_u_radnom_odnosu = document.createElement("delo_stvoreno_u_radnom_odnosu");
        delo_stvoreno_u_radnom_odnosu.setAttribute("property","pred:delo_stvoreno_u_radnom_odnosu");
        delo_stvoreno_u_radnom_odnosu.setAttribute("redni_broj","9");
        delo_stvoreno_u_radnom_odnosu.appendChild(document.createTextNode(a1.getDelo_stvoreno_u_radnom_odnosu().toString()));
        zahtev.appendChild(delo_stvoreno_u_radnom_odnosu);

        Element nacin_koriscenja_dela = document.createElement("nacin_koriscenja_dela");
        nacin_koriscenja_dela.setAttribute("property","pred:nacin_koriscenja_dela");
        nacin_koriscenja_dela.setAttribute("redni_broj","10");
        nacin_koriscenja_dela.appendChild(document.createTextNode(a1.getNacin_koriscenja_dela()));
        zahtev.appendChild(nacin_koriscenja_dela);

        Element prilozi_uz_zahtev = document.createElement("prilozi_uz_zahtev");
        prilozi_uz_zahtev.setAttribute("about","pred:prilozi_uz_zahtev");
        prilozi_uz_zahtev.setAttribute("redni_broj","12");
        Element opis_dela = document.createElement("opis_dela");
        opis_dela.setAttribute("property","pred:opis_dela");
        opis_dela.appendChild(document.createTextNode(a1.getPrilozi_uz_zahtev_opis_dela()));
        prilozi_uz_zahtev.appendChild(opis_dela);
        Element format_primera = document.createElement("format_primera");
        format_primera.setAttribute("property","pred:format_primera");
        format_primera.appendChild(document.createTextNode(a1.getPrilozi_uz_zahtev_format_primera()));
        prilozi_uz_zahtev.appendChild(format_primera);
        Element naziv_fajla = document.createElement("naziv_fajla");
        naziv_fajla.setAttribute("property","pred:naziv_fajla");
        naziv_fajla.appendChild(document.createTextNode(a1.getPrilozi_uz_zahtev_naziv_fajla()));
        prilozi_uz_zahtev.appendChild(naziv_fajla);
        zahtev.appendChild(prilozi_uz_zahtev);

        Element datum_podnosenja_zahteva = document.createElement("datum_podnosenja_zahteva");
        datum_podnosenja_zahteva.setAttribute("property","pred:datum");
        datum_podnosenja_zahteva.appendChild(document.createTextNode(a1.getDatum_podnosenja_zahteva()));
        zahtev.appendChild(datum_podnosenja_zahteva);

        Element sifra = document.createElement("sifra");
        sifra.setAttribute("property", "pred:sifra");
        sifra.appendChild(document.createTextNode(a1.getSifra()));
        zahtev.appendChild(sifra);



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
        postanskiBroj.appendChild(document.createTextNode(a.getPostanski_broj()));

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
        resenje.setAttribute("xmlns:a-1-r", "http://localhost:3030/a-1");
        resenje.setAttribute("xmlns:xsi", XSI_NAMESPACE);
        resenje.setAttribute("xmlns:pred", "http://www.xmlsux.com/predicate/");
        resenje.setAttribute("xsi:noNamespaceSchemaLocation", "file:./xsd/p1-resenje.xsd");

        Element broj_prijave = document.createElement("sifra");
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
