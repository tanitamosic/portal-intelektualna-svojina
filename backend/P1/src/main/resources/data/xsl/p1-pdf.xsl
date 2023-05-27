<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:proj="http://localhost:3030/tipovi"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:fo="http://www.w3.org/1999/XSL/Format"
    exclude-result-prefixes="xs" version="2.0">

    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="P1">
                    <fo:region-body margin="0.75in" />
                </fo:simple-page-master>
            </fo:layout-master-set>
            
            <fo:declarations>
                <x:xmpmeta xmlns:x="adobe:ns:meta/">
                    <rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#">
                        <rdf:Description rdf:about="" xmlns:dc="http://purl.org/dc/elements/1.1/">
                        <dc:title>Zahtev za priznanje patenta</dc:title>
                        <dc:creator>Elita sa SIIT-a</dc:creator>
                        <dc:description>Zahtev za priznanje patenta</dc:description>
                        </rdf:Description>
                    </rdf:RDF>
                </x:xmpmeta>
            </fo:declarations>
            

            <fo:page-sequence master-reference="P1">
                <fo:flow flow-name="xsl-region-body">

                    <fo:table table-layout="fixed" width="66%" 
                        margin="10px auto 10px auto" border="3px solid black" border-collapse="collapse">
                        <fo:table-column column-width="33%" />
                        <fo:table-column column-width="33%" />
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell padding="3px" number-columns-spanned="2" text-align="center" border-top="1px solid black" border-left="1px solid black" border-bottom="1px solid black">
                                    <fo:block>Popunjava zavod</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="3px" text-align="left" border-left="1px solid black" border-bottom="1px solid black" border-right="1px solid black"><fo:block>Broj prijave</fo:block></fo:table-cell>
                                <fo:table-cell padding="3px" text-align="left" border-bottom="1px solid black" border-right="1px solid black">
                                    <fo:block><xsl:value-of select="zahtev/zavod/formaZaZavod/brojPrijave"/></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="3px" text-align="left" border-left="1px solid black" border-bottom="1px solid black" border-right="1px solid black"><fo:block>Datum prijema</fo:block></fo:table-cell>
                                <fo:table-cell padding="3px" text-align="left" border-bottom="1px solid black" border-right="1px solid black">
                                    <fo:block><xsl:value-of select="zahtev/zavod/formaZaZavod/datumPrijema"/></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="3px" text-align="left" border-left="1px solid black" border-bottom="1px solid black" border-right="1px solid black"><fo:block>Priznati datum podnosenja</fo:block></fo:table-cell>
                                <fo:table-cell padding="3px" text-align="left" border-bottom="1px solid black" border-right="1px solid black">
                                    <fo:block><xsl:value-of select="zahtev/zavod/formaZaZavod/datumPodnosenja"/></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="3px" text-align="left" border-left="1px solid black" border-bottom="1px solid black" border-right="1px solid black"><fo:block>Pecat i potpis</fo:block></fo:table-cell>
                                <fo:table-cell padding="3px" text-align="left" border-bottom="1px solid black" border-right="1px solid black"><fo:block></fo:block></fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                            
                    </fo:table>
                    
                    <fo:block linefeed-treatment="preserve">
                        <xsl:text>Republika Srbija </xsl:text>
                        <xsl:text>&#10;</xsl:text>
                        <xsl:text>Zavod za intelektualnu svojinu </xsl:text>
                        <xsl:text>&#10;</xsl:text>
                        <xsl:text>Kneginje Ljubice broj 5 </xsl:text>
                        <xsl:text>&#10;</xsl:text>
                        <xsl:text>11000 Beograd </xsl:text>
                        <xsl:text>&#10;</xsl:text>
                    </fo:block>
                    <fo:block linefeed-treatment="preserve" font-size="16px" font-weight="bold" text-align="center">
                        <xsl:text >ZAHTEV &#xd;&#xa; ZA PRIZNANJE PATENTA </xsl:text>
                    </fo:block>

                    <fo:table border-collapse="collapse" table-layout="fixed" width="100%" 
                    margin="10px auto 10px auto" border="3px solid black">
                    <fo:table-body>
                        <fo:table-row>
                            <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" number-columns-spanned="3" border-top="1px solid black" border-left="1px solid black"><fo:block>Polje broj I NAZIV PRONALASKA</fo:block></fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                            <fo:table-cell padding="3px" border-left="1px solid black" border-bottom="1px solid black" number-columns-spanned="3">
                                <fo:block linefeed-treatment="preserve">Na srpskom jeziku: <xsl:value-of select="zahtev/formaPodnosioca/nazivPronalaska/srpski" />
                                        Na engleskom jeziku: <xsl:value-of select="zahtev/formaPodnosioca/nazivPronalaska/engleski" />
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                            <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" number-columns-spanned="3" padding="3px" border-left="1px solid black">
                                <fo:block font-weight="bold">Polje broj II PODNOSILAC PRIJAVE</fo:block>
                            
                                <xsl:if test="zahtev/formaPodnosioca/podnosilacPrijave/jePronalazac = 'true'">
                                        <fo:block>Podnosilac prijave je pronalazac</fo:block>
                                </xsl:if>
                                <xsl:if test="zahtev/formaPodnosioca/podnosilacPrijave/jePronalazac != 'true'">
                                        <fo:block>Podnosilac prijave nije pronalazac</fo:block>
                                </xsl:if>
                            </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                            <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" padding="3px" number-rows-spanned="3" border-left="1px solid black">
                                <fo:block>
                                    Ime i prezime / Poslovno ime 
                                    <xsl:text>&#xd;&#xa;</xsl:text>
                                    <xsl:value-of select="zahtev/formaPodnosioca/podnosilacPrijave/proj:ime" /> <xsl:value-of select="zahtev/formaPodnosioca/podnosilacPrijave/proj:prezime" />
                                    <xsl:value-of select="zahtev/formaPodnosioca/podnosilacPrijave/proj:naziv_preduzeca" /> <xsl:value-of select="zahtev/formaPodnosioca/podnosilacPrijave/proj:pib" />
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" padding="3px" number-rows-spanned="3" >
                                <fo:block> Ulica i broj, poštanski broj, mesto i država <xsl:text>&#xd;&#xa;</xsl:text>
                                    <xsl:text>&#xd;&#xa;</xsl:text>
                                    <xsl:value-of select="zahtev/formaPodnosioca/podnosilacPrijave/proj:adresa/*" separator=",&#x20;" />
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" padding="3px"><fo:block>Broj telefona <xsl:text>&#xd;&#xa;</xsl:text>
                                <xsl:value-of select="zahtev/formaPodnosioca/podnosilacPrijave/proj:kontakt/proj:telefon" /></fo:block> 
                            </fo:table-cell>
                            
                            
                        </fo:table-row>
                        <fo:table-row>
                            <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" padding="3px"><fo:block>Broj faksa <xsl:text>&#xd;&#xa;</xsl:text>
                                <xsl:value-of select="zahtev/formaPodnosioca/podnosilacPrijave/proj:kontakt/proj:faks" /></fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                            <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" padding="3px"><fo:block>E-pošta <xsl:text>&#xd;&#xa;</xsl:text>
                                <xsl:value-of select="zahtev/formaPodnosioca/podnosilacPrijave/proj:kontakt/proj:email" /></fo:block> 
                            </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row><fo:table-cell><fo:block></fo:block></fo:table-cell>
                        <!-- <fo:table-cell><fo:block></fo:block></fo:table-cell> -->
                        <!-- <fo:table-cell padding="3px" number-columns-spanned="2" border-left="1px solid black"><fo:block>Drzavljanstvo: <xsl:value-of
                                select="//pat:Podaci_o_podnosiocu_prijave//pat:Drzavljanstvo" /></fo:block>
                        </fo:table-cell> -->
                        </fo:table-row>
                    </fo:table-body>
                        
                    </fo:table>

                    <xsl:text>&#xd;&#xa;</xsl:text>

                    <fo:table border-collapse="collapse" table-layout="fixed" width="100%" 
                    margin="10px auto 10px auto" border="3px solid black">
                    <fo:table-body><fo:table-row>
                            <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" padding="3px" number-columns-spanned="3" border-left="1px solid black" border-top="1px solid black">
                                <fo:block font-weight="bold">
                                    Polje broj III PRONALAZAC
                                    <xsl:if test="zahtev/formaPodnosioca/pronalazac/zeliBitiNaveden = 'false'"> Pronalazac ne zeli da bude naveden u prijavi </xsl:if>
                                    <xsl:if test="zahtev/formaPodnosioca/pronalazac/zeliBitiNaveden = 'true'"> Pronalazac zeli da bude naveden u prijavi </xsl:if>
                                </fo:block>
                                
                            </fo:table-cell>
                        </fo:table-row>
                        <xsl:if test="zahtev/formaPodnosioca/pronalazac/zeliBitiNaveden = 'true'">
                            <fo:table-row>
                                <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" padding="3px" number-rows-spanned="3" border-left="1px solid black">
                                    <fo:block>
                                        Ime i Prezime
                                        <xsl:text>&#xd;&#xa;</xsl:text>
                                        <xsl:text>&#xd;&#xa;</xsl:text>
                                        <xsl:value-of select="zahtev/formaPodnosioca/pronalazac/proj:ime" />&#160; 
                                        <xsl:value-of select="zahtev/formaPodnosioca/pronalazac/proj:prezime" />
                                    </fo:block>
                                    
                                </fo:table-cell>
                                <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" padding="3px" number-rows-spanned="3">
                                    <fo:block>Ulica i broj, poštanski broj, mesto i država
                                        <xsl:text>&#xd;&#xa;</xsl:text>
                                        <xsl:text>&#xd;&#xa;</xsl:text>
                                        <xsl:value-of select="zahtev/formaPodnosioca/pronalazac/proj:adresa/*" separator=",&#xa;" />
                                    </fo:block>
                                   
                                </fo:table-cell>
                                <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" padding="3px">
                                    <fo:block>Broj telefona <xsl:text>&#xd;&#xa;</xsl:text>
                                    <xsl:value-of
                                        select="zahtev/formaPodnosioca/pronalazac/proj:kontakt/proj:telefon" /></fo:block> 
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" padding="3px">
                                    <fo:block>Broj faksa <xsl:text>&#xd;&#xa;</xsl:text>
                                    <xsl:value-of
                                        select="zahtev/formaPodnosioca/pronalazac/proj:kontakt/proj:faks" /></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" padding="3px"><fo:block>E-pošta <xsl:text>&#xd;&#xa;</xsl:text>
                                    <xsl:value-of
                                        select="zahtev/formaPodnosioca/pronalazac/proj:kontakt/proj:email" /></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </xsl:if>
                                
                            <!-- <fo:table-row>
                                <fo:table-cell colspan="3" style="border-left-width:1px;">
                                    <xsl:choose>
                                        <xsl:when test="//pat:Putanja_do_primera">
                                            <xsl:element name="a">
                                                <xsl:attribute name="href">
        http://localhost:8000/patent/download/prilog/<xsl:value-of select="//pat:Putanja_do_primera" />
                                                </xsl:attribute>
        Izjava podnosilaca prijave o osnovu sticanja prava </xsl:element>
                                        </xsl:when>
                                        <xsl:otherwise> </xsl:otherwise>
                                    </xsl:choose>
                                </fo:table-cell>
                            </fo:table-row> -->
                        <!-- </xsl:if> --></fo:table-body>
                        
                    </fo:table>

                    <xsl:text>&#xd;&#xa;</xsl:text>

                    <fo:table border-collapse="collapse" table-layout="fixed" width="100%" 
                    margin="10px auto 10px auto" border="3px solid black">
                    <fo:table-body>
                        <fo:table-row>
                            <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" padding="3px" number-columns-spanned="3" border-left="1px solid black" border-top="1px solid black">
                                <fo:block font-weight="bold" linefeed-treatment="preserve">
                                    Polje broj IV
                                    <xsl:value-of select="zahtev/formaPodnosioca/posrednik/vrstaPosrednika" />
                                    <xsl:text>&#xd;&#xa;</xsl:text>
                                    Zajednicki predstavnik: 
                                    <xsl:if test="zahtev/formaPodnosioca/posrednik/zajednickiPredstavnik = 'true'"> DA </xsl:if>
                                    <xsl:if test="zahtev/formaPodnosioca/posrednik/zajednickiPredstavnik = 'false'"> NE </xsl:if></fo:block>
                                
                            </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                            <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" padding="3px" number-rows-spanned="2" border-left="1px solid black">
                                <fo:block>
                                    Ime i Prezime / Poslovno ime: 
                                    <xsl:value-of select="zahtev/formaPodnosioca/podnosilacPrijave/proj:ime" /> <xsl:value-of select="zahtev/formaPodnosioca/podnosilacPrijave/proj:prezime" />
                                    <xsl:value-of select="zahtev/formaPodnosioca/podnosilacPrijave/proj:naziv_preduzeca" /> <xsl:value-of select="zahtev/formaPodnosioca/podnosilacPrijave/proj:pib" />
                                </fo:block>
                                
                            </fo:table-cell>
                            <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" padding="3px" number-rows-spanned="2">
                                <fo:block> Ulica i broj, poštanski broj, mesto i država <xsl:text>&#xd;&#xa;</xsl:text>
                                <xsl:text>&#xd;&#xa;</xsl:text>
                                <xsl:value-of select="zahtev/formaPodnosioca/posrednik/proj:adresa/*" separator=",&#x20;"/></fo:block>
                            </fo:table-cell>
                            <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" padding="3px"><fo:block> Broj telefona <xsl:text>&#xd;&#xa;</xsl:text>
                                <xsl:value-of
                                    select="zahtev/formaPodnosioca/posrednik/proj:kontakt/proj:telefon" /></fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                            <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" padding="3px"><fo:block> E-pošta <xsl:text>&#xd;&#xa;</xsl:text>
                                <xsl:value-of
                                    select="zahtev/formaPodnosioca/posrednik/proj:kontakt/proj:email" /></fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                    </fo:table-body>
                        
                    </fo:table>
                    <fo:table border-collapse="collapse" table-layout="fixed" width="100%" 
                    margin="10px auto 10px auto" border="3px solid black">
                    <fo:table-body>
                        <fo:table-row>
                            <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" padding="3px" border-left="1px solid black"><fo:block>Polje broj V ADRESA ZA DOSTAVLJANJA</fo:block></fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                            <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" padding="3px" border-left="1px solid black"><fo:block>Ulica i broj, poštanski broj i mesto: <xsl:text>&#xd;&#xa;</xsl:text>
                                <xsl:text>&#xd;&#xa;</xsl:text>
                                <xsl:value-of select="zahtev/formaPodnosioca/adresaZaDostavljanje/proj:adresa/proj:*" separator=",&#xa;"/>
                                <xsl:text>&#x20;</xsl:text></fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                    </fo:table-body>
                        
                    </fo:table>
                    <fo:table border-collapse="collapse" table-layout="fixed" width="100%" 
                    margin="10px auto 10px auto" border="3px solid black">
                        <fo:table-body>
                            <fo:table-row>
                            <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" padding="3px" border-left="1px solid black"><fo:block>Polje broj VI NACIN DOSTAVLJANJA</fo:block></fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                            <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" padding="3px" border-left="1px solid black">
                                <fo:block>
                                    <xsl:value-of select="zahtev/formaPodnosioca/nacinDostavljanja" />
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                            <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" padding="3px" border-left="1px solid black">
                                <fo:block>
                                    Polje broj VII 
                                    <xsl:value-of select="zahtev/formaPodnosioca/prijava/vrstaPrijave" />
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                            <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" padding="3px" border-left="1px solid black">
                                <fo:block> Broj prvobitne prijave / osnovne prijave, odnosno osnovnog patenta: <fo:basic-link external-destination="http://localhost:8083/download/pdf/{zahtev/formaPodnosioca/prijava/brojPrvobitnePrijave}"><xsl:value-of select="zahtev/formaPodnosioca/prijava/brojPrvobitnePrijave" /></fo:basic-link></fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                            <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" padding="3px" border-left="1px solid black">
                                <fo:block> Datum podnosenja prvobitne prijave / osnovne prijave: 
                                <xsl:value-of select="zahtev/formaPodnosioca/prijava/datumPodnosenjaPrvobitnePrijave" /></fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                        </fo:table-body>
                        
                    </fo:table>
                    <fo:table border-collapse="collapse" table-layout="fixed" width="100%" 
                    margin="10px auto 10px auto" border="3px solid black">
                        <fo:table-body>
                            <fo:table-row>
                            <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" padding="3px" number-columns-spanned="7" border-left="1px solid black">
                                <fo:block>Polje broj VIII ZAHTEV ZA PRIZNANJE PRAVA PRVENSTVA IZ RANIJIH PRIJAVA</fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                            <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" padding="3px" number-columns-spanned="2" border-left="1px solid black">
                                <fo:block>Broj ranije prijave</fo:block>
                            </fo:table-cell>
                            <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" padding="3px" number-columns-spanned="3">
                                <fo:block>Datum podnosenja ranije prijave</fo:block>
                            </fo:table-cell>
                            <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" padding="3px" number-columns-spanned="2">
                                <fo:block>Dvoslovna oznaka drzave, regionalne ili medjunarodne organizacije</fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                        <xsl:for-each select="zahtev/formaPodnosioca/zahtevZaPriznanjePrava/prijave/ranijaPrijava">
                            <fo:table-row>
                                <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" padding="3px" number-columns-spanned="2" border-left="1px solid black">
                                    <fo:block> <fo:basic-link external-destination="http://localhost:8083/download/pdf/{brojPrijave}"> <xsl:value-of select="brojPrijave" /></fo:basic-link></fo:block>
                                    
                                </fo:table-cell>
                                <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" padding="3px" number-columns-spanned="3">
                                    <fo:block><xsl:value-of select="datum" /></fo:block>
                                    
                                </fo:table-cell>
                                <fo:table-cell border-bottom="1px solid black" border-right="1px solid black" padding="3px" number-columns-spanned="2">
                                    <fo:block><xsl:value-of select="drzavaIliOrganizacija" /></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </xsl:for-each>
                        </fo:table-body>
                        
                    </fo:table>
                    <fo:block>
                        <xsl:if test="zahtev/formaPodnosioca/zahtevZaPriznanjePrava/podaciOOstalimPravimaNaListu2 = 'true'">
                            <fo:block padding="7px">Dodatni list:</fo:block>
                            <fo:block padding="7px">
                                <xsl:value-of select="zahtev/formaPodnosioca/dodatniList2" />
                            </fo:block>
                        </xsl:if>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>


    </xsl:template>
</xsl:stylesheet>