<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:proj="http://localhost:3030/tipovi"
    xmlns:z1="http://localhost:3030/z1"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:fo="http://www.w3.org/1999/XSL/Format"
    xmlns:qr="http://java4less.com/j4lbarcode/fop"

    exclude-result-prefixes="xs" version="2.0">
    <xs:import namespace="http://java4less.com/j4lbarcode/fop" schemaLocation="../xml/xsd/qrcode.xsd"/>
    <xsl:template match="/zahtev">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="Z1">
                    <fo:region-body margin="0.75in" />
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:declarations>
                <x:xmpmeta xmlns:x="adobe:ns:meta/">
                    <rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#">
                        <rdf:Description rdf:about="" xmlns:dc="http://purl.org/dc/elements/1.1/">
                            <dc:title>Zahtev za priznanje ziga</dc:title>
                            <dc:creator>Elita sa SIIT-a</dc:creator>
                            <dc:description>Zahtev za priznanje ziga</dc:description>
                        </rdf:Description>
                    </rdf:RDF>
                </x:xmpmeta>
            </fo:declarations>

            <fo:page-sequence master-reference="Z1">
                <fo:flow flow-name="xsl-region-body">

                    <fo:block text-align="center" font-weight="bold">
                        Zahtev za priznanje Ziga
                    </fo:block>
                    <fo:block text-align="center" font-weight="bold">
                        Zavod za intelektualnu svojinu, Kneginje Ljubice broj 5, 11000 Beograd
                    </fo:block>

                    <fo:table table-layout="fixed"  border-collapse="collapse" width="100%" margin="0 0 0 0">
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" number-columns-spanned="3"
                                    border-top="1px solid black" border-left="1px solid black">
                                    <fo:block font-weight="bold">1. Podnosilac prijave</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" number-columns-spanned="3" border-left="1px solid black">
                                    <fo:block linefeed-treatment="preserve">
                                        <xsl:value-of select="concat(podnosilac/proj:naziv_preduzeca, ', ')"/> <xsl:value-of select="concat(podnosilac/proj:pib, ', ')" /> <xsl:value-of select="concat(podnosilac/proj:ime, ' ')"/> <xsl:value-of select="concat(podnosilac/proj:prezime, ', ')"/>
                                        
                                        <xsl:value-of select="podnosilac/proj:adresa/*" separator=" "/>
                                    </fo:block>

                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" border-left="1px solid black">
                                    <fo:block>telefon: <xsl:value-of select="podnosilac/proj:kontakt/proj:telefon" /></fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black">
                                    <fo:block>e-mail: <xsl:value-of select="podnosilac/proj:kontakt/proj:email" /></fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black">
                                    <fo:block>faks: <xsl:value-of select="podnosilac/proj:kontakt/proj:faks" /></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" number-columns-spanned="3" border-left="1px solid black">
                                    <fo:block font-weight="bold">2. Punomocnik</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" number-columns-spanned="3" border-left="1px solid black">
                                    <fo:block linefeed-treatment="preserve">
                                        <xsl:value-of select="punomocnik/proj:naziv_preduzeca"/> <xsl:value-of select="punomocnik/proj:pib" /> <xsl:value-of select="punomocnik/proj:ime"/> <xsl:value-of select="punomocnik/proj:prezime"/>
                                        
                                        <xsl:value-of select="punomocnik/proj:adresa/*" separator=" "/></fo:block>

                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" border-left="1px solid black">
                                    <fo:block>telefon: <xsl:value-of select="punomocnik/proj:kontakt/proj:telefon" /></fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black">
                                    <fo:block>e-mail: <xsl:value-of select="punomocnik/proj:kontakt/proj:email" /></fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black">
                                    <fo:block>faks: <xsl:value-of select="punomocnik/proj:kontakt/proj:faks" /></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" number-columns-spanned="3" border-left="1px solid black">
                                    <fo:block font-weight="bold">3. Podaci o zajednickom predstavniku ako postoji vise podnosioca prijave</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" number-columns-spanned="3" border-left="1px solid black">
                                    <fo:block linefeed-treatment="preserve">
                                        <xsl:value-of select="zajednickiPredstavnik/proj:naziv_preduzeca"/> <xsl:value-of select="zajednickiPredstavnik/proj:pib" /> <xsl:value-of select="zajednickiPredstavnik/proj:ime"/> <xsl:value-of select="zajednickiPredstavnik/proj:prezime"/>

                                        <xsl:value-of select="zajednickiPredstavnik/proj:adresa/*" separator=",&#xa;"/></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" border-left="1px solid black">
                                    <fo:block>telefon: <xsl:value-of select="zajednickiPredstavnik/proj:kontakt/proj:telefon" /></fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black">
                                    <fo:block>e-mail: <xsl:value-of select="zajednickiPredstavnik/proj:kontakt/proj:email" /></fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black">
                                    <fo:block>faks: <xsl:value-of select="zajednickiPredstavnik/proj:kontakt/proj:faks" /></fo:block>
                                </fo:table-cell>
                            </fo:table-row>

                        </fo:table-body>

                    </fo:table>

                    <fo:table table-layout="fixed"  border-collapse="collapse" width="100%" margin="0 0 0 0">
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" number-columns-spanned="2" border-left="1px solid black">
                                    <fo:block font-weight="bold">4. Prijava se odnosi za:</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" number-columns-spanned="2" number-rows-spanned="1"
                                    border-top="1px solid black">
                                    <fo:block>c) izgled znaka:</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" border-left="1px solid black">
                                    <fo:block>a)</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black">
                                    <fo:block><xsl:value-of select="podaci_o_zigu/vrsta_ziga"/></fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" number-columns-spanned="2" number-rows-spanned="2">
                                    <fo:block>
                                        <fo:instream-foreign-object >
                                            <qr:j4lbarcode mode="inline"> <!--xmlns="http://java4less.com/j4lbarcode/fop"  -->
                                                <qrcode>
                                                    <code>http://localhost:8085/z1/download/img/<xsl:value-of select="podaci_o_zigu/izgled_ziga"/></code>
                                                    <moduleSize>2</moduleSize>
                                                    <processTilde>false</processTilde>
                                                    <margin>30</margin>
                                                    <ecLevel>H</ecLevel>
                                                    <encoding>AUTO</encoding>
                                                    <configuration>1</configuration>
                                                </qrcode>
                                            </qr:j4lbarcode>

                                        </fo:instream-foreign-object>



                                        <!-- <xsl:element name="img">
                                            <xsl:attribute name="src"> data\<xsl:value-of
                                                    select="//sz:Zig//sz:Izgled_putanja_do_slike" />
                                            </xsl:attribute>
                                        </xsl:element> -->
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" border-left="1px solid black"><fo:block> b)</fo:block></fo:table-cell>
                                    <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black">
                                        <fo:block><xsl:value-of select="podaci_o_zigu/format_ziga"/></fo:block>
                                    </fo:table-cell>
                                
                            </fo:table-row>

                            <xsl:if test="podaci_o_zigu/boja">
                                <fo:table-row>
                                    <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" number-columns-spanned="2" border-left="1px solid black">
                                        <fo:block linefeed-treatment="preserve">
                                            <xsl:text>5. Naznacenje boje, odnosno boja iz kojih se znak sastoji:
                                            </xsl:text>
                                            <xsl:value-of select="podaci_o_zigu/boja"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:if>

                            <xsl:if test="podaci_o_zigu/transliteracija">
                                <fo:table-row>
                                    <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" number-columns-spanned="2" border-left="1px solid black">
                                        <fo:block linefeed-treatment="preserve">
                                            <xsl:text>6. Transliteracija znaka:
                                            </xsl:text>
                                            <xsl:value-of select="podaci_o_zigu/transliteracija"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:if>

                            <xsl:if test="podaci_o_zigu/prevod">
                                <fo:table-row>
                                    <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" number-columns-spanned="2" border-left="1px solid black">
                                        <fo:block linefeed-treatment="preserve">
                                            <xsl:text>7. Prevod znaka
                                            </xsl:text>
                                            <xsl:value-of select="podaci_o_zigu/prevod"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:if>

                            <xsl:if test="podaci_o_zigu/opis">
                                <fo:table-row>
                                    <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" number-columns-spanned="2" border-left="1px solid black">
                                        <fo:block linefeed-treatment="preserve">
                                            <xsl:text>8. Opis znaka
                                            </xsl:text>
                                            <xsl:value-of select="podaci_o_zigu/opis"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:if>
                        </fo:table-body>
                    </fo:table>

                    <fo:table table-layout="fixed"  border-collapse="collapse" width="100%" margin="0 0 0 0">
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-top="1px solid black" border-right="1px solid black" number-columns-spanned="3" border-left="1px solid black">
                                    <fo:block font-weight="bold">9. Brojevi klasa robe i usluga prema Nicanskoj klasifikaciji</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" border-left="1px solid black" number-columns-spanned="3">
                                    <fo:block>    
                                        <xsl:for-each select="podaci_o_zigu/klasa">  
                                            <xsl:value-of select="self::klasa"/>,
                                        </xsl:for-each>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>

                            <fo:table-row>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" number-columns-spanned="3" border-left="1px solid black">
                                    <fo:block linefeed-treatment="preserve" font-weight="bold">
                                        <xsl:text>10. Zatrazeno pravo prvenstva i osnov: 
                                        </xsl:text>
                                        <xsl:value-of select="podaci_o_zigu/pravo_prvenstva"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>

                            <fo:table-row>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" border-left="1px solid black">
                                    <fo:block font-weight="bold">11. Placene takse</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black">
                                    <fo:block>Dinara</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" number-rows-spanned="5"
                                    vertical-align="top" text-align="center">
                                    <fo:block>Potpis podnosioca zahteva</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" border-left="1px solid black">
                                    <fo:block>a) Osnovna taksa</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black">
                                    <fo:block>
                                        <xsl:value-of select="takse/osnovna_taksa"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" border-left="1px solid black">
                                    <!-- <fo:block>b) Za <xsl:value-of select="count(//sz:Klasa)" /> klase</fo:block> -->
                                    <fo:block> b) za <xsl:value-of select="count(zahtev/podaci_o_zigu/klasa)" /> klasa </fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black">
                                    <fo:block>
                                        <xsl:value-of select="takse/za_klase"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" border-left="1px solid black">
                                    <fo:block>c) Za graficko resenje</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black">
                                    <fo:block>
                                        <xsl:value-of select="takse/za_graficko_resenje"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" border-left="1px solid black">
                                    <fo:block>UKUPNO</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black">
                                    <fo:block>
                                        <xsl:value-of select="sum(takse/osnovna_taksa | takse/za_klase | takse/za_graficko_resenje)" />
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>

                    </fo:table>

                    <fo:block break-before="page" text-align="center" font-weight="bold">
                        POPUNJAVA ZAVOD
                    </fo:block>

                    <fo:table table-layout="fixed"  border-collapse="collapse" width="100%" margin="0 0 0 0">
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" number-columns-spanned="2"
                                    border-top="1px solid black" border-left="1px solid black">
                                    <fo:block>Prilozi uz zahtev:</fo:block>
                                    <fo:block>
                                        <xsl:for-each select="zavod/prilozi/prilog">
                                            <fo:block font-size="15px"><xsl:value-of select="self::prilog"/></fo:block>
                                        </xsl:for-each>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black"
                                    text-align="center" border-top="1px solid black">
                                    <fo:block linefeed-treatment="preserve">Broj prijave ziga:

                                    <xsl:value-of select="zavod/brojPrijave"/>
                                  
                                  Datum podnosenja: <xsl:value-of select="zavod/datumPodnosenja"/></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            
                        </fo:table-body>

                    </fo:table>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>