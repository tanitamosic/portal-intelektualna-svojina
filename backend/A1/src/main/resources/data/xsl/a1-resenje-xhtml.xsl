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
                <title>Rešenje zahteva za unošenje u evidenciju i deponovanje patenata</title>
                <style type="text/css">
                    table {
                        border-collapse: collapse;
                        margin: 0;
                        width: 95%;
                        border: 1px;
                        border-color: black;
                    }
                    th, td {
                        text-align: left;
                        padding: 3px;
                        border: 1px;
                        border-color: black;
                        border-collapse: collapse;
                    }
                    tr:nth-child(even){ background-color: #f2f2f2 }
                    tr { border: 1px; border-color: black; border-collapse: collapse; }
                </style>
            </head>
            <body>
                <center>
                    <h3>Rešenje zahteva za unošenje u evidenciju i deponovanje patenata</h3>
                </center>

                <table>
                    <tr>
                        <td>Sifra zahteva</td>
                        <td>
                            <xsl:value-of select="resenje_zahteva/sifra"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Datum obrade</td>
                        <td>
                            <xsl:value-of select="resenje_zahteva/datum_obrade"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <xsl:element name="a">
                                <xsl:attribute name="href">
                                    http://localhost:8084/download/pdf/<xsl:value-of select="resenje_zahteva/sifra"/>
                                </xsl:attribute>
                                Referenca na zahtev
                            </xsl:element>
                        </td>
                    </tr>
                </table>
                <br/>
                <table>
                    <tr>
                        <th colspan="2">Službenik koji je obradio zahtev</th>
                    </tr>
                    <tr>
                        <td>Ime i prezima</td>
                        <td>
                            <xsl:value-of select="resenje_zahteva/ime_sluzbenika"/> <xsl:value-of select="resenje_zahteva/prezime_sluzbenika"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Email adresa</td>
                        <td>
                            <xsl:value-of select="resenje_zahteva/email_sluzbenika"/>
                        </td>
                    </tr>
                </table>
                <br/>
                <table>
                    <xsl:choose>
                        <xsl:when test="resenje_zahteva/odbijen='false'">
                            <tr>
                                <td>Status zahteva</td>
                                <td>
                                    <xsl:attribute name="style">
                                        <xsl:value-of select="'color:green;'"/>
                                    </xsl:attribute>
                                    PRIHVACEN
                                </td>
                            </tr>
                            <tr>
                                <td>Sifra</td>
                                <td>
                                    <xsl:attribute name="style">
                                        <xsl:value-of select="'color:red;'"/>
                                    </xsl:attribute>
                                    <xsl:value-of select="resenje_zahteva/sifra"/>
                                </td>
                            </tr>
                        </xsl:when>
                        <xsl:otherwise>
                            <tr>
                                <td>Status zahteva</td>
                                <td>
                                    <xsl:attribute name="style">
                                        <xsl:value-of select="'color:red;'"/>
                                    </xsl:attribute>
                                    ODBIJEN
                                </td>
                            </tr>
                            <tr>
                                <td>Razlog odbijanja</td>
                                <td>
                                    <xsl:value-of select="resenje_zahteva/razlog_odbijanja"/>
                                </td>
                            </tr>
                        </xsl:otherwise>
                    </xsl:choose>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>