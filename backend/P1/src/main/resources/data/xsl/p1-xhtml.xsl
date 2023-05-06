<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:proj="http://localhost:3030/tipovi"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs" version="2.0">

    <xsl:output method="xml" />
    <xsl:template match="/">
        <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
            <head>
                <title>Patent</title>
                <style type="text/css">
                    table {
                        margin: 10px;
                        width: 80%;
                        border:0px;
                        margin-left: auto;
                        margin-right: auto;
                    }
                    th, td {
                        text-align: left;
                        padding: 3px;
                        border:1px;
                        border-bottom-width:1px;
                        border-right-width:1px;
                        border-style: solid;
                        border-color: black;
                    }

                    tr{
                        border:0px;
                    }

                    body {
                        font-family: sans-serif;
                    }

                </style>
            </head>
            <body>
                <table style="width: 65%">
                    <tr>
                        <td colspan="2" style="text-align:center; border-left-width:3px; border-top-width:3px; border-right-width:3px;">Popunjava zavod</td>
                    </tr>
                    <tr>
                        <td style="border-left-width:3px;">Broj prijave</td>
                        <td><xsl:value-of select="zahtev/zavod/formaZaZavod/brojPrijave"/></td>
                    </tr>
                    <tr>
                        <td style="border-left-width:3px;">Datum prijema</td>
                        <td><xsl:value-of select="zahtev/zavod/formaZaZavod/datumPrijema"/></td>
                    </tr>
                    <tr>
                        <td style="border-left-width:3px;">Priznati datum podnosenja</td>
                        <td><xsl:value-of select="zahtev/zavod/formaZaZavod/datumPodnosenja"/></td>
                    </tr>
                    <tr>
                        <td style="border-left-width:3px; border-bottom-width:3px; border-right-width:3px; border-top-width:3px;">Pecat i potpis</td>
                        <td></td>
                    </tr>
                </table>
                <p><br/>
                    Republika Srbija<br/>
                    Zavod za intelektualnu svojinu<br/>
                    Kneginje Ljubice broj 5<br/>
                    11000 Beograd
                </p>
                <center>
                    <h3>ZAHTEV<br/>
                        ZA PRIZNANJE PATENTA</h3>
                </center>

                <table>
                    <tr><th colspan="3" style="border-top-width:3px; border-left-width:3px;">Polje broj I NAZIV PRONALASKA</th></tr>
                    <tr>
                        <td colspan="3" style="border-left-width:3px;">Na srpskom jeziku: <xsl:value-of select="zahtev/formaPodnosioca/nazivPronalaska/srpski" /><br/>
                            Na engleskom jeziku: <xsl:value-of select="zahtev/formaPodnosioca/nazivPronalaska/engleski" /></td>
                    </tr>
                    <tr>
                        <td colspan="3" style="border-left-width:3px;">
                            <b>Polje broj II PODNOSILAC PRIJAVE</b>
                            <xsl:if test="zahtev/formaPodnosioca/podnosilacPrijave/jePronalazac = 'true'">
                                Podnosilac prijave je pronalazac
                            </xsl:if>
                            <xsl:if test="zahtev/formaPodnosioca/podnosilacPrijave/jePronalazac != 'true'">
                                Podnosilac prijave nije pronalazac
                            </xsl:if>
                        </td>
                    </tr>
                    <tr>
                        <td rowspan="3" style="border-left-width:3px;">
                            Ime i prezime / Poslovno ime 
                            <br/><br/>
                            <xsl:value-of select="zahtev/formaPodnosioca/podnosilacPrijave/proj:ime" /> <xsl:value-of select="zahtev/formaPodnosioca/podnosilacPrijave/proj:prezime" />
                            <xsl:value-of select="zahtev/formaPodnosioca/podnosilacPrijave/proj:naziv_preduzeca" /> <xsl:value-of select="zahtev/formaPodnosioca/podnosilacPrijave/proj:pib" />
                        </td>
                        <td rowspan="3">
                            Ulica i broj, poštanski broj, mesto i država
                            <br/><br/>
                            <xsl:value-of select="zahtev/formaPodnosioca/podnosilacPrijave/proj:adresa/*" separator="&#x20;" />
                        </td>
                        <td>
                            Broj telefona
                            <br/>
                            <xsl:value-of select="zahtev/formaPodnosioca/podnosilacPrijave/proj:kontakt/proj:telefon" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Broj faksa
                            <br/>
                            <xsl:value-of select="zahtev/formaPodnosioca/podnosilacPrijave/proj:kontakt/proj:faks" />
                        </td>
                    </tr>
                    <tr>
                        <td rowspan="2">
                            E-pošta
                            <br/>
                            <xsl:value-of select="zahtev/formaPodnosioca/podnosilacPrijave/proj:kontakt/proj:email" />
                        </td>
                    </tr>
<!--                    <tr>-->
<!--                        <td colspan="2" style="border-left-width:1px;">Drzavljanstvo: <xsl:value-of select="//pat:Podaci_o_podnosiocu_prijave/pat:Drzavljanstvo"/></td>-->
<!--                    </tr>-->
<!--                    <tr>-->
<!--                        <td colspan="2">-->
<!--                        </td>-->
<!--                    </tr>-->
                        <!-- <td colspan="3" style="border-left-width:1px;">
                            <xsl:choose>
                                <xsl:when test="//pat:Putanja_do_priloga_podnosioca">
                                    <xsl:element name="a">
                                        <xsl:attribute name="href">
                                            http://localhost:8000/patent/download/prilog/<xsl:value-of select="//pat:Putanja_do_priloga_podnosioca"/>
                                        </xsl:attribute>
                                        Izjavu o zajedničkom predstavniku
                                    </xsl:element>
                                </xsl:when>
                            </xsl:choose>
                        </td> -->
<!--                    </tr>-->
                </table>

                <br/>

                <table>
                    <tr>
                        <td colspan="3" style="border-top-width:3px; border-left-width:3px;">
                            <b>Polje broj III PRONALAZAC</b>
                            <xsl:if test="zahtev/formaPodnosioca/pronalazac/zeliBitiNaveden = 'false'"> Pronalazac ne zeli da bude naveden u prijavi </xsl:if>
                            <xsl:if test="zahtev/formaPodnosioca/pronalazac/zeliBitiNaveden = 'true'"> Pronalazac zeli da bude naveden u prijavi </xsl:if>
                        </td>
                    </tr>
                    <xsl:if test="zahtev/formaPodnosioca/pronalazac/zeliBitiNaveden = 'true'"> <!-- informacije o pronalazacu se ispisuju samo ukoliko on to zeli -->
                        <tr>
                            <td rowspan="3" style="border-left-width:3px;">
                                Ime i prezime / Poslovno ime 
                            <br/><br/>
                            <xsl:value-of select="zahtev/formaPodnosioca/pronalazac/proj:ime" /> <xsl:value-of select="zahtev/formaPodnosioca/pronalazac/proj:prezime" /> 
                            <xsl:value-of select="zahtev/formaPodnosioca/pronalazac/proj:naziv_preduzeca" /> <xsl:value-of select="zahtev/formaPodnosioca/pronalazac/proj:pib" />
                            </td>
                            <td rowspan="3">
                                Ulica i broj, poštanski broj, mesto i država
                                <br/><br/>
                                <xsl:value-of select="zahtev/formaPodnosioca/pronalazac/proj:adresa/*" separator="&#x20;" />
                            </td>
                            <td>
                                Broj telefona
                                <br/>
                                <xsl:value-of select="zahtev/formaPodnosioca/pronalazac/proj:kontakt/proj:telefon" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Broj faksa
                                <br/>
                                <xsl:value-of select="zahtev/formaPodnosioca/pronalazac/proj:kontakt/proj:faks" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                E-pošta
                                <br/>
                                <xsl:value-of select="zahtev/formaPodnosioca/pronalazac/proj:kontakt/proj:email" />
                            </td>
                        </tr>
                        <tr>
                            <td/>
                            <!-- <td colspan="3" style="border-left-width:1px;">
                                <xsl:choose>
                                    <xsl:when test="//pat:Putanja_do_primera">
                                        <xsl:element name="a">
                                            <xsl:attribute name="href">
                                                http://localhost:8000/patent/download/prilog/<xsl:value-of select="//pat:Putanja_do_primera"/>
                                            </xsl:attribute>
                                            Izjava podnosilaca prijave o osnovu sticanja prava
                                        </xsl:element>
                                    </xsl:when>
                                </xsl:choose>
                            </td> -->
                        </tr>
                    </xsl:if>
                </table>

                <br/>

                <table>
                    <tr>
                        <td colspan="3" style="border-top-width:3px; border-left-width:3px;">
                            <b>Polje broj IV</b>
                            <xsl:value-of select="zahtev/formaPodnosioca/posrednik/vrstaPosrednika" />
                            <br/>
                            ZAJEDNICKI PREDSTAVNIK: <xsl:text>&#x20;</xsl:text>
                            <xsl:if test="zahtev/formaPodnosioca/posrednik/zajednickiPredstavnik = 'true'"> DA </xsl:if>
                            <xsl:if test="zahtev/formaPodnosioca/posrednik/zajednickiPredstavnik = 'false'"> NE </xsl:if>
                        </td>
                    </tr>
                    <tr>
                        <td rowspan="2" style="border-left-width:3px;">
                            <xsl:value-of select="zahtev/formaPodnosioca/posrednik/proj:ime" /> <xsl:value-of select="zahtev/formaPodnosioca/posrednik/proj:prezime" />
                            <xsl:value-of select="zahtev/formaPodnosioca/posrednik/proj:naziv_preduzeca" /> <xsl:value-of select="zahtev/formaPodnosioca/posrednik/proj:pib" />
                        </td>
                        <td rowspan="2">
                            Ulica i broj, poštanski broj, mesto i država
                            <br/><br/>
                            <xsl:value-of select="zahtev/formaPodnosioca/posrednik/proj:adresa/*" separator="&#x20;"/>
                        </td>
                        <td>
                            Broj telefona
                            <br/>
                            <xsl:value-of select="zahtev/formaPodnosioca/posrednik/proj:telefon"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            E-pošta
                            <br/>
                            <xsl:value-of select="zahtev/formaPodnosioca/posrednik/proj:emai" />
                        </td>
                    </tr>
                </table>
                <table>
                    <tr>
                        <th style="border-left-width:3px;">Polje broj V ADRESA ZA DOSTAVLJANJA</th>
                    </tr>
                    <tr>
                        <td style="border-left-width:3px;">Ulica i broj, poštanski broj i mesto:
                            <br/><br/>
                            <xsl:value-of select="zahtev/formaPodnosioca/adresaZaDostavljanje/proj:adresa/proj:*" separator="&#xa;"/>
                        </td>
                    </tr>
                </table>
                <table>
                    <tr>
                        <th style="border-left-width:3px;">Polje broj VI NACIN DOSTAVLJANJA</th>
                    </tr>
                    <tr>
                        <td style="border-left-width:3px;">
                            <xsl:value-of select="zahtev/formaPodnosioca/nacinDostavljanja" />
                        </td>
                    </tr>
                    <tr>
                        <th style="border-left-width:3px;">Polje broj VII
                            <xsl:value-of select="zahtev/formaPodnosioca/prijava/vrstaPrijave" />
                        </th>
                    </tr>
                    <tr>
                        <td style="border-left-width:3px;">
                            Broj prvobitne prijave / osnovne prijave, odnosno osnovnog patenta: <xsl:value-of select="zahtev/formaPodnosioca/prijava/brojPrvobitnePrijave" />
                        </td>
                    </tr>
                    <tr>
                        <td style="border-left-width:1px;">
                            Datum podnosenja prvobitne prijave / osnovne prijave: <xsl:value-of select="zahtev/formaPodnosioca/prijava/datumPodnosenjaPrvobitnePrijave" />
                        </td>
                    </tr>
                </table>
                <table>
                    <tr><th colspan="7" style="border-left-width:3px;">Polje broj VIII ZAHTEV ZA PRIZNANJE PRAVA PRVENSTVA IZ RANIJIH PRIJAVA</th></tr>
                    <tr>
                        <th colspan="2" style="border-left-width:3px;">
                            Broj ranije prijave
                        </th>
                        <th colspan="3">
                            Datum podnosenja ranije prijave
                        </th>
                        <th colspan="2">
                            Dvoslovna oznaka drzave, regionalne ili medjunarodne organizacije
                        </th>
                    </tr>
                    <xsl:for-each select="zahtev/formaPodnosioca/zahtevZaPriznanjePrava/prijave/ranijaPrijava">
                        <tr>
                            <td colspan="2" style="border-left-width:3px;">
                                <xsl:value-of select="brojPrijave" />
                            </td>
                            <td colspan="3">
                                <xsl:value-of select="datum" />
                            </td>
                            <td colspan="2">
                                <xsl:value-of select="drzavaIliOrganizacija" />
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>

                <xsl:if test="zahtev/formaPodnosioca/zahtevZaPriznanjePrava/podaciOOstalimPravimaNaListu2 = 'true'">
                    <center>Dodatni list:</center>
                    <center>
                        <xsl:value-of select="zahtev/formaPodnosioca/dodatniList2" />
                    </center>
                </xsl:if>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
