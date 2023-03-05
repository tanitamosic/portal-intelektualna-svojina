<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:proj="http://localhost:3030/tipovi"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:fo="http://www.w3.org/1999/XSL/Format"
    exclude-result-prefixes="xs"
    version="2.0">
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="A1">
                    <fo:region-body margin="0.75in" />
                </fo:simple-page-master>
            </fo:layout-master-set>
            
            <fo:page-sequence master-reference="A1">
                <fo:flow flow-name="xsl-region-body">
                    <fo:table table-layout="fixed" width="100%" font-family="sans-serif"
                        margin="10px auto 10px auto" border="3px solid black">
                        <fo:table-column column-width="50%" />
                        <fo:table-column column-width="50%" />
                        
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell padding="7px" number-columns-spanned="2"><fo:block font-size="13px" font-weight="bold">ZAVOD ZA INTELEKTUALNU SVOJINU&#160;&#160;&#160;&#160;&#160;&#160;&#160;
                                    &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
                                    &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
                                    &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
                                    &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;OBRAZAC A-1</fo:block>
                                    <fo:block >Beograd, Kneginje Ljubice 5</fo:block>
                                    <fo:block font-size="16px" text-align="center" font-weight="bold">ZAHTEV ZA UNOŠENJE U EVIDENCIJU I DEPONOVANJE AUTORSKIH DELA</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="7px" number-columns-spanned="2">
                                    <fo:block font-size="14px" margin-top="10px">1) Podnosilac - ime, prezime, adresa i državljanstvo autora ili drugog nosioca autorskog prava ako je podnosilac fizičko lice, odnosno poslovno ime i sedište nosioca autora prava ako je podnosilac pravno lice*:</fo:block>>
                                    <fo:block margin-left="5px"
                                        margin-top="5px"
                                        font-size="14px"
                                        font-weight="bold">
                                    <xsl:value-of select="zahtev/podnosilac_zahteva/lice/proj:naziv_preduzeca"></xsl:value-of>&#160;
                                    <xsl:value-of select="zahtev/podnosilac_zahteva/lice/proj:pib" ></xsl:value-of>&#160;
                                    <xsl:value-of select="zahtev/podnosilac_zahteva/lice/proj:ime"></xsl:value-of>&#160;
                                    <xsl:value-of select="zahtev/podnosilac_zahteva/lice/proj:prezime"></xsl:value-of>&#160;
                                    
                                </fo:block>
                                    <fo:block margin-left="5px"
                                        margin-top="5px"
                                        font-size="14px"
                                        font-weight="bold">
                                    <xsl:value-of select="zahtev/podnosilac_zahteva/lice/proj:adresa/*" separator=",&#xa;"></xsl:value-of>
                                </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="7px">
                                    <fo:block font-size="14px" margin-top="10px">Telefon: <xsl:value-of select="zahtev/podnosilac_zahteva/lice/proj:kontakt/proj:telefon"></xsl:value-of></fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="7px">
                                    <fo:block font-size="14px" margin-top="10px">E-Mail: <xsl:value-of select="zahtev/podnosilac_zahteva/lice/proj:kontakt/proj:email"></xsl:value-of></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            
                            <fo:table-row>
                                <fo:table-cell padding="7px" number-columns-spanned="2">
                                    <fo:block font-size="14px" margin-top="10px">2) Pseudonim ili znak autora, (ako ga ima):</fo:block>
                                    <fo:block margin-left="5px"
                                        margin-top="5px"
                                        font-size="14px"
                                        font-weight="bold"><xsl:value-of select="zahtev/pseudonim_podnosioca"></xsl:value-of>&#160;</fo:block>
                                    <fo:block font-size="14px" margin-top="10px">3) Ime, prezime i adresa punomocnika, ako se prijava odnosi preko punomocnika:</fo:block>
                                    <fo:block margin-left="5px"
                                        margin-top="5px"
                                        font-size="14px"
                                        font-weight="bold">
                                    <xsl:value-of select="zahtev/punomocnik/lice/proj:naziv_preduzeca"></xsl:value-of>&#160;
                                    <xsl:value-of select="zahtev/punomocnik/lice/proj:pib" ></xsl:value-of>&#160;
                                    <xsl:value-of select="zahtev/punomocnik/lice/proj:ime"></xsl:value-of>&#160;
                                    <xsl:value-of select="zahtev/punomocnik/lice/proj:prezime"></xsl:value-of>&#160;
                                </fo:block>
                                    <fo:block margin-left="5px"
                                        margin-top="5px"
                                        font-size="14px"
                                        font-weight="bold">
                                    <xsl:value-of select="zahtev/punomocnik/lice/proj:adresa/*" separator=",&#xa;"></xsl:value-of>
                                </fo:block>
                                    <fo:block font-size="14px" margin-top="10px">4) Naslov autorskog dela, odnosno alternativni naslov, ako ga ima, po kome autorsko delo moze da se identifikuje*:</fo:block>
                                    <fo:block margin-left="5px"
                                        margin-top="5px"
                                        font-size="14px"
                                        font-weight="bold"><xsl:value-of select="zahtev/naslov_dela" separator=",&#xa;"></xsl:value-of></fo:block>
                                
                                    <fo:block font-size="14px" margin-top="10px"> 5) Podaci o naslovu autorskog dela na kome se zasniva delo prerade, ako je u pitanju autorsko delo prerade, kao i podatak o autoru izvornog dela:</fo:block>                        
                                    <fo:block margin-left="5px"
                                        margin-top="5px"
                                        font-size="14px"
                                        font-weight="bold"><xsl:value-of select="zahtev/podaci_o_naslovu_izvornog_dela/naslov"></xsl:value-of></fo:block>
                                    <fo:block margin-left="5px"
                                        margin-top="5px"
                                        font-size="14px"
                                        font-weight="bold"><xsl:for-each select="zahtev/podaci_o_naslovu_izvornog_dela/autor">
                                            <fo:block margin-left="5px"
                                                margin-top="5px"
                                                font-size="14px"
                                                font-weight="bold"> <xsl:value-of select="proj:ime"></xsl:value-of> &#160;
                                        <xsl:value-of select="proj:prezime"></xsl:value-of> </fo:block>&#160;
                                </xsl:for-each></fo:block>
                                
                                    <fo:block font-size="14px" margin-top="10px">6) Podaci o vrsti autorskog dela (knjzevno delo, muzicko delo, likovno delom racunarski program i dr)*:</fo:block>
                                    <fo:block margin-left="5px"
                                        margin-top="5px"
                                        font-size="14px"
                                        font-weight="bold"> <xsl:value-of select="zahtev/vrsta_dela"></xsl:value-of> </fo:block>    
                                
                                    <fo:block font-size="14px" margin-top="10px">7) Podaci o formi zapisa autorskog dela (stampaani tekst, opticki disk i slicno)*:</fo:block>
                                    <fo:block margin-left="5px"
                                        margin-top="5px"
                                        font-size="14px"
                                        font-weight="bold"> <xsl:value-of select="zahtev/forma_dela"></xsl:value-of> </fo:block>  
                                
                                    <fo:block font-size="14px" margin-top="10px">8) Podaci o autory ako podnosilac prijave iz tacke 1. ovog zahteva nije autor i to: prezime, ime, adresa i drzavljanstvo autora(grupe autora ili koautora), a ako su u pitanju jedan ili vise autora koji nisu zivi, imena autora i godine smrti autora a ako je u pitanju autorsko delo anonimnog autora navod da je autorsko delo delo anonimnog autora: </fo:block>
                                    <fo:block margin-left="5px"
                                        margin-top="5px"
                                        font-size="14px"
                                        font-weight="bold"> <xsl:for-each select="zahtev/podaci_o_autoru/autor">
                                            <fo:block margin-left="5px"
                                                margin-top="5px"
                                                font-size="14px"
                                                font-weight="bold"> <xsl:value-of select="proj:ime"></xsl:value-of> </fo:block>  
                                            <fo:block margin-left="5px"
                                                margin-top="5px"
                                                font-size="14px"
                                                font-weight="bold"> <xsl:value-of select="proj:prezime"></xsl:value-of> </fo:block>  
                                            <fo:block margin-left="5px"
                                                margin-top="5px"
                                                font-size="14px"
                                                font-weight="bold"><xsl:value-of select="proj:adresa/*" separator=",&#xa;"></xsl:value-of></fo:block>
                                            <fo:block margin-left="5px"
                                                margin-top="5px"
                                                font-size="14px"
                                                font-weight="bold"><xsl:value-of select="godina_smrti"></xsl:value-of></fo:block>
                                    
                                    
                                </xsl:for-each> </fo:block> 
                                    <fo:block font-size="14px" margin-top="10px">9) Podatak da li je u pitanju autorsko delo stvoreno u radnom odnosu:</fo:block>
                                    <fo:block margin-left="5px"
                                        margin-top="5px"
                                        font-size="14px"
                                        font-weight="bold"><xsl:value-of select="zahtev/delo_stvoreno_u_radnom_odnosu"></xsl:value-of></fo:block>
                                
                                    <fo:block font-size="14px" margin-top="10px">10) Nacin koriscenja autorskog dela ili nameravani nacin koriscenja autorskog dela:</fo:block>
                                    <fo:block margin-left="5px"
                                        margin-top="5px"
                                        font-size="14px"
                                        font-weight="bold"><xsl:value-of select="zahtev/nacin_koriscenja_dela"></xsl:value-of></fo:block>
                                
                                    <fo:block font-size="14px" margin-top="10px">11) Prilozi koji se podnose uz zahtev:</fo:block>
                                    <fo:block margin-left="5px"
                                        margin-top="5px"
                                        font-size="14px"
                                        font-weight="bold">Opis Dela:<xsl:value-of select="zahtev/prilozi_uz_zahtev/opis_dela"></xsl:value-of></fo:block>
                                    <fo:block margin-left="5px"
                                        margin-top="5px"
                                        font-size="14px"
                                        font-weight="bold">Format Primera:<xsl:value-of select="zahtev/prilozi_uz_zahtev/format_primera"></xsl:value-of></fo:block>
                                    <fo:block margin-left="5px"
                                        margin-top="5px"
                                        font-size="14px"
                                        font-weight="bold">Naziv Fajla:<xsl:value-of select="zahtev/prilozi_uz_zahtev/naziv_fajla"></xsl:value-of></fo:block>
                                
                                
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="7px" number-columns-spanned="2">
                                    <fo:block font-size="14px" margin-top="10px">Broj Prijave: <b><xsl:value-of select="zahtev/@sifra"></xsl:value-of></b></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="7px" number-columns-spanned="2">
                                    <fo:block font-size="14px" margin-top="10px">Datum podnosenja: <b><xsl:value-of select="zahtev/datum_podnosenja_zahteva"></xsl:value-of></b></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
    
</xsl:stylesheet>