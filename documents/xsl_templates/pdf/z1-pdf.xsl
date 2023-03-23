<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:proj="http://localhost:3030/tipovi"
    xmlns:z1="http://localhost:3030/z1"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:fo="http://www.w3.org/1999/XSL/Format"
    exclude-result-prefixes="xs"
    version="2.0">
    
    <xsl:template match="/z1:zahtev">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="Z1">
                    <fo:region-body margin="0.75in" />
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="Z1">
                <fo:flow flow-name="xsl-region-body">
                    
                    <fo:block text-align="center" font-weight="bold">
                        ZAHTEV ZA PRIZNJANJE ŽIGA
                    </fo:block>
                    <fo:block text-align="center" font-weight="bold">
                        Zavodu za intelektualnu svojinu, Kneginje Ljubice 5, 110000 Beograd
                    </fo:block>
                    
                    <fo:table table-layout="fixed" width="100%" font-family="sans-serif" border="3px solid black"
                        margin="10px auto 10px auto">
                        <fo:table-column column-width="100%" />
                        
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell padding="7px" font-size="14px" margin-top="10px">
                                    <fo:block font-weight="bold">Podnosilac prijave:</fo:block><fo:block> ime i prezime/poslovno ime, ulica i broj, postanski broj, mesto, drzava prebivalista/sedista</fo:block></fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="7px">
                                    <fo:block margin-left="5px"
                                        margin-top="5px"
                                        font-size="14px"
                                        font-weight="bold">
                                        <xsl:value-of select="z1:podnosilac/proj:naziv_preduzeca"></xsl:value-of>&#160;
                                        <xsl:value-of select="z1:podnosilac/proj:pib" ></xsl:value-of>&#160;
                                        <xsl:value-of select="z1:podnosilac/proj:ime"></xsl:value-of>&#160;
                                        <xsl:value-of select="z1:podnosilac/proj:prezime"></xsl:value-of>&#160;
                                        
                                    </fo:block>
                                    <fo:block margin-left="5px"
                                        margin-top="5px"
                                        font-size="14px"
                                        font-weight="bold">
                                        <xsl:value-of select="z1:podnosilac/proj:adresa/*" separator=",&#xa;"></xsl:value-of>
                                    </fo:block>
                                    <fo:block font-size="14px" margin-top="10px">Telefon: <xsl:value-of select="z1:podnosilac/lice/proj:kontakt/proj:telefon"></xsl:value-of></fo:block>
                                    <fo:block font-size="14px" margin-top="10px">E-Mail: <xsl:value-of select="z1:podnosilac/proj:kontakt/proj:email"></xsl:value-of></fo:block>
                                    <fo:block font-size="14px" margin-top="10px">Faks: <xsl:value-of select="z1:podnosilac/proj:kontakt/proj:faks"></xsl:value-of></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="7px">
                                    <fo:block font-size="14px" margin-top="10px"><fo:block font-weight="bold">2. Punomocnik: </fo:block> ime i prezime/poslovno ime, ulica i broj, postanski broj, mesto, drzava prebivalista/sedista: </fo:block>
                                </fo:table-cell>
                                
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="7px">
                                    <fo:block margin-left="5px"
                                        margin-top="5px"
                                        font-size="14px"
                                        font-weight="bold">
                                        <xsl:value-of select="z1:punomocnik/proj:naziv_preduzeca"></xsl:value-of>&#160;
                                        <xsl:value-of select="z1:punomocnik/proj:pib" ></xsl:value-of>&#160;
                                        <xsl:value-of select="z1:punomocnik/proj:ime"></xsl:value-of>&#160;
                                        <xsl:value-of select="z1:punomocnik/proj:prezime"></xsl:value-of>&#160;
                                        
                                    </fo:block>
                                    <fo:block margin-left="5px"
                                        margin-top="5px"
                                        font-size="14px"
                                        font-weight="bold">
                                        <xsl:value-of select="z1:punomocnik/proj:adresa/*" separator=",&#xa;"></xsl:value-of>
                                    </fo:block>
                                    <fo:block font-size="14px" margin-top="10px">Telefon: <xsl:value-of select="z1:punomocnik/lice/proj:kontakt/proj:telefon"></xsl:value-of></fo:block>
                                    <fo:block font-size="14px" margin-top="10px">E-Mail: <xsl:value-of select="z1:punomocnik/proj:kontakt/proj:email"></xsl:value-of></fo:block>
                                    <fo:block font-size="14px" margin-top="10px">Faks: <xsl:value-of select="z1:punomocnik/proj:kontakt/proj:faks"></xsl:value-of></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="7px">
                                    <fo:block font-size="14px" margin-top="10px" font-weight="bold"><fo:block font-weight="bold">3. Podaci o zajednickom predstavniku ako postoji vise podnosilaca prijave:</fo:block> </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="7px">
                                    <fo:block margin-left="5px"
                                        margin-top="5px"
                                        font-size="14px"
                                        font-weight="bold">
                                        <xsl:value-of select="z1:zajednicki_predstavnik/proj:naziv_preduzeca"></xsl:value-of>&#160;
                                        <xsl:value-of select="z1:zajednicki_predstavnik/proj:pib" ></xsl:value-of>&#160;
                                        <xsl:value-of select="z1:zajednicki_predstavnik/proj:ime"></xsl:value-of>&#160;
                                        <xsl:value-of select="z1:zajednicki_predstavnik/proj:prezime"></xsl:value-of>&#160;
                                        
                                    </fo:block>
                                    <fo:block margin-left="5px"
                                        margin-top="5px"
                                        font-size="14px"
                                        font-weight="bold">
                                        <xsl:value-of select="z1:zajednicki_predstavnik/proj:adresa/*" separator=",&#xa;"></xsl:value-of>
                                    </fo:block>
                                    <fo:block font-size="14px" margin-top="10px">Telefon: <xsl:value-of select="z1:zajednicki_predstavnik/lice/proj:kontakt/proj:telefon"></xsl:value-of></fo:block>
                                    <fo:block font-size="14px" margin-top="10px">E-Mail: <xsl:value-of select="z1:zajednicki_predstavnik/proj:kontakt/proj:email"></xsl:value-of></fo:block>
                                    <fo:block font-size="14px" margin-top="10px">Faks: <xsl:value-of select="z1:zajednicki_predstavnik/proj:kontakt/proj:faks"></xsl:value-of></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="7px">
                                    <fo:block font-size="14px" margin-top="10px"> Vrsta Znaka:   <xsl:value-of select="z1:podaci_o_zigu/z1:vrsta_ziga"></xsl:value-of></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="7px">
                                    <fo:block font-size="14px" margin-top="10px">Format znaka: <xsl:value-of select="z1:podaci_o_zigu/z1:format_ziga"></xsl:value-of></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="7px">
                                    <fo:block font-size="14px" margin-top="10px">Izgled znaka: <xsl:value-of select="z1:podaci_o_zigu/z1:izgled_ziga"></xsl:value-of></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="7px">
                                    <fo:block font-size="14px" margin-top="10px">Naznacenje boje, odnosno boja iz kojih se znak sastoji: <xsl:value-of select="z1:podaci_o_zigu/z1:boja"></xsl:value-of></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="7px">
                                    <fo:block font-size="14px" margin-top="10px"> Transliteracija znaka: <xsl:value-of select="z1:podaci_o_zigu/z1:transliteracija"></xsl:value-of></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="7px">
                                    <fo:block font-size="14px" margin-top="10px">Prevod znaka: <xsl:value-of select="z1:podaci_o_zigu/z1:prevod"></xsl:value-of></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="7px">
                                    <fo:block font-size="14px" margin-top="10px"> Opis znaka: <xsl:value-of select="z1:podaci_o_zigu/z1:opis"></xsl:value-of></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="7px">
                                    <fo:block font-size="14px" margin-top="10px"> Klase robe i usluga prema Nicanskoj klasifikaciji: </fo:block>
                                    <xsl:for-each select="z1:podaci_o_zigu/z1:klasa">
                                        <fo:block font-size="15px"><xsl:value-of select="self::z1:klasa"></xsl:value-of></fo:block>
                                    </xsl:for-each>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="7px">
                                    <fo:block font-size="14px" margin-top="10px"> Zatrazeno pravo prvenstva i osnov: <xsl:value-of select="z1:podaci_o_zigu/z1:pravo_prvenstva"></xsl:value-of></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                    
                    <fo:table table-layout="fixed" width="400px" font-family="sans-serif" border="2px solid black"
                         margin-top="20px" margin-bottom="20px">
                        <fo:table-column column-width="200px" />
                        <fo:table-column column-width="200px" />
                        
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell border="1px solid black"><fo:block margin-left="5px"
                                    margin-top="5px"
                                    font-size="14px"
                                    font-weight="bold">11. Placene takse:</fo:block></fo:table-cell>
                                <fo:table-cell border="1px solid black"><fo:block margin-left="5px"
                                    margin-top="5px"
                                    font-size="14px"
                                    font-weight="bold">Dinara:</fo:block></fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell border="1px solid black"><fo:block margin-left="5px"
                                    margin-top="5px"
                                    font-size="14px"
                                    font-weight="bold">a) osnovna taksa</fo:block></fo:table-cell>
                                <fo:table-cell border="1px solid black"><fo:block margin-left="5px"
                                    margin-top="5px"
                                    font-size="14px"
                                    font-weight="bold"><xsl:value-of select="z1:takse/z1:osnovna_taksa"></xsl:value-of> </fo:block></fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell border="1px solid black"><fo:block margin-left="5px"
                                    margin-top="5px"
                                    font-size="14px"
                                    font-weight="bold">b) za ______ klasa</fo:block><fo:block margin-left="5px"
                                        margin-top="5px"
                                        font-size="14px"
                                        font-weight="bold">c) za graficko resenje</fo:block></fo:table-cell>
                                <fo:table-cell border="1px solid black"><fo:block margin-left="5px"
                                    margin-top="5px"
                                    font-size="14px"
                                    font-weight="bold"><xsl:value-of select="z1:takse/z1:za_klase"></xsl:value-of> </fo:block>
                                    <fo:block margin-left="5px"
                                        margin-top="5px"
                                        font-size="14px"
                                        font-weight="bold"><xsl:value-of select="z1:takse/z1:za_graficko_resenje"></xsl:value-of> </fo:block></fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                    
                    <fo:table table-layout="fixed" width="400px" font-family="sans-serif" border="2px solid black"
                        margin-top="20px" margin-bottom="20px">
                        <fo:table-column column-width="400px" />
                        
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell border="1px solid black">
                                    <fo:block text-align="center" margin-left="5px"
                                        margin-top="5px"
                                        font-size="14px"
                                        font-weight="bold">POPUNJAVA ZAVOD</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell border="1px solid black">
                                    <fo:block text-align="center" font-size="14px" margin-top="10px" font-weight="bold">Prilozi uz zahtev:</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <xsl:for-each select="z1:zavod/z1:prilozi/z1:prilog">
                                <fo:table-row>
                                    <fo:table-cell border="1px solid black" text-align="center" >
                                        <fo:block font-size="15px"><xsl:value-of select="self::z1:prilog"></xsl:value-of></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:for-each>
                            
                            <fo:table-row>
                                <fo:table-cell text-align="center">
                                    <fo:block font-size="14px" margin-top="10px">Broj prijave žiga: <xsl:value-of select="z1:zavod/z1:broj_prijave"></xsl:value-of></fo:block>
                                </fo:table-cell>
                            </fo:table-row>   
                            <fo:table-row>
                                <fo:table-cell text-align="center">
                                    <fo:block margin-left="5px"
                                        margin-top="5px"
                                        font-size="14px"
                                        font-weight="bold">Datum podnosenja:<xsl:value-of select="z1:zavod/z1:datum_podnosenja"></xsl:value-of></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                    
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
    
</xsl:stylesheet>