package com.xmlprojekat.dom;

import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 
 * Primer demonstrira metode API-ja za potrebe programskog kreiranja DOM stabla. 
 * Pored programskog kreiranja DOM stabla, primer demonstrira i serijalizaciju
 * DOM stabla na proizvoljan stream (npr. FileOutputStream, System.out, itd.).
 *
 */
public class DOMWriter {

	private static String Z1_NAMESPACE = "http://localhost:3030/z1";
	private static String P1_NAMESPACE = "http://localhost:3030/p1";
	private static String IMPORT_NAMESPACE = "http://localhost:3030/tipovi";

	private static String XSI_NAMESPACE = "http://www.w3.org/2001/XMLSchema-instance";

	private static DocumentBuilderFactory factory;

	private static TransformerFactory transformerFactory;

	private Document document;

	/*
	 * Factory initialization static-block
	 */
	static {
		factory = DocumentBuilderFactory.newInstance();

		transformerFactory = TransformerFactory.newInstance();
	}

	/**
	 * Generates document object model for a given XML file.
	 */
	public void createDocument() {

		try {

			DocumentBuilder builder = factory.newDocumentBuilder();

			// Kreiranje novog dokumenta 
			document = builder.newDocument();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generates sample document object model
	 * programmatically using DOM API methods.
	 */
	public Document generateZ1() {
		createDocument();

		Element zahtev = document.createElementNS(Z1_NAMESPACE, "zahtev");
		document.appendChild(zahtev);
		zahtev.setAttributeNS(XSI_NAMESPACE, "xsi:schemaLocation", "http://localhost:3030/z1 ./z-1.xsd");

		// PODNOSILAC
		Element podnosilac = document.createElementNS(Z1_NAMESPACE, "podnosilac");
		zahtev.appendChild(podnosilac);

		Element adresa = document.createElementNS(IMPORT_NAMESPACE, "adresa");
		podnosilac.appendChild(adresa);

		Element mesto = document.createElementNS(IMPORT_NAMESPACE, "mesto");
		adresa.appendChild(mesto);
		mesto.appendChild(document.createTextNode("Novi Sad"));

		Element postanskiBroj = document.createElementNS(IMPORT_NAMESPACE, "postanski_broj");
		adresa.appendChild(postanskiBroj);
		postanskiBroj.appendChild(document.createTextNode("21000"));

		Element ulica = document.createElementNS(IMPORT_NAMESPACE, "ulica");
		adresa.appendChild(ulica);
		ulica.appendChild(document.createTextNode("Sime Milosevica"));

		Element broj = document.createElementNS(IMPORT_NAMESPACE, "broj");
		adresa.appendChild(broj);
		broj.appendChild(document.createTextNode("9a"));

		Element kontakt = document.createElementNS(IMPORT_NAMESPACE, "kontakt");
		podnosilac.appendChild(kontakt);

		Element email = document.createElementNS(IMPORT_NAMESPACE, "email");
		kontakt.appendChild(document.createTextNode("mikimilic@gmail.com"));
		kontakt.appendChild(email);

		Element ime = document.createElementNS(IMPORT_NAMESPACE, "ime");
		ime.appendChild(document.createTextNode("Miki"));
		podnosilac.appendChild(ime);

		Element prezime = document.createElementNS(IMPORT_NAMESPACE, "prezime");
		ime.appendChild(document.createTextNode("Milic"));
		podnosilac.appendChild(prezime);

		// PUNOMOCNIK

		Element punomocnik = document.createElementNS(Z1_NAMESPACE, "podnosilac");
		zahtev.appendChild(punomocnik);

		Element adresa1 = document.createElementNS(IMPORT_NAMESPACE, "adresa");
		punomocnik.appendChild(adresa);

		Element mesto1 = document.createElementNS(Z1_NAMESPACE, "mesto");
		adresa.appendChild(mesto1);
		mesto.appendChild(document.createTextNode("Beograd"));

		Element postanskiBroj1 = document.createElementNS(Z1_NAMESPACE, "postanski_broj");
		adresa.appendChild(postanskiBroj1);
		postanskiBroj1.appendChild(document.createTextNode("11000"));

		Element ulica1 = document.createElementNS(Z1_NAMESPACE, "ulica");
		adresa.appendChild(ulica1);
		ulica1.appendChild(document.createTextNode("Suvoborska"));

		Element broj1 = document.createElementNS(Z1_NAMESPACE, "broj");
		adresa.appendChild(broj1);
		broj1.appendChild(document.createTextNode("115"));

		Element kontakt1 = document.createElementNS(IMPORT_NAMESPACE, "kontakt");
		punomocnik.appendChild(kontakt1);

		Element email1 = document.createElementNS(IMPORT_NAMESPACE, "email");
		kontakt.appendChild(document.createTextNode("kontakt@mikijevaagenicija.com"));
		kontakt.appendChild(email1);

		Element naziv = document.createElementNS(IMPORT_NAMESPACE, "naziv_preduzeca");
		naziv.appendChild(document.createTextNode("Mikijeva agencija"));
		punomocnik.appendChild(naziv);

		Element pib = document.createElementNS(IMPORT_NAMESPACE, "pib");
		pib.appendChild(document.createTextNode("741852963"));
		punomocnik.appendChild(pib);

		// PODACI O ZIGU
		Element zig = document.createElementNS(Z1_NAMESPACE, "podaci_o_zigu");
		zahtev.appendChild(zig);

		Element vrsta = document.createElementNS(Z1_NAMESPACE, "vrsta_ziga");
		vrsta.appendChild(document.createTextNode("Individualni"));
		zig.appendChild(vrsta);

		Element format = document.createElementNS(Z1_NAMESPACE, "format_ziga");
		format.appendChild(document.createTextNode("Grafički"));
		zig.appendChild(format);

		Element izgled = document.createElementNS(Z1_NAMESPACE, "izgled_ziga");
		izgled.appendChild(document.createTextNode("[link do slike]"));
		zig.appendChild(izgled);

		Element boja = document.createElementNS(Z1_NAMESPACE, "boja");
		boja.appendChild(document.createTextNode("Crvena, bela"));
		zig.appendChild(boja);

		Element opis = document.createElementNS(Z1_NAMESPACE, "opis");
		opis.appendChild(document.createTextNode("Velika latiniča slova M i A, međusobno preklopljena."));
		zig.appendChild(opis);

		Element k1 = document.createElementNS(Z1_NAMESPACE, "klasa");
		k1.appendChild(document.createTextNode("12"));
		zig.appendChild(k1);

		Element k2 = document.createElementNS(Z1_NAMESPACE, "klasa");
		k2.appendChild(document.createTextNode("13"));
		zig.appendChild(k2);

		Element prv = document.createElementNS(Z1_NAMESPACE, "pravo_prvenstva");
		prv.appendChild(document.createTextNode("Prvi smo se setili."));
		zig.appendChild(prv);

		// TAKSE
		Element takse = document.createElementNS(Z1_NAMESPACE, "takse");
		zahtev.appendChild(takse);

		Element osnovna = document.createElementNS(Z1_NAMESPACE, "osnovna_taksa");
		osnovna.appendChild(document.createTextNode("741.23"));
		takse.appendChild(osnovna);

		Element klase = document.createElementNS(Z1_NAMESPACE, "za_klase");
		klase.appendChild(document.createTextNode("7418.96"));
		takse.appendChild(klase);

		Element graf = document.createElementNS(Z1_NAMESPACE, "za_graficko_resenje");
		graf.appendChild(document.createTextNode("2413.34"));
		takse.appendChild(graf);

		// ZAVOD
		Element zavod = document.createElementNS(Z1_NAMESPACE, "zavod");
		zahtev.appendChild(zavod);

		Element prilozi = document.createElementNS(Z1_NAMESPACE, "prilozi");
		zavod.appendChild(prilozi);

		Element p1 = document.createElementNS(Z1_NAMESPACE, "prilog");
		p1.appendChild(document.createTextNode("Primerak znaka"));
		prilozi.appendChild(p1);

		Element p2 = document.createElementNS(Z1_NAMESPACE, "prilog");
		p2.appendChild(document.createTextNode("Spisak robe i usluga"));
		prilozi.appendChild(p2);

		Element brojPrijave = document.createElementNS(Z1_NAMESPACE, "broj_prijave");
		brojPrijave.appendChild(document.createTextNode("Ž 2019/0034"));
		zavod.appendChild(brojPrijave);

		Element datum = document.createElementNS(Z1_NAMESPACE, "datum_podnosenja");
		datum.appendChild(document.createTextNode("12.12.2019."));
		zavod.appendChild(datum);

		return document;
	}


	private void addAddress(Element parentNode, String city, String postNumber, String street, String streetNumber) {
		Element adresa = document.createElementNS(IMPORT_NAMESPACE, "adresa");
		parentNode.appendChild(adresa);

		Element mesto = document.createElementNS(IMPORT_NAMESPACE, "mesto");
		adresa.appendChild(mesto);
		mesto.appendChild(document.createTextNode(city));

		Element postanskiBroj = document.createElementNS(IMPORT_NAMESPACE, "postanski_broj");
		adresa.appendChild(postanskiBroj);
		postanskiBroj.appendChild(document.createTextNode(postNumber));

		Element ulica = document.createElementNS(IMPORT_NAMESPACE, "ulica");
		adresa.appendChild(ulica);
		ulica.appendChild(document.createTextNode(street));

		Element broj = document.createElementNS(IMPORT_NAMESPACE, "broj");
		adresa.appendChild(broj);
		broj.appendChild(document.createTextNode(streetNumber));


	}

	private void addContact(Element parentNode, String gmail) {
		Element kontakt = document.createElementNS(IMPORT_NAMESPACE, "kontakt");
		parentNode.appendChild(kontakt);

		Element email = document.createElementNS(IMPORT_NAMESPACE, "email");
		kontakt.appendChild(document.createTextNode(gmail));
		kontakt.appendChild(email);
	}

	public Document generateP1() {
		createDocument();

		Element zahtev = document.createElementNS(P1_NAMESPACE, "zahtev");
		document.appendChild(zahtev);
		zahtev.setAttributeNS(XSI_NAMESPACE, "xsi:schemaLocation", "http://localhost:3030/p1 ./p1.xsd");

		// ZAVOD
		Element zavod = document.createElementNS(P1_NAMESPACE, "zavod");
		zahtev.appendChild(zavod);

		// // FORMA ZA ZAVOD
		Element formaZaZavod = document.createElementNS(P1_NAMESPACE, "formaZaZavod");
		zavod.appendChild(formaZaZavod);

		Element brojPrijave = document.createElementNS(P1_NAMESPACE, "brojPrijave");
		formaZaZavod.appendChild(brojPrijave);
		brojPrijave.appendChild(document.createTextNode("П-2014\\0001"));
		Element datumPrijema = document.createElementNS(IMPORT_NAMESPACE, "datumPrijema");
		formaZaZavod.appendChild(datumPrijema);
		datumPrijema.appendChild(document.createTextNode("2.2.2014."));
		Element datumPodnosenja = document.createElementNS(IMPORT_NAMESPACE, "datumPodnosenja");
		formaZaZavod.appendChild(datumPodnosenja);
		datumPodnosenja.appendChild(document.createTextNode("1.1.2014."));

		// // PODACI O ZAVODU
		Element podaciOZavodu = document.createElementNS(P1_NAMESPACE, "podaciOZavodu");
		zavod.appendChild(podaciOZavodu);

		Element institucija = document.createElementNS(P1_NAMESPACE, "institucija");
		podaciOZavodu.appendChild(institucija);
		institucija.appendChild(document.createTextNode("Zavod za intelektualnu svojinu"));

		addAddress(podaciOZavodu, "Beograd", "11000", "Knjeginje Ljubice", "5");

		// FORMA PODNOSIOCA
		Element formaPodnosioca = document.createElementNS(P1_NAMESPACE, "formaPodnosioca");
		zahtev.appendChild(formaPodnosioca);

		// // NAZIV PRONALASKA
		Element nazivPronalaska = document.createElementNS(P1_NAMESPACE, "nazivPronalaska");
		formaPodnosioca.appendChild(nazivPronalaska);
		Element srpski = document.createElementNS(P1_NAMESPACE, "srpski");
		nazivPronalaska.appendChild(srpski);
		srpski.appendChild(document.createTextNode("Srpsko Ime"));
		Element engleski = document.createElementNS(P1_NAMESPACE, "engleski");
		nazivPronalaska.appendChild(engleski);
		engleski.appendChild(document.createTextNode("English Name"));

		// // PODNOSILAC PRIJAVE
		Element podnosilacPrijave = document.createElementNS(P1_NAMESPACE, "podnosilacPrijave");
		formaPodnosioca.appendChild(podnosilacPrijave);
		addAddress(podnosilacPrijave, "Beograd", "11000", "Nikole Tesle", "11a");
		addContact(podnosilacPrijave, "kirck@gmail.com");
		Element jePronalazac = document.createElementNS(P1_NAMESPACE, "jePronalazac");
		jePronalazac.appendChild(document.createTextNode("false"));
		podnosilacPrijave.appendChild(jePronalazac);

		// // PRONALAZAC
		Element pronalazac = document.createElementNS(P1_NAMESPACE, "pronalazac");
		formaPodnosioca.appendChild(pronalazac);
		addAddress(pronalazac, "Kragujevac", "34000", "Laze Lazarevica", "11a");
		addContact(pronalazac, "kirck@gmail.com");
		Element imePronalazaca = document.createElementNS(P1_NAMESPACE, "ime");
		Element prezimePronalazaca = document.createElementNS(P1_NAMESPACE, "prezime");
		imePronalazaca.appendChild(document.createTextNode("Pera"));
		prezimePronalazaca.appendChild(document.createTextNode("Peric"));
		pronalazac.appendChild(imePronalazaca);
		pronalazac.appendChild(prezimePronalazaca);

		// // POSREDNIK
		Element posrednik = document.createElementNS(P1_NAMESPACE, "posrednik");
		formaPodnosioca.appendChild(posrednik);
		addAddress(posrednik, "Pozarevac", "12000", "Radovana Dragovica", "24");
		addContact(posrednik, "posrednik@gmail.com");
		Element vrstaPosrednika = document.createElementNS(P1_NAMESPACE, "vrstaPosrednika");
		vrstaPosrednika.appendChild(document.createTextNode("Punomocnik za zastupanje"));
		posrednik.appendChild(vrstaPosrednika);

		// // ADRESA ZA DOSTAVLJANJE
		Element adresaZaDostavljanje = document.createElementNS(P1_NAMESPACE, "adresaZaDostavljanje");
		formaPodnosioca.appendChild(adresaZaDostavljanje);
		addAddress(adresaZaDostavljanje, "Novi Sad", "21000", "Kralja Petra", "7");

		// // NACIN DOSTAVLJANJA
		Element nacinDostavljanja = document.createElementNS(P1_NAMESPACE, "nacinDostavljanja");
		formaPodnosioca.appendChild(nacinDostavljanja);
		nacinDostavljanja.appendChild(document.createTextNode("Iskljucivo elektronskim putem u formi elektronskog dokumenta"));

		// // PRIJAVA
		Element prijava = document.createElementNS(P1_NAMESPACE, "prijava");
		formaPodnosioca.appendChild(prijava);
		Element vrstaPrijave = document.createElementNS(P1_NAMESPACE, "vrstaPrijave");
		prijava.appendChild(vrstaPrijave);
		vrstaPrijave.appendChild(document.createTextNode("Dopunska prijava"));
		Element brojPrvobitnePrijave = document.createElementNS(P1_NAMESPACE, "brojPrvobitnePrijave");
		prijava.appendChild(brojPrvobitnePrijave);
		brojPrvobitnePrijave.appendChild(document.createTextNode("П-2014\\0010"));
		Element datumPodnosenjaPrvobitnePrijave = document.createElementNS(P1_NAMESPACE, "datumPodnosenjaPrvobitnePrijave");
		prijava.appendChild(datumPodnosenjaPrvobitnePrijave);
		datumPodnosenjaPrvobitnePrijave.appendChild(document.createTextNode("7.7.2014."));

		// // ZAHTEV ZA PRIZNANJE PRAVA
		Element zahtevZaPriznanjePrava = document.createElementNS(P1_NAMESPACE, "zahtevZaPriznanjePrava");
		formaPodnosioca.appendChild(zahtevZaPriznanjePrava);
		Element prijave = document.createElementNS(P1_NAMESPACE, "prijave");
		zahtevZaPriznanjePrava.appendChild(prijave);

		Element ranijaPrijava1 = document.createElementNS(P1_NAMESPACE, "ranijaPrijava1");
		prijave.appendChild(ranijaPrijava1);
		Element datum1 = document.createElementNS(IMPORT_NAMESPACE, "datum");
		Element brojPrijave1 = document.createElementNS(P1_NAMESPACE, "brojPrijave");
		Element drzavaOrg1 = document.createElementNS(P1_NAMESPACE, "drzavaIliOrganizacija");
		datum1.appendChild(document.createTextNode("6.6.2013."));
		brojPrijave1.appendChild(document.createTextNode("П-2013\\0011"));
		drzavaOrg1.appendChild(document.createTextNode("Finska"));
		ranijaPrijava1.appendChild(datum1);
		ranijaPrijava1.appendChild(brojPrijave1);
		ranijaPrijava1.appendChild(drzavaOrg1);

		Element ranijaPrijava2 = document.createElementNS(P1_NAMESPACE, "ranijaPrijava2");
		prijave.appendChild(ranijaPrijava2);
		Element datum2 = document.createElementNS(IMPORT_NAMESPACE, "datum");
		Element brojPrijave2 = document.createElementNS(P1_NAMESPACE, "brojPrijave");
		Element drzavaOrg2 = document.createElementNS(P1_NAMESPACE, "drzavaIliOrganizacija");
		datum1.appendChild(document.createTextNode("5.5.2012."));
		brojPrijave1.appendChild(document.createTextNode("П-2012\\0012"));
		drzavaOrg1.appendChild(document.createTextNode("Norveska"));
		ranijaPrijava2.appendChild(datum2);
		ranijaPrijava2.appendChild(brojPrijave2);
		ranijaPrijava2.appendChild(drzavaOrg2);

		Element podaciOOstalimPravimaNaListu2 = document.createElementNS(P1_NAMESPACE, "podaciOOstalimPravimaNaListu2");
		podaciOOstalimPravimaNaListu2.appendChild(document.createTextNode("false"));
		zahtevZaPriznanjePrava.appendChild(podaciOOstalimPravimaNaListu2);
		return document;
	}

	public Document generateA1() {
		createDocument();

		Element zahtev = document.createElement("zahtev");

		zahtev.setAttribute("xmlns:proj", "http://localhost:3030/tipovi");
		zahtev.setAttribute("xmlns:a-1", "http://localhost:3030/a-1");
		zahtev.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		zahtev.setAttribute("xsi:noNamespaceSchemaLocation", "file:./a-1.xsd");
		zahtev.setAttribute("sifra", "A-0001");

		document.appendChild(zahtev);

		Element podnosilac_zahteva = document.createElement("podnosilac_zahteva");
		podnosilac_zahteva.setAttribute("redni_broj", "1");
		Element podnosilac_zahteva_lice = document.createElement("lice");
		podnosilac_zahteva_lice.setAttribute("xsi:type", "proj:TPravnoLice");
		Element podnosilac_zahteva_lice_adresa = document.createElement("proj:adresa");
		Element podnosilac_zahteva_lice_adresa_mesto = document.createElement("proj:mesto");
		podnosilac_zahteva_lice_adresa_mesto.setTextContent("Jupiter");
		Element podnosilac_zahteva_lice_adresa_postanski_broj = document.createElement("proj:postanski_broj");
		podnosilac_zahteva_lice_adresa_postanski_broj.setTextContent("24000");
		Element podnosilac_zahteva_lice_adresa_ulica = document.createElement("proj:ulica");
		podnosilac_zahteva_lice_adresa_ulica.setTextContent("Great Red Spot");
		Element podnosilac_zahteva_lice_adresa_broj = document.createElement("proj:broj");
		podnosilac_zahteva_lice_adresa_broj.setTextContent("42");
		podnosilac_zahteva_lice_adresa.appendChild(podnosilac_zahteva_lice_adresa_mesto);
		podnosilac_zahteva_lice_adresa.appendChild(podnosilac_zahteva_lice_adresa_postanski_broj);
		podnosilac_zahteva_lice_adresa.appendChild(podnosilac_zahteva_lice_adresa_ulica);
		podnosilac_zahteva_lice_adresa.appendChild(podnosilac_zahteva_lice_adresa_broj);
		Element podnosilac_zahteva_lice_kontakt = document.createElement("proj:kontakt");
		Element podnosilac_zahteva_lice_kontakt_email = document.createElement("proj:email");
		podnosilac_zahteva_lice_kontakt_email.setTextContent("contact@cabbage.corp");
		podnosilac_zahteva_lice_kontakt.appendChild(podnosilac_zahteva_lice_kontakt_email);
		Element podnosilac_zahteva_lice_naziv_preduzeca = document.createElement("proj:naziv_preduzeca");
		podnosilac_zahteva_lice_naziv_preduzeca.setTextContent("Cabbage Corp Ltd.");
		Element podnosilac_zahteva_lice_pib = document.createElement("proj:pib");
		podnosilac_zahteva_lice_pib.setTextContent("999999999");
		podnosilac_zahteva_lice.appendChild(podnosilac_zahteva_lice_adresa);
		podnosilac_zahteva_lice.appendChild(podnosilac_zahteva_lice_kontakt);
		podnosilac_zahteva_lice.appendChild(podnosilac_zahteva_lice_naziv_preduzeca);
		podnosilac_zahteva_lice.appendChild(podnosilac_zahteva_lice_pib);
		podnosilac_zahteva.appendChild(podnosilac_zahteva_lice);


		Element pseudonim_podnosioca = document.createElement("pseudonim_podnosioca");
		pseudonim_podnosioca.setAttribute("redni_broj", "2");
		pseudonim_podnosioca.setTextContent("21 Cabbage");


		Element punomocnik = document.createElement("punomocnik");
		punomocnik.setAttribute("redni_broj", "3");
		Element punomocnik_lice = document.createElement("lice");
		punomocnik_lice.setAttribute("xsi:type", "proj:TFizickoLice");
		Element punomocnik_lice_adresa = document.createElement("proj:adresa");
		Element punomocnik_lice_adresa_mesto = document.createElement("proj:mesto");
		punomocnik_lice_adresa_mesto.setTextContent("Novi Sad");
		Element punomocnik_lice_adresa_postanski_broj = document.createElement("proj:postanski_broj");
		punomocnik_lice_adresa_postanski_broj.setTextContent("21000");
		Element punomocnik_lice_adresa_ulica = document.createElement("proj:ulica");
		punomocnik_lice_adresa_ulica.setTextContent("Kraljevica Marka");
		Element punomocnik_lice_adresa_broj = document.createElement("proj:broj");
		punomocnik_lice_adresa_broj.setTextContent("12/42");
		punomocnik_lice_adresa.appendChild(punomocnik_lice_adresa_mesto);
		punomocnik_lice_adresa.appendChild(punomocnik_lice_adresa_postanski_broj);
		punomocnik_lice_adresa.appendChild(punomocnik_lice_adresa_ulica);
		punomocnik_lice_adresa.appendChild(punomocnik_lice_adresa_broj);
		Element punomocnik_lice_kontakt = document.createElement("proj:kontakt");
		Element punomocnik_lice_kontakt_telefon = document.createElement("proj:telefon");
		punomocnik_lice_kontakt_telefon.setTextContent("+381656524299");
		Element punomocnik_lice_kontakt_email = document.createElement("proj:email");
		punomocnik_lice_kontakt_email.setTextContent("petar.kupusarevic@gmail.com");
		punomocnik_lice_kontakt.appendChild(punomocnik_lice_kontakt_telefon);
		punomocnik_lice_kontakt.appendChild(punomocnik_lice_kontakt_email);
		Element punomocnik_lice_ime = document.createElement("proj:ime");
		punomocnik_lice_ime.setTextContent("Petar");
		Element punomocnik_lice_prezime = document.createElement("proj:prezime");
		punomocnik_lice_prezime.setTextContent("Kupusarevic");
		punomocnik_lice.appendChild(punomocnik_lice_adresa);
		punomocnik_lice.appendChild(punomocnik_lice_kontakt);
		punomocnik_lice.appendChild(punomocnik_lice_ime);
		punomocnik_lice.appendChild(punomocnik_lice_prezime);
		punomocnik.appendChild(punomocnik_lice);


		Element naslov_dela = document.createElement("naslov_dela");
		naslov_dela.setAttribute("redni_broj", "4");
		naslov_dela.setTextContent("How to scam people");

		Element vrsta_dela = document.createElement("vrsta_dela");
		vrsta_dela.setAttribute("redni_broj", "6");
		vrsta_dela.setTextContent("Informaciono");

		Element forma_dela = document.createElement("forma_dela");
		forma_dela.setAttribute("redni_broj", "7");
		forma_dela.setTextContent("Knjiga");

		Element podaci_o_autoru = document.createElement("podaci_o_autoru");
		podaci_o_autoru.setAttribute("redni_broj", "8");
		Element podaci_o_autoru_autor1 = document.createElement("autor");
		Element podaci_o_autoru_autor1_adresa = document.createElement("proj:adresa");
		Element podaci_o_autoru_autor1_adresa_mesto = document.createElement("proj:mesto");
		podaci_o_autoru_autor1_adresa_mesto.setTextContent("Novi Sad");
		Element podaci_o_autoru_autor1_adresa_postanski_broj = document.createElement("proj:postanski_broj");
		podaci_o_autoru_autor1_adresa_postanski_broj.setTextContent("21000");
		Element podaci_o_autoru_autor1_adresa_ulica = document.createElement("proj:ulica");
		podaci_o_autoru_autor1_adresa_ulica.setTextContent("Kraljevica Marka");
		Element podaci_o_autoru_autor1_adresa_broj = document.createElement("proj:broj");
		podaci_o_autoru_autor1_adresa_broj.setTextContent("12/42");
		podaci_o_autoru_autor1_adresa.appendChild(podaci_o_autoru_autor1_adresa_mesto);
		podaci_o_autoru_autor1_adresa.appendChild(podaci_o_autoru_autor1_adresa_postanski_broj);
		podaci_o_autoru_autor1_adresa.appendChild(podaci_o_autoru_autor1_adresa_ulica);
		podaci_o_autoru_autor1_adresa.appendChild(podaci_o_autoru_autor1_adresa_broj);
		Element podaci_o_autoru_autor1_kontakt = document.createElement("proj:kontakt");
		Element podaci_o_autoru_autor1_kontakt_telefon = document.createElement("proj:telefon");
		podaci_o_autoru_autor1_kontakt_telefon.setTextContent("+381656524299");
		Element podaci_o_autoru_autor1_kontakt_email = document.createElement("proj:email");
		podaci_o_autoru_autor1_kontakt_email.setTextContent("petar.kupusarevic@gmail.com");
		podaci_o_autoru_autor1_kontakt.appendChild(podaci_o_autoru_autor1_kontakt_telefon);
		podaci_o_autoru_autor1_kontakt.appendChild(podaci_o_autoru_autor1_kontakt_email);
		Element podaci_o_autoru_autor1_ime = document.createElement("proj:ime");
		podaci_o_autoru_autor1_ime.setTextContent("Petar");
		Element podaci_o_autoru_autor1_prezime = document.createElement("proj:prezime");
		podaci_o_autoru_autor1_prezime.setTextContent("Kupusarevic");
		podaci_o_autoru_autor1.appendChild(podaci_o_autoru_autor1_adresa);
		podaci_o_autoru_autor1.appendChild(podaci_o_autoru_autor1_kontakt);
		podaci_o_autoru_autor1.appendChild(podaci_o_autoru_autor1_ime);
		podaci_o_autoru_autor1.appendChild(podaci_o_autoru_autor1_prezime);
		podaci_o_autoru.appendChild(podaci_o_autoru_autor1);
		Element podaci_o_autoru_autor2 = document.createElement("autor");
		Element podaci_o_autoru_autor2_adresa = document.createElement("proj:adresa");
		Element podaci_o_autoru_autor2_adresa_mesto = document.createElement("proj:mesto");
		podaci_o_autoru_autor2_adresa_mesto.setTextContent("Novi Sad");
		Element podaci_o_autoru_autor2_adresa_postanski_broj = document.createElement("proj:postanski_broj");
		podaci_o_autoru_autor2_adresa_postanski_broj.setTextContent("21000");
		Element podaci_o_autoru_autor2_adresa_ulica = document.createElement("proj:ulica");
		podaci_o_autoru_autor2_adresa_ulica.setTextContent("Kraljevica Marka");
		Element podaci_o_autoru_autor2_adresa_broj = document.createElement("proj:broj");
		podaci_o_autoru_autor2_adresa_broj.setTextContent("12/42");
		podaci_o_autoru_autor2_adresa.appendChild(podaci_o_autoru_autor2_adresa_mesto);
		podaci_o_autoru_autor2_adresa.appendChild(podaci_o_autoru_autor2_adresa_postanski_broj);
		podaci_o_autoru_autor2_adresa.appendChild(podaci_o_autoru_autor2_adresa_ulica);
		podaci_o_autoru_autor2_adresa.appendChild(podaci_o_autoru_autor2_adresa_broj);
		Element podaci_o_autoru_autor2_kontakt = document.createElement("proj:kontakt");
		Element podaci_o_autoru_autor2_kontakt_email = document.createElement("proj:email");
		podaci_o_autoru_autor2_kontakt_email.setTextContent("petrov_drug_pauk@gmail.com");
		podaci_o_autoru_autor2_kontakt.appendChild(podaci_o_autoru_autor2_kontakt_email);
		Element podaci_o_autoru_autor2_ime = document.createElement("proj:ime");
		podaci_o_autoru_autor2_ime.setTextContent("Pauk");
		Element podaci_o_autoru_autor2_prezime = document.createElement("proj:prezime");
		podaci_o_autoru_autor2_prezime.setTextContent("Kupusarevic");
		podaci_o_autoru_autor2.appendChild(podaci_o_autoru_autor2_adresa);
		podaci_o_autoru_autor2.appendChild(podaci_o_autoru_autor2_kontakt);
		podaci_o_autoru_autor2.appendChild(podaci_o_autoru_autor2_ime);
		podaci_o_autoru_autor2.appendChild(podaci_o_autoru_autor2_prezime);
		podaci_o_autoru.appendChild(podaci_o_autoru_autor2);


		Element nacin_koriscenja_dela = document.createElement("nacin_koriscenja_dela");
		nacin_koriscenja_dela.setAttribute("redni_broj", "10");
		nacin_koriscenja_dela.setTextContent("Citati.... naravno..");

		Element datum_podnosenja_zahteva = document.createElement("datum_podnosenja_zahteva");
		datum_podnosenja_zahteva.setTextContent("11.12.2022.");

		zahtev.appendChild(podaci_o_autoru);
		zahtev.appendChild(pseudonim_podnosioca);
		zahtev.appendChild(punomocnik);
		zahtev.appendChild(naslov_dela);
		zahtev.appendChild(vrsta_dela);
		zahtev.appendChild(forma_dela);
		zahtev.appendChild(podaci_o_autoru);
		zahtev.appendChild(nacin_koriscenja_dela);
		zahtev.appendChild(datum_podnosenja_zahteva);


		return document;
	}

	/**
	 * Serializes DOM tree to an arbitrary OutputStream.
	 */
	public void transform(OutputStream out) {
		try {

			// Kreiranje instance objekta zaduzenog za serijalizaciju DOM modela
			Transformer transformer = transformerFactory.newTransformer();

			// Indentacija serijalizovanog izlaza
			transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			// Nad "source" objektom (DOM stablo) vrši se transformacija
			DOMSource source = new DOMSource(document);

			// Rezultujući stream (argument metode) 
			StreamResult result = new StreamResult(out);

			// Poziv metode koja vrši opisanu transformaciju
			transformer.transform(source, result);

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}