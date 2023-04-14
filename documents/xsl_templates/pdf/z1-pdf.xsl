<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:proj="http://localhost:3030/tipovi"
    xmlns:z1="http://localhost:3030/z1"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:fo="http://www.w3.org/1999/XSL/Format"
    exclude-result-prefixes="xs" version="2.0">

    <xsl:template match="/z1:zahtev">
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
                                        <xsl:value-of select="concat(z1:podnosilac/proj:naziv_preduzeca, ', ')"></xsl:value-of> <xsl:value-of select="concat(z1:podnosilac/proj:pib, ', ')" ></xsl:value-of> <xsl:value-of select="concat(z1:podnosilac/proj:ime, ' ')"></xsl:value-of> <xsl:value-of select="concat(z1:podnosilac/proj:prezime, ', ')"></xsl:value-of>
                                        
                                        <xsl:value-of select="z1:podnosilac/proj:adresa/*" separator=" "></xsl:value-of>
                                    </fo:block>

                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" border-left="1px solid black">
                                    <fo:block>telefon: <xsl:value-of select="z1:podnosilac/proj:kontakt/proj:telefon" /></fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black">
                                    <fo:block>e-mail: <xsl:value-of select="z1:podnosilac/proj:kontakt/proj:email" /></fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black">
                                    <fo:block>faks: <xsl:value-of select="z1:podnosilac/proj:kontakt/proj:faks" /></fo:block>
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
                                        <xsl:value-of select="z1:punomocnik/proj:naziv_preduzeca"></xsl:value-of> <xsl:value-of select="z1:punomocnik/proj:pib" ></xsl:value-of> <xsl:value-of select="z1:punomocnik/proj:ime"></xsl:value-of> <xsl:value-of select="z1:punomocnik/proj:prezime"></xsl:value-of>
                                        
                                        <xsl:value-of select="z1:punomocnik/proj:adresa/*" separator=" "></xsl:value-of></fo:block>

                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" border-left="1px solid black">
                                    <fo:block>telefon: <xsl:value-of select="z1:punomocnik/proj:kontakt/proj:telefon" /></fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black">
                                    <fo:block>e-mail: <xsl:value-of select="z1:punomocnik/proj:kontakt/proj:email" /></fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black">
                                    <fo:block>faks: <xsl:value-of select="z1:punomocnik/proj:kontakt/proj:faks" /></fo:block>
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
                                        <xsl:value-of select="z1:zajednicki_predstavnik/proj:naziv_preduzeca"></xsl:value-of> <xsl:value-of select="z1:zajednicki_predstavnik/proj:pib" ></xsl:value-of> <xsl:value-of select="z1:zajednicki_predstavnik/proj:ime"></xsl:value-of> <xsl:value-of select="z1:zajednicki_predstavnik/proj:prezime"></xsl:value-of>

                                        <xsl:value-of select="z1:zajednicki_predstavnik/proj:adresa/*" separator=",&#xa;"></xsl:value-of></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" border-left="1px solid black">
                                    <fo:block>telefon: <xsl:value-of select="z1:zajednicki_predstavnik/proj:kontakt/proj:telefon" /></fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black">
                                    <fo:block>e-mail: <xsl:value-of select="z1:zajednicki_predstavnik/proj:kontakt/proj:email" /></fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black">
                                    <fo:block>faks: <xsl:value-of select="z1:zajednicki_predstavnik/proj:kontakt/proj:faks" /></fo:block>
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
                                    <fo:block><xsl:value-of select="z1:podaci_o_zigu/z1:vrsta_ziga"></xsl:value-of></fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" number-columns-spanned="2" number-rows-spanned="2">
                                    <fo:block>
                                        <xsl:value-of select="z1:podaci_o_zigu/z1:izgled_ziga"></xsl:value-of>
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
                                        <fo:block><xsl:value-of select="z1:podaci_o_zigu/z1:format_ziga"></xsl:value-of></fo:block>
                                    </fo:table-cell>
                                
                            </fo:table-row>

                            <xsl:if test="z1:podaci_o_zigu/z1:boja">
                                <fo:table-row>
                                    <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" number-columns-spanned="2" border-left="1px solid black">
                                        <fo:block linefeed-treatment="preserve">
                                            <xsl:text>5. Naznacenje boje, odnosno boja iz kojih se znak sastoji:
                                            </xsl:text>
                                            <xsl:value-of select="z1:podaci_o_zigu/z1:boja"></xsl:value-of>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:if>

                            <xsl:if test="z1:podaci_o_zigu/z1:transliteracija">
                                <fo:table-row>
                                    <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" number-columns-spanned="2" border-left="1px solid black">
                                        <fo:block linefeed-treatment="preserve">
                                            <xsl:text>6. fo:table-rowansliteracija znaka:
                                            </xsl:text>
                                            <xsl:value-of select="z1:podaci_o_zigu/z1:transliteracija"></xsl:value-of>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:if>

                            <xsl:if test="z1:podaci_o_zigu/z1:prevod">
                                <fo:table-row>
                                    <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" number-columns-spanned="2" border-left="1px solid black">
                                        <fo:block linefeed-treatment="preserve">
                                            <xsl:text>7. Prevod znaka
                                            </xsl:text>
                                            <xsl:value-of select="z1:podaci_o_zigu/z1:prevod"></xsl:value-of>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:if>

                            <xsl:if test="z1:podaci_o_zigu/z1:opis">
                                <fo:table-row>
                                    <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" number-columns-spanned="2" border-left="1px solid black">
                                        <fo:block linefeed-treatment="preserve">
                                            <xsl:text>8. Opis znaka
                                            </xsl:text>
                                            <xsl:value-of select="z1:podaci_o_zigu/z1:opis"></xsl:value-of>
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
                                        <xsl:for-each select="z1:podaci_o_zigu/z1:klasa">  
                                            <xsl:value-of select="self::z1:klasa"></xsl:value-of>, 
                                        </xsl:for-each>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>

                            <fo:table-row>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" number-columns-spanned="3" border-left="1px solid black">
                                    <fo:block linefeed-treatment="preserve" font-weight="bold">
                                        <xsl:text>10. Zatrazeno pravo prvenstva i osnov: 
                                        </xsl:text>
                                        <xsl:value-of select="z1:podaci_o_zigu/z1:pravo_prvenstva"></xsl:value-of>
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
                                        <xsl:value-of select="z1:takse/z1:osnovna_taksa"></xsl:value-of>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" border-left="1px solid black">
                                    <!-- <fo:block>b) Za <xsl:value-of select="count(//sz:Klasa)" /> klase</fo:block> -->
                                    <fo:block> b) za <xsl:value-of select="count(z1:zahtev/z1:podaci_o_zigu/z1:klasa)" /> klasa </fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black">
                                    <fo:block>
                                        <xsl:value-of select="z1:takse/z1:za_klase"></xsl:value-of> 
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" border-left="1px solid black">
                                    <fo:block>c) Za graficko resenje</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black">
                                    <fo:block>
                                        <xsl:value-of select="z1:takse/z1:za_graficko_resenje"></xsl:value-of>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black" border-left="1px solid black">
                                    <fo:block>UKUPNO</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black">
                                    <fo:block>
                                        <xsl:value-of select="sum(z1:takse/z1:osnovna_taksa | z1:takse/z1:za_klase | z1:takse/z1:za_graficko_resenje)" />
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
                                        <xsl:for-each select="z1:zavod/z1:prilozi/z1:prilog">
                                            <fo:block font-size="15px"><xsl:value-of select="self::z1:prilog"></xsl:value-of></fo:block>
                                        </xsl:for-each>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px" border-bottom="1px solid black" border-right="1px solid black"
                                    text-align="center" border-top="1px solid black">
                                    <fo:block linefeed-treatment="preserve">Broj prijave ziga:

                                    <xsl:value-of select="z1:zavod/z1:broj_prijave"></xsl:value-of>
                                  
                                  Datum podnosenja: <xsl:value-of select="z1:zavod/z1:datum_podnosenja"></xsl:value-of></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            
                        </fo:table-body>

                    </fo:table>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>