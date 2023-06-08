<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:proj="http://localhost:3030/tipovi"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                exclude-result-prefixes="xs" version="2.0">
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="Z1-resenje">
                    <fo:region-body margin="0.75in" />
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:declarations>
                <x:xmpmeta xmlns:x="adobe:ns:meta/">
                    <rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#">
                        <rdf:Description rdf:about="" xmlns:dc="http://purl.org/dc/elements/1.1/">
                            <dc:title>Rešenje zahteva za unošenje u evidenciju i deponovanje patenata</dc:title>
                            <dc:creator>Elita sa SIIT-a</dc:creator>
                            <dc:description>Resenje zahteva za priznanje patenta</dc:description>
                        </rdf:Description>
                    </rdf:RDF>
                </x:xmpmeta>
            </fo:declarations>

            <fo:page-sequence master-reference="Z1-resenje">
                <fo:flow flow-name="xsl-region-body">

                    <fo:block linefeed-treatment="preserve" text-align="center" font-size="16px" font-weight="bold">
                        <xsl:text>Rešenje zahteva za unošenje u evidenciju i deponovanje patenata</xsl:text>
                        <xsl:text>&#10;</xsl:text>
                    </fo:block>

                    <fo:table table-layout="fixed" width="95%"
                              margin="10px auto 10px auto" border="3px solid black" border-collapse="collapse">
                        <fo:table-body>
                            <fo:table-row border="1px solid black">
                                <fo:table-cell padding="3px">
                                    <fo:block>Broj prijave</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px">
                                    <fo:block><xsl:value-of select="resenje_zahteva/broj_prijave"/></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row border="1px solid black">
                                <fo:table-cell padding="3px">
                                    <fo:block>Datum obrade</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px">
                                    <fo:block><xsl:value-of select="resenje_zahteva/datum_obrade"/></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="3px" number-columns-spanned="2">
                                    <fo:block>
                                        <fo:basic-link external-destination="http://localhost:8083/download/pdf/{resenje_zahteva/broj_prijave}">Referenca na zahtev</fo:basic-link>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>

                    <fo:block linefeed-treatment="preserve">
                        <xsl:text>&#10;</xsl:text>
                    </fo:block>

                    <fo:table table-layout="fixed" width="95%"
                              margin="10px auto 10px auto" border="3px solid black" border-collapse="collapse">
                        <fo:table-body>
                            <fo:table-row border="1px solid black">
                                <fo:table-cell padding="3px" number-columns-spanned="2">
                                    <fo:block>Službenik koji je obradio zahtev</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row border="1px solid black">
                                <fo:table-cell padding="3px">
                                    <fo:block>Ime i prezime</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px">
                                    <fo:block><xsl:value-of select="resenje_zahteva/ime_sluzbenika"/> <xsl:value-of select="resenje_zahteva/prezime_sluzbenika"/></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row border="1px solid black">
                                <fo:table-cell padding="3px">
                                    <fo:block>Email</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px">
                                    <fo:block><xsl:value-of select="resenje_zahteva/email_sluzbenika"/></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>

                    <fo:block linefeed-treatment="preserve">
                        <xsl:text>&#10;</xsl:text>
                    </fo:block>

                    <fo:table table-layout="fixed" width="95%"
                              margin="10px auto 10px auto" border="3px solid black" border-collapse="collapse">
                        <fo:table-body>
                            <xsl:choose>
                                <xsl:when test="resenje_zahteva/odbijen='false'">
                                    <fo:table-row>
                                        <fo:table-cell padding="3px">
                                            <fo:block>Status zahteva</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="3px">
                                            <fo:block color="green">
                                                PRIHVACEN
                                            </fo:block>

                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell padding="3px">
                                            <fo:block>Sifra</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="3px">
                                            <fo:block>
                                                <xsl:value-of select="resenje_zahteva/sifra"/>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </xsl:when>
                                <xsl:otherwise>
                                    <fo:table-row>
                                        <fo:table-cell padding="3px">
                                            <fo:block>Status zahteva</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="3px">
                                            <fo:block color="red">
                                                ODBIJEN
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell padding="3px">
                                            <fo:block>Razlog odbijanja</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="3px">
                                            <fo:block>
                                                <xsl:value-of select="resenje_zahteva/razlog_odbijanja"/>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </xsl:otherwise>
                            </xsl:choose>
                        </fo:table-body>
                    </fo:table>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>