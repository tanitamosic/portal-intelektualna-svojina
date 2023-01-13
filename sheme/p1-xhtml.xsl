<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:proj="http://localhost:3030/tipovi"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    
    exclude-result-prefixes="xs"
    version="2.0">
    <xsl:output method="xml" />

    <xsl:template match="/">
        <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
            <head>
                <title>Zahtev P1</title>
                <style>
                    .center.text {
                    text-align: center;
                    }
                    table {
                    padding: 7px;
                    margin-left: auto;
                    margin-right: auto;
                    width: 60%;
                    border: 3px solid black;
                    }
                    .field {
                    box-shadow: 0px 1px 0px 0px rgb(0, 0, 0);
                    }
                    td {
                    padding: 7px;
                    }
                </style>
            </head>
            
            <body>
                <div class="center text">
                    <h1>Zahtev za priznanje patenta</h1>
                </div>
                <div>
                    <table>
                        <tr>
                            <td colspan="4"><b>Popunjava zavod</b></td>
                        </tr>
                        <tr>
                            <td colspan="1">Broj prijave</td>
                            <td colspan="3"><xsl:value-of select="zahtev/zavod/formaZaZavod/brojPrijave"/></td>
                        </tr>
                        <tr>
                            <td colspan="1">Datum prijema</td>
                            <td colspan="3"><xsl:value-of select="zahtev/zavod/formaZaZavod/datumPrijema"/></td>
                        </tr>
                        <tr>
                            <td colspan="1">Datum podnosenja</td>
                            <td colspan="3"><xsl:value-of select="zahtev/zavod/formaZaZavod/datumPodnosenja"/></td>
                        </tr>
                    </table>
                    <div class="center text">
                        <p><xsl:value-of select="zahtev/zavod/podaciOZavodu/institucija"/></p>
                        <p><xsl:value-of select="zahtev/zavod/podaciOZavodu/proj:adresa/*" separator=",&#xa;"/></p>
                    </div>
                    
                </div>
                <div>
                    <table>
                        <tr class="field">
                            <td><b>Polje broj I NAZIV PRONALASKA</b></td>
                        </tr>
                        <tr>
                            <td>Na srpskom: <xsl:value-of select="zahtev/formaPodnosioca/nazivPronalaska/srpski"/></td>
                        </tr>
                        <tr>
                            <td>Na engleskom: <xsl:value-of select="zahtev/formaPodnosioca/nazivPronalaska/engleski"/></td>
                        </tr>
                        <tr class="field">
                            <td><b>Polje broj II PODNOSILAC PRIJAVE</b></td>
                            <xsl:if test="zahtev/formaPodnosioca/podnosilacPrijave/jePronalazac = 'true'">
                                <td>Podnosilac prijave je pronalazac</td>
                            </xsl:if>
                            <xsl:if test="zahtev/formaPodnosioca/podnosilacPrijave/jePronalazac != 'true'">
                                <td>Podnosilac prijave nije pronalazac</td>
                            </xsl:if>
                        </tr>
                        <tr>
                            <td>Adresa: <xsl:value-of select="zahtev/formaPodnosioca/podnosilacPrijave/proj:adresa/*" separator="&#xa;"/></td>
                        </tr>
                        <tr>
                            <td>Kontakt: <xsl:value-of select="zahtev/formaPodnosioca/podnosilacPrijave/proj:kontakt/*" separator="&#xa;"/></td>
                        </tr>
                        <tr class="field">
                            <td><b>Polje broj III PRONALAZAC</b></td>
                        </tr>
                        <tr>
                            <td>
                                <span><xsl:value-of select="zahtev/formaPodnosioca/pronalazac/proj:ime"/>&#160;</span> 
                                <span><xsl:value-of select="zahtev/formaPodnosioca/pronalazac/proj:prezime"/></span>
                            </td>
                        </tr>
                        <tr>
                            <td>Adresa: <xsl:value-of select="zahtev/formaPodnosioca/pronalazac/proj:adresa/*" separator="&#xa;"/></td>
                        </tr>
                        <tr>
                            <td>Kontakt: <xsl:value-of select="zahtev/formaPodnosioca/pronalazac/proj:kontakt/proj:*" separator="&#xa;"/></td>
                        </tr>
                        <tr class="field">
                            <td><b>Polje broj IV</b></td>
                            <td colspan="2"><xsl:value-of select="zahtev/formaPodnosioca/posrednik/vrstaPosrednika"/></td>
                        </tr>
                        <tr>
                            <td>Adresa: <xsl:value-of select="zahtev/formaPodnosioca/posrednik/proj:adresa/*"/></td>
                        </tr>
                        <tr>
                            <td>Kontakt: <xsl:value-of select="zahtev/formaPodnosioca/posrednik/proj:kontakt/proj:*" separator="&#xa;"/></td>
                        </tr>
                        <tr class="field">
                            <td><b>Polje broj V</b></td>
                        </tr>
                        <tr>
                            <td>Adresa za dostavljanje: <xsl:value-of select="zahtev/formaPodnosioca/adresaZaDostavljanje/proj:adresa/proj:*"/></td>
                        </tr>
                        <tr class="field">
                            <td><b>Polje broj VI NACIN DOSTAVLJANJA</b></td>
                            <td colspan="3"><xsl:value-of select="zahtev/formaPodnosioca/nacinDostavljanja"/></td>
                        </tr>
                        <tr class="field">
                            <td><b>Polje broj VII</b></td>
                            <td colspan="2"><xsl:value-of select="zahtev/formaPodnosioca/prijava/vrstaPrijave"/></td>
                        </tr>
                        <tr>
                            <td>
                                Broj prvobitne prijave / osnovne prijave, odnosno osnovnog patenta: <xsl:value-of select="zahtev/formaPodnosioca/prijava/brojPrvobitnePrijave"/>
                            </td>
                            <td>
                                Datum podnosenja prvobitne prijave / osnovne prijave: <xsl:value-of select="zahtev/formaPodnosioca/prijava/datumPodnosenjaPrvobitnePrijave"/>
                            </td>
                        </tr>
                        <tr class="field">
                            <td colspan="2"><b>Polje broj VIII ZAHTEV ZA PRIZNANJE PRAVA PRVENSTVA IZ RANIJIH PRIJAVA</b></td>
                        </tr>
                        <tr>
                            <td>Datum podnosenja ranije prijave</td>
                            <td>Broj ranije prijave</td>
                            <td>Dvoslovna oznaka drzave, regionalne ili medjunarodne organizacije</td>
                        </tr>
                       
                        <xsl:for-each select = "zahtev/formaPodnosioca/zahtevZaPriznanjePrava/prijave/ranijaPrijava">
                            <tr> 
                                <xsl:value-of select="@broj"/>
                                <td><xsl:value-of select = "datum"/></td> 
                                <td><xsl:value-of select = "brojPrijave" separator="&#xA;"/></td> 
                                <td><xsl:value-of select = "drzavaIliOrganizacija" separator="&#xA;"/></td>
                            </tr>
                        </xsl:for-each>
                        <xsl:if test="zahtev/formaPodnosioca/zahtevZaPriznanjePrava/podaciOOstalimPravimaNaListu2 = 'true'">
                            <tr>Dodatni list:</tr>
                            <tr>
                                <td>
                                    <xsl:value-of select="zahtev/formaPodnosioca/dodatniList2"/>
                                </td>
                            </tr>
                        </xsl:if>
                    </table>
                </div>
                
            </body>
            
        </html>
    </xsl:template>
</xsl:stylesheet>