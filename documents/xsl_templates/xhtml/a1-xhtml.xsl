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
                <title>Zahtev A1</title>
                <style>
                    .center.text {
                    text-align: center;
                    }
                    .gray.text {
                       color:white ;
                    }
                    table {
                    margin-left: auto;
                    margin-right: auto;
                    width: 1000px;
                    border: 2px solid black;
                    }
                    .field {
                    box-shadow: 0px 1px 0px 0px rgb(0, 0, 0);
                    }
                    td {
                    padding: 10px;
                    }
                    tr{
                    border: 1px solid black;
                    margin-bottom:0px;
                    margin-top:0px;
                    }
                    .question{
                    font-size:14px;
                    margin-top:10px;
                    }
                    .answer{
                    margin-left:5px;
                    margin-top:5px;
                    font-size:14px;
                    font-weight:bold;
                    }
                </style>
            </head>
            <body>
                <table style="margin-left:350px;margin-top:20px;margin-right:20px;margin-bottom:20px;">
                    <tr>
                        <td colspan="4"><p style="font-size:13px;"><b>ZAVOD ZA INTELEKTUALNU SVOJINU</b>&#160;&#160;&#160;&#160;&#160;&#160;&#160;
                            &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
                            &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
                            &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
                            &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;<b>OBRAZAC A-1</b></p>
                            <p style="color:gray;">Beograd, Kneginje Ljubice 5</p>
                            <p style="text-align:center;font-size:16px;"><b>ZAHTEV ZA UNOŠENJE U EVIDENCIJU I DEPONOVANJE AUTORSKIH DELA</b></p>
                        </td>
                    </tr>
                    <tr><td>
                        <p class="question">1) Podnosilac - ime, prezime, adresa i državljanstvo autora ili drugog nosioca autorskog prava ako je podnosilac fizičko lice, odnosno poslovno ime i sedište nosioca autora prava ako je podnosilac pravno lice*:</p>
                        <p class="answer">
                            <xsl:value-of select="zahtev/podnosilac_zahteva/lice/proj:naziv_preduzeca"></xsl:value-of>&#160;
                            <xsl:value-of select="zahtev/podnosilac_zahteva/lice/proj:pib" ></xsl:value-of>&#160;
                            <xsl:value-of select="zahtev/podnosilac_zahteva/lice/proj:ime"></xsl:value-of>&#160;
                            <xsl:value-of select="zahtev/podnosilac_zahteva/lice/proj:prezime"></xsl:value-of>&#160;
                            
                        </p>
                        <p class="answer">
                            <xsl:value-of select="zahtev/podnosilac_zahteva/lice/proj:adresa/*" separator=",&#xa;"></xsl:value-of>
                        </p>
                    </td></tr>
                    <tr><td><p class="question">Telefon: <xsl:value-of select="zahtev/podnosilac_zahteva/lice/proj:kontakt/proj:telefon"></xsl:value-of></p></td>
                        <td><p class="question">E-Mail: <xsl:value-of select="zahtev/podnosilac_zahteva/lice/proj:kontakt/proj:email"></xsl:value-of></p></td></tr>
                
                    <tr><td><p class="question">2) Pseudonim ili znak autora, (ako ga ima):</p>
                        <p class="answer"><xsl:value-of select="zahtev/pseudonim_podnosioca"></xsl:value-of>&#160;</p>
                        <p class="question">3) Ime, prezime i adresa punomocnika, ako se prijava odnosi preko punomocnika:</p>
                        <p class="answer">
                            <xsl:value-of select="zahtev/punomocnik/lice/proj:naziv_preduzeca"></xsl:value-of>&#160;
                            <xsl:value-of select="zahtev/punomocnik/lice/proj:pib" ></xsl:value-of>&#160;
                            <xsl:value-of select="zahtev/punomocnik/lice/proj:ime"></xsl:value-of>&#160;
                            <xsl:value-of select="zahtev/punomocnik/lice/proj:prezime"></xsl:value-of>&#160;
                        </p>
                        <p class="answer">
                            <xsl:value-of select="zahtev/punomocnik/lice/proj:adresa/*" separator=",&#xa;"></xsl:value-of>
                        </p>
                        <p class="question">4) Naslov autorskog dela, odnosno alternativni naslov, ako ga ima, po kome autorsko delo moze da se identifikuje*:</p>
                        <p class="answer"><xsl:value-of select="zahtev/naslov_dela" separator=",&#xa;"></xsl:value-of></p>

                        <p class="question"> 5) Podaci o naslovu autorskog dela na kome se zasniva delo prerade, ako je u pitanju autorsko delo prerade, kao i podatak o autoru izvornog dela:</p>                        
                        <p class="answer"><xsl:value-of select="zahtev/podaci_o_naslovu_izvornog_dela/naslov"></xsl:value-of></p>
                        <p class="answer"><xsl:for-each select="zahtev/podaci_o_naslovu_izvornog_dela/autor">
                            <p class="answer"> <xsl:value-of select="proj:ime"></xsl:value-of> &#160;
                             <xsl:value-of select="proj:prezime"></xsl:value-of> </p>&#160;
                        </xsl:for-each></p>
                        
                        <p class="question">6) Podaci o vrsti autorskog dela (knjzevno delo, muzicko delo, likovno delom racunarski program i dr)*:</p>
                        <p class="answer"> <xsl:value-of select="zahtev/vrsta_dela"></xsl:value-of> </p>    
                        
                        <p class="question">7) Podaci o formi zapisa autorskog dela (stampaani tekst, opticki disk i slicno)*:</p>
                        <p class="answer"> <xsl:value-of select="zahtev/forma_dela"></xsl:value-of> </p>  
                        
                        <p class="question">8) Podaci o autory ako podnosilac prijave iz tacke 1. ovog zahteva nije autor i to: prezime, ime, adresa i drzavljanstvo autora(grupe autora ili koautora), a ako su u pitanju jedan ili vise autora koji nisu zivi, imena autora i godine smrti autora a ako je u pitanju autorsko delo anonimnog autora navod da je autorsko delo delo anonimnog autora: </p>
                        <p class="answer"> <xsl:for-each select="zahtev/podaci_o_autoru/autor">
                            <p class="answer"> <xsl:value-of select="proj:ime"></xsl:value-of> </p>  
                            <p class="answer"> <xsl:value-of select="proj:prezime"></xsl:value-of> </p>  
                            <p class="answer"><xsl:value-of select="proj:adresa/*" separator=",&#xa;"></xsl:value-of></p>
                            <p class="answer"><xsl:value-of select="godina_smrti"></xsl:value-of></p>
                            
                            
                        </xsl:for-each> </p> 
                        <p class="question">9) Podatak da li je u pitanju autorsko delo stvoreno u radnom odnosu:</p>
                        <p class="answer"><xsl:value-of select="zahtev/delo_stvoreno_u_radnom_odnosu"></xsl:value-of></p>
                        
                        <p class="question">10) Nacin koriscenja autorskog dela ili nameravani nacin koriscenja autorskog dela:</p>
                        <p class="answer"><xsl:value-of select="zahtev/nacin_koriscenja_dela"></xsl:value-of></p>
                        
                        <p class="question">11) Prilozi koji se podnose uz zahtev:</p>
                        <p class="answer">Opis Dela:<xsl:value-of select="zahtev/prilozi_uz_zahtev/opis_dela"></xsl:value-of></p>
                        <p class="answer">Format Primera:<xsl:value-of select="zahtev/prilozi_uz_zahtev/format_primera"></xsl:value-of></p>
                        <p class="answer">Naziv Fajla:<xsl:value-of select="zahtev/prilozi_uz_zahtev/naziv_fajla"></xsl:value-of></p>
                            
                        
                    </td></tr>
                    <tr>
                        <td>
                            <p class="question">Broj Prijave: <b><xsl:value-of select="zahtev/@sifra"></xsl:value-of></b></p>
                        </td>
                    </tr>
                    <tr>
                        <td><p class="question">Datum podnosenja: <b><xsl:value-of select="zahtev/datum_podnosenja_zahteva"></xsl:value-of></b></p></td>
                    </tr>

                </table>
            
            </body>
        
        
        </html>
        
        
    </xsl:template>
    
</xsl:stylesheet>