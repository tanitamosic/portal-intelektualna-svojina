<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:proj="http://localhost:3030/tipovi"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:fo="http://www.w3.org/1999/XSL/Format"
    exclude-result-prefixes="xs"
    version="2.0">

    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="A1">
                    <fo:region-body margin="0.75in" />
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:declarations>
                <x:xmpmeta xmlns:x="adobe:ns:meta/">
                    <rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#">
                        <rdf:Description rdf:about="" xmlns:dc="http://purl.org/dc/elements/1.1/">
                        <dc:title>Zahtev za unošenje u evidenciju i deponovanje autorskih prava</dc:title>
                        <dc:creator>Elita sa SIIT-a</dc:creator>
                        <dc:description>Zahtev za unošenje u evidenciju i deponovanje autorskih prava</dc:description>
                        </rdf:Description>
                    </rdf:RDF>
                </x:xmpmeta>
            </fo:declarations>

            <fo:page-sequence master-reference="A1">
                <fo:flow flow-name="xsl-region-body">
                    <fo:table width="100%" table-layout="fixed" border="1px solid black">
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="2">
                                    <fo:block font-weight="bold" linefeed-treatment="preserve">
                                        ZAVOD ZA INTELEKTUALNU SVOJINU
                                        
                                        Beograd, Knjeginje Ljubice 5

                                        OBRAZAC A-1
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="2">
                                    <fo:block font-weight="bold" text-align="center" font-size="15px" margin-top="10px" margin-bottom="10px" linefeed-treatment="preserve">
                                        
                                    

                                        ZAHTEV ZA UNOŠENJE U EVIDENCIJU I DEPONOVANJE AUTORSKIH DELA
                                        


                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            
                        </fo:table-body>
                        
                    </fo:table>
                    
                    <fo:table width="100%" table-layout="fixed" border="1px solid black">
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell padding="3px" border-left="1px solid black" number-columns-spanned="2">
                                    <fo:block linefeed-treatment="preserve">
                                        1) Podnosilac - ime, prezime, adresa i drzavljanstvo autora ili drugog nosioca autorskog
                                        prava ako je podnosilac fizicko lice, odnosno poslovno ime i sediste nosioca autorskog prava
                                        ako je podnosilac pravno lice:
                                        
                                        
                                        <xsl:if test="zahtev/podnosilac_zahteva/lice[@xsi:type='proj:TFizickoLice']">
                                            <xsl:value-of select="zahtev/podnosilac_zahteva/lice/proj:ime"/>
                                            <xsl:text>&#x20;</xsl:text>
                                            <xsl:value-of select="zahtev/podnosilac_zahteva/lice/proj:prezime"/>,
                                            <xsl:text>&#x20;</xsl:text>

                                            <xsl:value-of select="zahtev/podnosilac_zahteva/lice/proj:adresa/proj:*" separator="&#x20;"/>
                                            
                                        </xsl:if>

                                        <xsl:if test="zahtev/podnosilac_zahteva/lice[@xsi:type='proj:TPravnoLice']">
                                                <xsl:value-of select="zahtev/podnosilac_zahteva/lice/proj:naziv_preduzeca"/>,
                                                <xsl:text>&#x20;</xsl:text>
                                                <xsl:value-of select="zahtev/podnosilac_zahteva/lice/proj:pib"/>
                                        </xsl:if>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell border="1px solid black" padding="3px">
                                    <fo:block>
                                         telefon: <xsl:value-of select="zahtev/podnosilac_zahteva/lice/proj:kontakt/proj:telefon"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell border="1px solid black" padding="3px">
                                    <fo:block>
                                        e-mail: <xsl:value-of select="zahtev/podnosilac_zahteva/lice/proj:kontakt/proj:email"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>

                    <fo:table width="100%" table-layout="fixed" border="1px solid black">
                        <fo:table-body>
                            <fo:table-row>
                            <fo:table-cell padding="3px" border-left="1px solid black">
                                <fo:block linefeed-treatment="preserve">
                                    2) Pseudonim ili znak autora, (ako ga ima):
                                    
                                    <xsl:choose>
                                        <xsl:when test="zahtev/pseudonim_podnosioca">
                                            <xsl:value-of select="zahtev/pseudonim_podnosioca"/>
                                            
                                            
                                        </xsl:when>
                                        <xsl:otherwise>
                                            Autor nema pseudonim.
                                            
                                            
                                        </xsl:otherwise>
                                    </xsl:choose>
        
                                    3) Ime, prezime i adresa punomocnika ako se prijava podnosi preko punomocnika:
                                    <xsl:choose>
                                        <xsl:when test="zahtev/punomocnik/lice/proj:ime">
                                            

                                            <xsl:value-of select="zahtev/punomocnik/lice/proj:ime"/>
                                            <xsl:text>&#x20;</xsl:text>
                                            <xsl:value-of select="zahtev/punomocnik/lice/proj:prezime"/>,
                                            <xsl:text>&#x20;</xsl:text>
                                            
                                            <xsl:value-of select="zahtev/punomocnik/lice/proj:adresa/proj:*" separator="&#x20;"/>
                                            
                                            
                                        </xsl:when>
        
                                        <xsl:otherwise>
                                            Prijava se ne odnosi preko punomocnika.
                                            
                                            
                                        </xsl:otherwise>
                                    </xsl:choose>
        
                                    4) Naslov autorskog dela, odnosno alternativni naslov, ako ga ima, po kome autorsko delo
                                    moze da se identifikuje:
                                    
                                    
                                    <xsl:value-of select="zahtev/naslov_dela"/>
                                    
                                    
                                    5) Podaci o naslovu autorskog dela na kome se zasniva delo prerade, kao i podatak o autoru
                                    izvornog dela:
                                    
                                    
                                    <xsl:choose>
                                        <xsl:when test="zahtev/podaci_o_naslovu_izvonog_dela">
                                            Naslov:
                                            <xsl:value-of select="zahtev/podaci_o_naslovu_izvonog_dela/naslov"/>
                                            
                                            Autor:
                                            <xsl:value-of select="zahtev/podaci_o_naslovu_izvonog_dela/autor/proj:ime" />
                                            <xsl:value-of select="zahtev/podaci_o_naslovu_izvonog_dela/autor/proj:prezime" />
                                            
                                        </xsl:when>
                                        <xsl:otherwise>
                                            Autorsko delo nije zasnovano ni na jednom drugom delu.
                                            
                                            
                                        </xsl:otherwise>
                                    </xsl:choose>
        
                                    6) Podaci o vrsti autorskog dela (knjizevno delo, muzicko delo, likovno delo, racunarski
                                    program i dr.):
                                    
                                    
                                    <xsl:value-of select="zahtev/vrsta_dela"/>
                                    
                                    
        
                                    7) Podaci o formi zapisa autorskog dela (stampani tekst, opticki disk i slicno):
                                    
                                    
                                    <xsl:value-of select="zahtev/forma_dela"/>
                                    
                                    
        
                                    8) Podaci o autoru:
                                    
                                    <xsl:for-each select="zahtev/podaci_o_autoru/autor">
                                        <xsl:choose>
                                            <xsl:when test="proj:godina_smrti">
                                                Ime autora: <xsl:value-of select="proj:ime"/>
                                                Godina smrti: <xsl:value-of select="proj:godina_smrti"/>,
                                            </xsl:when>
                                            <xsl:when test="proj:ime">
                                                Ime i prezime: <xsl:value-of select="proj:ime"/><xsl:text>&#x20;</xsl:text><xsl:value-of select="proj:prezime"/>,
                                                Adresa: <xsl:value-of select="proj:adresa/proj:*" separator="&#x20;"/>,
                                            </xsl:when>
                                            <xsl:otherwise>
                                                Autor je anoniman.
                                            </xsl:otherwise>
                                        </xsl:choose>
                                    </xsl:for-each>
        
                                    9) Podatak da li je u pitanju autorsko delo stvoreno u radnom odnosu:
                                    
                                    
                                    <xsl:choose>
                                        <xsl:when test="zahtev/delo_stvoreno_u_radnom_odnosu">
                                            Autorsko delo je stvoreno u radnom odnosu.
                                        </xsl:when>
                                        <xsl:otherwise>
                                            Autorsko delo nije stvoreno u radnom odnosu.
                                        </xsl:otherwise>
                                    </xsl:choose>
                                    
                                    
        
                                    10) Nacin korišcenja ili nameravani nacin korišcenja autorskog dela:
                                    
                                    
                                    <xsl:value-of select="zahtev/nacin_koriscenja_dela"/>
                                    
                                    
                                </fo:block>
                                
                            </fo:table-cell>
                        </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                    
    
                    <fo:table width="100%" table-layout="fixed" border="1px solid black">
                        <fo:table-body>
                            <fo:table-row>
                            <fo:table-cell number-columns-spanned="3">
                                <fo:block width="100%">
                                    <fo:block text-align="center" font-weight="bold" margin-bottom="15px">POPUNJAVA ZAVOD</fo:block>
                                    <fo:block linefeed-treatment="preserve" font-weight="bold"><xsl:text>&#x20;&#x20;&#x20;</xsl:text>Prilozi uz prijavu:
                                    
                                    </fo:block>
                                    <fo:block linefeed-treatment="preserve">
                                        <!-- <xsl:element name="a">
                                        <xsl:attribute name="href"> http://localhost:8001/autorskaPrava/download/prilog/<xsl:value-of select="//aut:Putanja_do_opisa"/>
                                        </xsl:attribute>
                                            Opis autorskog dela
                                        </xsl:element> -->
                                        Opis autorskog dela <xsl:value-of select="zahtev/prilozi_uz_zahtev/opis_dela"/>
                                        
                                        <!-- <xsl:element name="a">
                                            <xsl:attribute name="href">
                                                http://localhost:8001/autorskaPrava/download/prilog/<xsl:value-of
                                                    select="//aut:Putanja_do_primera"/>
                                            </xsl:attribute>
                                            Primer autorskog dela
                                        </xsl:element> -->
                                        Primer autorskog dela: <xsl:value-of select="zahtev/prilozi_uz_zahtev/format_primera"/>
                                    </fo:block>
                                    
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                            <fo:table-cell><fo:block></fo:block></fo:table-cell>
                            <fo:table-cell><fo:block></fo:block></fo:table-cell>
                            <fo:table-cell>
                                <fo:block>
                                    <fo:table>
                                        <fo:table-body>
                                            <fo:table-row>
                                                <fo:table-cell border="1px solid black">
                                                    <fo:block linefeed-treatment="preserve">
                                                        Broj prijave:
                                                        <xsl:value-of select="/zahtev/@sifra"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                            </fo:table-row>
                                            <fo:table-row>
                                                <fo:table-cell border="1px solid black">
                                                    <fo:block linefeed-treatment="preserve">
                                                        Datum podnošenja:
                                                        <xsl:value-of select="zahtev/datum_podnosenja_zahteva"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                            </fo:table-row>
                                        </fo:table-body>
                                    </fo:table>
                                    
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                        </fo:table-body>
                        
                    </fo:table>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
