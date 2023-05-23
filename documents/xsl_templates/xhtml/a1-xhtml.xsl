<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:proj="http://localhost:3030/tipovi"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs" version="2.0">
    <xsl:output method="xml" />
    <xsl:template match="/">
        <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
            <head>
                <title>Zahtev za unošenje u evidenciju i deponovanje autorskih prava</title>
                <style type="text/css">
                    table {
                    margin: 10px;
                    width: 100%;
                    border:0px;
                    }
                    th, td {
                    text-align: left;
                    padding: 3px;
                    border:0px;
                    border-bottom-width:1px solid black;
                    border-right-width:1px solid black;
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
                <table>
                    <tr>
                        <td>
                            <b>ZAVOD ZA INTELEKTUALNU SVOJINU</b>
                            <br/>
                            Beograd, Knjeginje Ljubice 5
                        </td>
                        <td>
                            <b>OBRAZAC A-1</b>
                        </td>
                    </tr>
                    <br/>
                    <br/>
                    <br/>
                    <p style="text-align:center; font-size: 15px;">
                        <b>ZAHTEV ZA UNOŠENJE U EVIDENCIJU I DEPONOVANJE AUTORSKIH DELA</b>
                    </p>
                    <br/>
                    <br/>
                    <br/>
                </table>
                <br/>
                <table>
                    <tr>
                        <td style="border-left-width:1px;">
                            1) Podnosilac - ime, prezime, adresa i drzavljanstvo autora ili drugog nosioca autorskog
                            prava ako je podnosilac fizicko lice, odnosno poslovno ime i sediste nosioca autorskog prava
                            ako je podnosilac pravno lice:
                            <br/>
                            <br/>
                            <xsl:if test="zahtev/podnosilac_zahteva/lice[@xsi:type='proj:TFizickoLice']">
                                <xsl:value-of select="zahtev/podnosilac_zahteva/lice/proj:ime"/>
                                <br/>
                                <xsl:value-of select="zahtev/podnosilac_zahteva/lice/proj:prezime"/>,
                                <br/>
                                <xsl:value-of select="zahtev/podnosilac_zahteva/lice/proj:adresa/proj:*" separator=" "/>
                                
                            </xsl:if>

                            <xsl:if test="zahtev/podnosilac_zahteva/lice[@xsi:type='proj:TPravnoLice']">
                                <xsl:value-of select="zahtev/podnosilac_zahteva/lice/proj:naziv_preduzeca"/>,
                                <br/>
                                <xsl:value-of select="zahtev/podnosilac_zahteva/lice/proj:pib"/>
                            </xsl:if>
                        </td>
                    </tr>
                </table>
                <br/>

                <table style="margin: 5px">
                    <tr>
                        <td style="border-left-width:1px;">
                            telefon: <xsl:value-of select="zahtev/podnosilac_zahteva/lice/proj:kontakt/proj:telefon"/>
                        </td>
                        <td>
                            e-mail: <xsl:value-of select="zahtev/podnosilac_zahteva/lice/proj:kontakt/proj:email"/>
                        </td>
                    </tr>
                </table>
                <br/>
                <table>
                    <tr>
                        <td style="border-left-width:1px;">
                            2) Pseudonim ili znak autora, (ako ga ima):
                            <br/>
                            <br/>
                            <xsl:choose>
                                <xsl:when test="zahtev/pseudonim_podnosioca">
                                    <xsl:value-of select="zahtev/pseudonim_podnosioca"/>
                                </xsl:when>
                                <xsl:otherwise>
                                    Autor nema pseudonim.
                                </xsl:otherwise>
                            </xsl:choose>
                            <br/>
                            <br/>
                            3) Ime, prezime i adresa punomocnikam ako se prijava podnosi preko punomocnika:
                            <br/>
                            <br/>
                            <xsl:choose>
                                <xsl:when test="zahtev/punomocnik/lice/proj:ime">
                                    <xsl:value-of select="zahtev/punomocnik/lice/proj:ime"/>
                                    <br/>
                                    <xsl:value-of select="zahtev/punomocnik/lice/proj:prezime"/>,
                                    <br/>
                                    <xsl:value-of select="zahtev/punomocnik/lice/proj:adresa/proj:*" separator="&#x20;"/>
                                </xsl:when>
                                <xsl:otherwise>
                                    Prijava se ne odnosi preko punomocnika.
                                </xsl:otherwise>
                            </xsl:choose>
                            <br/>
                            <br/>
                            4) Naslov autorskog dela, odnosno alternativni naslov, ako ga ima, po kome autorsko delo
                            moze da se identifikuje:
                            <br/>
                            <br/>
                            <xsl:value-of select="zahtev/naslov_dela"/>
                            <br/>
                            <br/>

                            5) Podaci o naslovu autorskog dela na kome se zasniva delo prerade, kao i podatak o autoru
                            izvornog dela:
                            <br/>
                            <br/>
                            <xsl:choose>
                                <xsl:when test="zahtev/podaci_o_naslovu_izvonog_dela">
                                    Naslov:
                                    <xsl:value-of select="zahtev/podaci_o_naslovu_izvonog_dela/naslov"/>
                                    <br/>
                                    Autor:
                                    <xsl:value-of select="zahtev/podaci_o_naslovu_izvonog_dela/autor/proj:ime" />
                                    <xsl:value-of select="zahtev/podaci_o_naslovu_izvonog_dela/autor/proj:prezime" />
                                </xsl:when>
                                <xsl:otherwise>
                                    Autorsko delo nije zasnovano ni na jednom drugom delu.
                                </xsl:otherwise>
                            </xsl:choose>
                            <br/>
                            6) Podaci o vrsti autorskog dela (knjizevno delo, muzicko delo, likovno delo, racunarski
                            program i dr.):
                            <br/>
                            <br/>
                            <xsl:value-of select="zahtev/vrsta_dela"/>
                            <br/>
                            <br/>
                            7) Podaci o formi zapisa autorskog dela (stampani tekst, opticki disk i slicno):
                            <br/>
                            <br/>
                            <xsl:value-of select="zahtev/forma_dela"/>
                            <br/>
                            <br/>
                            8) Podaci o autoru:
                            <br/>
                            <br/>
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
                            <br/>
                            <br/>
                            <xsl:choose>
                                <xsl:when test="zahtev/delo_stvoreno_u_radnom_odnosu">
                                    Autorsko delo je stvoreno u radnom odnosu.
                                </xsl:when>
                                <xsl:otherwise>
                                    Autorsko delo nije stvoreno u radnom odnosu.
                                </xsl:otherwise>
                            </xsl:choose>
                            <br/>
                            <br/>
                            10) Nacin korišcenja ili nameravani nacin korišcenja autorskog dela:
                            <br/>
                            <br/>
                            <xsl:value-of select="zahtev/nacin_koriscenja_dela"/>
                            <br/>
                            <br/>

                        </td>
                    </tr>
                </table>
                <br/>

                <table>
                    <tr>
                        <td>
                            <div style="width: 100%">
                                <p style="text-align: center; font-weight:bold; margin-bottom: 15px">POPUNJAVA ZAVOD
                                </p>
                                <p>Prilozi uz prijavu:</p>
                                <br></br>
                                <br></br>
                                <xsl:value-of select="zahtev/prilozi_uz_zahtev/opis_dela"/>
                                <br></br>
                                <br></br>
                                <xsl:value-of select="zahtev/prilozi_uz_zahtev/format_primera"/>
                            </div>
                            <div style="width: 100%; float: right">
                                <table style="margin-top: 70px">
                                    <tr>
                                        <td style="font-size: 20px; padding:10px;">Broj prijave
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="font-size: 22px; font-weight: bold; padding:10px">
                                            <xsl:value-of select="/zahtev/@sifra"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="font-size: 20px; padding:10px;">
                                            Datum podnošenja:
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="font-size: 22px; padding:10px">
                                            <xsl:value-of select="zahtev/datum_podnosenja_zahteva"/>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
