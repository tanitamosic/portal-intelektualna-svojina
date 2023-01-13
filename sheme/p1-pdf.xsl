<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:proj="http://localhost:3030/tipovi"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:fo="http://www.w3.org/1999/XSL/Format"
    exclude-result-prefixes="xs"
    version="2.0">
    <xsl:template match="fo:table-cell">
        <fo:table-cell padding-left="7px" number-columns-spanned="2" margin-bottom="5px">
            <xsl:apply-templates />
        </fo:table-cell>
    </xsl:template>
    <xsl:template match="fo:table-row">
        <fo:table-row border-before="1px solid black">
            <xsl:apply-templates />
        </fo:table-row>
    </xsl:template>
    <xsl:template match="/">
        <fo:root>

            <fo:layout-master-set>
                <fo:simple-page-master master-name="P1">
                    <fo:region-body margin="0.75in" />
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="P1">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-family="sans-serif" font-size="24px" font-weight="bold"
                        padding="10px">
                        Zahtev za priznanje patenta
                    </fo:block>

                    <fo:block>
                        <fo:table table-layout="fixed" width="100%" font-family="sans-serif"
                            margin="50px auto 50px auto" border="3px solid black">
                            <fo:table-column column-width="25%" />
                            <fo:table-column column-width="25%" />
                            <fo:table-column column-width="25%" />
                            <fo:table-column column-width="25%" />

                            <fo:table-body>
                                <fo:table-row border-bottom="1px solid black">
                                    <fo:table-cell number-columns-spanned="4" padding="7px"
                                        font-weight="bold">
                                        <fo:block>Polje broj I NAZIV PRONALASKA</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding="7px" number-columns-spanned="2">
                                        <fo:block>Na srpskom: <xsl:value-of
                                                select="zahtev/formaPodnosioca/nazivPronalaska/srpski" /></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding="7px" number-columns-spanned="2">
                                        <fo:block>Na engleskom: <xsl:value-of
                                                select="zahtev/formaPodnosioca/nazivPronalaska/engleski" /></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row border-bottom="1px solid black">
                                    <fo:table-cell padding="7px" font-weight="bold" number-columns-spanned="2">
                                        <fo:block>Polje broj II PODNOSILAC PRIJAVE</fo:block>
                                    </fo:table-cell>
                                    <xsl:if
                                        test="zahtev/formaPodnosioca/podnosilacPrijave/jePronalazac = 'true'">
                                        <fo:table-cell padding="7px" number-columns-spanned="2">
                                            <fo:block>Podnosilac prijave je pronalazac</fo:block>
                                        </fo:table-cell>
                                    </xsl:if>
                                    <xsl:if
                                        test="zahtev/formaPodnosioca/podnosilacPrijave/jePronalazac != 'true'">
                                        <fo:table-cell padding="7px" number-columns-spanned="2">
                                            <fo:block>Podnosilac prijave nije pronalazac</fo:block>
                                        </fo:table-cell>
                                    </xsl:if>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding="7px" number-columns-spanned="4">
                                        <fo:block>Adresa: <xsl:value-of
                                                select="zahtev/formaPodnosioca/podnosilacPrijave/proj:adresa/*"
                                                separator=",&#xa;" /></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding="7px" number-columns-spanned="4">
                                        <fo:block>Kontakt: <xsl:value-of
                                                select="zahtev/formaPodnosioca/podnosilacPrijave/proj:kontakt/*"
                                                separator=",&#xa;" /></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row border-bottom="1px solid black">
                                    <fo:table-cell padding="7px" font-weight="bold" number-columns-spanned="4">
                                        <fo:block>Polje broj III PRONALAZAC</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding="7px" number-columns-spanned="4">
                                        <fo:block>
                                            <xsl:value-of
                                                select="zahtev/formaPodnosioca/pronalazac/proj:ime" />
        &#160; 
                                        <xsl:value-of
                                                select="zahtev/formaPodnosioca/pronalazac/proj:prezime" />
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding="7px" number-columns-spanned="4">
                                        <fo:block> Adresa: <xsl:value-of
                                                select="zahtev/formaPodnosioca/pronalazac/proj:adresa/*"
                                                separator=",&#xa;" />
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding="7px" number-columns-spanned="4">
                                        <fo:block> Kontakt: <xsl:value-of
                                                select="zahtev/formaPodnosioca/pronalazac/proj:kontakt/proj:*"
                                                separator=",&#xa;" />
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row border-bottom="1px solid black">
                                    <fo:table-cell font-weight="bold" padding="7px">
                                        <fo:block>Polje broj IV</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding="7px" number-columns-spanned="2">
                                        <fo:block>
                                            <xsl:value-of
                                                select="zahtev/formaPodnosioca/posrednik/vrstaPosrednika" />
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding="7px">
                                        <fo:block>Adresa: <xsl:value-of
                                            select="zahtev/formaPodnosioca/posrednik/proj:adresa/*" separator=",&#xa;"/></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding="7px" number-columns-spanned="4">
                                        <fo:block>Kontakt: <xsl:value-of
                                                select="zahtev/formaPodnosioca/posrednik/proj:kontakt/proj:*"
                                                separator=",&#xa;" /></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row border-bottom="1px solid black">
                                    <fo:table-cell padding="7px" font-weight="bold">
                                        <fo:block>Polje broj V</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding="7px" number-columns-spanned="4">
                                        <fo:block>Adresa za dostavljanje: <xsl:value-of
                                            select="zahtev/formaPodnosioca/adresaZaDostavljanje/proj:adresa/proj:*" separator=",&#xa;"/></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row border-bottom="1px solid black">
                                    <fo:table-cell padding="7px" font-weight="bold">
                                        <fo:block>Polje broj VI NACIN DOSTAVLJANJA</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding="7px" number-columns-spanned="3">
                                        <fo:block>
                                            <xsl:value-of
                                                select="zahtev/formaPodnosioca/nacinDostavljanja" />
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row border-bottom="1px solid black">
                                    <fo:table-cell padding="7px" font-weight="bold" >
                                        <fo:block>Polje broj VII</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding="7px" number-columns-spanned="2">
                                        <fo:block>
                                            <xsl:value-of
                                                select="zahtev/formaPodnosioca/prijava/vrstaPrijave" />
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding="7px" number-columns-spanned="2">
                                        <fo:block>Broj prvobitne prijave / osnovne prijave, odnosno
        osnovnog patenta: <xsl:value-of select="zahtev/formaPodnosioca/prijava/brojPrvobitnePrijave" /></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding="7px" number-columns-spanned="2">
                                        <fo:block>Datum podnosenja prvobitne prijave / osnovne
        prijave: <xsl:value-of
                                                select="zahtev/formaPodnosioca/prijava/datumPodnosenjaPrvobitnePrijave" /></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row border-bottom="1px solid black">
                                    <fo:table-cell padding="7px" number-columns-spanned="4" font-weight="bold">
                                        <fo:block>Polje broj VIII ZAHTEV ZA PRIZNANJE PRAVA
        PRVENSTVA IZ RANIJIH PRIJAVA</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding="7px" border-bottom="1px solid black" border-right="1px solid black">
                                        <fo:block>Datum podnosenja ranije prijave</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding="7px" border-bottom="1px solid black" border-right="1px solid black">
                                        <fo:block>Broj ranije prijave</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding="7px" border-bottom="1px solid black" number-columns-spanned="2">
                                        <fo:block>Dvoslovna oznaka drzave, regionalne ili
        medjunarodne organizacije</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <xsl:for-each
                                    select="zahtev/formaPodnosioca/zahtevZaPriznanjePrava/prijave">
                                    <fo:table-row>
                                        <fo:table-cell padding="7px" border-bottom="1px solid black" border-right="1px solid black">
                                            <fo:block>
                                                <xsl:value-of select="ranijaPrijava/datum" />
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="7px" border-bottom="1px solid black" border-right="1px solid black">
                                            <fo:block>
                                                <xsl:value-of select="ranijaPrijava/brojPrijave" />
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="7px" number-columns-spanned="2" border-bottom="1px solid black">
                                            <fo:block>
                                                <xsl:value-of
                                                    select="ranijaPrijava/drzavaIliOrganizacija" />
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </xsl:for-each>
                                <xsl:if
                                    test="zahtev/formaPodnosioca/zahtevZaPriznanjePrava/podaciOOstalimPravimaNaListu2 = 'true'">
                                    <fo:table-row>
                                        <fo:table-cell padding="7px">
                                            <fo:block>Dodatni list:</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding="7px">
                                            <fo:block>
                                                <xsl:value-of
                                                    select="zahtev/formaPodnosioca/dodatniList2" />
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </xsl:if>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>


</xsl:stylesheet>