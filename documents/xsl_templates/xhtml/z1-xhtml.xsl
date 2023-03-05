<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:proj="http://localhost:3030/tipovi"
    xmlns:z1="http://localhost:3030/z1"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0">
    <xsl:output method="xml" />
    
    <xsl:template match="/z1:zahtev">
        <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
            <head>
                <title>Zahtev Ž1</title>
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
                    width: 800px;
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
                    .smallerT{
                    margin-left: auto;
                    margin-right: auto;
                    width: 400px;
                    border: 2px solid black;
                    }
                </style>
            </head>
            
            <body>
                <div style="text-align:center;">
                    <p><h1><b>ZAHTEV ZA PRIZNJANJE ŽIGA</b></h1></p>
                    <p><b>Zavodu za intelektualnu svojinu, Kneginje Ljubice 5, 110000 Beograd</b></p>
                </div>
                
                <table style="margin-left:570px;margin-top:20px;margin-right:20px;margin-bottom:20px;">
                    <tr>
                        <td><p class="question"><b>1. Podnosilac prijave:</b> ime i prezime/poslovno ime, ulica i broj, postanski broj, mesto, drzava prebivalista/sedista:</p></td>
                    </tr>
                    <tr>
                        <td>
                            <p class="answer">
                                <xsl:value-of select="z1:podnosilac/proj:naziv_preduzeca"></xsl:value-of>&#160;
                                <xsl:value-of select="z1:podnosilac/proj:pib" ></xsl:value-of>&#160;
                                <xsl:value-of select="z1:podnosilac/proj:ime"></xsl:value-of>&#160;
                                <xsl:value-of select="z1:podnosilac/proj:prezime"></xsl:value-of>&#160;
                        
                            </p>
                            <p class="answer">
                                <xsl:value-of select="z1:podnosilac/proj:adresa/*" separator=",&#xa;"></xsl:value-of>
                            </p>
                            <p class="question">Telefon: <xsl:value-of select="z1:podnosilac/lice/proj:kontakt/proj:telefon"></xsl:value-of></p>
                            <p class="question">E-Mail: <xsl:value-of select="z1:podnosilac/proj:kontakt/proj:email"></xsl:value-of></p>
                            <p class="question">Faks: <xsl:value-of select="z1:podnosilac/proj:kontakt/proj:faks"></xsl:value-of></p>
                        </td>
                    </tr>
                    <tr>
                        <td><p class="question"><b>2. Punomocnik: </b> ime i prezime/poslovno ime, ulica i broj, postanski broj, mesto, drzava prebivalista/sedista: </p></td>
                        
                    </tr>
                    <tr>
                        <td>
                            <p class="answer">
                                <xsl:value-of select="z1:punomocnik/proj:naziv_preduzeca"></xsl:value-of>&#160;
                                <xsl:value-of select="z1:punomocnik/proj:pib" ></xsl:value-of>&#160;
                                <xsl:value-of select="z1:punomocnik/proj:ime"></xsl:value-of>&#160;
                                <xsl:value-of select="z1:punomocnik/proj:prezime"></xsl:value-of>&#160;
                                
                            </p>
                            <p class="answer">
                                <xsl:value-of select="z1:punomocnik/proj:adresa/*" separator=",&#xa;"></xsl:value-of>
                            </p>
                            <p class="question">Telefon: <xsl:value-of select="z1:punomocnik/lice/proj:kontakt/proj:telefon"></xsl:value-of></p>
                            <p class="question">E-Mail: <xsl:value-of select="z1:punomocnik/proj:kontakt/proj:email"></xsl:value-of></p>
                            <p class="question">Faks: <xsl:value-of select="z1:punomocnik/proj:kontakt/proj:faks"></xsl:value-of></p>
                        </td>
                    </tr>
                    <tr>
                        <td><p class="question"><b>3. Podaci o zajednickom predstavniku ako postoji vise podnosilaca prijave: </b> </p></td>
                    </tr>
                    <tr>
                        <td>
                            <p class="answer">
                                <xsl:value-of select="z1:zajednicki_predstavnik/proj:naziv_preduzeca"></xsl:value-of>&#160;
                                <xsl:value-of select="z1:zajednicki_predstavnik/proj:pib" ></xsl:value-of>&#160;
                                <xsl:value-of select="z1:zajednicki_predstavnik/proj:ime"></xsl:value-of>&#160;
                                <xsl:value-of select="z1:zajednicki_predstavnik/proj:prezime"></xsl:value-of>&#160;
                                
                            </p>
                            <p class="answer">
                                <xsl:value-of select="z1:zajednicki_predstavnik/proj:adresa/*" separator=",&#xa;"></xsl:value-of>
                            </p>
                            <p class="question">Telefon: <xsl:value-of select="z1:zajednicki_predstavnik/lice/proj:kontakt/proj:telefon"></xsl:value-of></p>
                            <p class="question">E-Mail: <xsl:value-of select="z1:zajednicki_predstavnik/proj:kontakt/proj:email"></xsl:value-of></p>
                            <p class="question">Faks: <xsl:value-of select="z1:zajednicki_predstavnik/proj:kontakt/proj:faks"></xsl:value-of></p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p class="question"><b> Vrsta Znaka: </b>  <xsl:value-of select="z1:podaci_o_zigu/z1:vrsta_ziga"></xsl:value-of></p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p class="question"> <b>Format znaka: </b><xsl:value-of select="z1:podaci_o_zigu/z1:format_ziga"></xsl:value-of></p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p class="question"> <b>Izgled znaka: </b><xsl:value-of select="z1:podaci_o_zigu/z1:izgled_ziga"></xsl:value-of></p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p class="question"> <b>Naznacenje boje, odnosno boja iz kojih se znak sastoji: </b><xsl:value-of select="z1:podaci_o_zigu/z1:boja"></xsl:value-of></p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p class="question"><b> Transliteracija znaka: </b><xsl:value-of select="z1:podaci_o_zigu/z1:transliteracija"></xsl:value-of></p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p class="question"> <b>Prevod znaka: </b><xsl:value-of select="z1:podaci_o_zigu/z1:prevod"></xsl:value-of></p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p class="question"><b> Opis znaka: </b><xsl:value-of select="z1:podaci_o_zigu/z1:opis"></xsl:value-of></p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p class="question"><b> Klase robe i usluga prema Nicanskoj klasifikaciji: </b></p>
                            <xsl:for-each select="z1:podaci_o_zigu/z1:klasa">
                                <p style="font-size:15px;"><xsl:value-of select="self::z1:klasa"></xsl:value-of></p>
                            </xsl:for-each>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p class="question"><b> Zatrazeno pravo prvenstva i osnov: </b><xsl:value-of select="z1:podaci_o_zigu/z1:pravo_prvenstva"></xsl:value-of></p>
                        </td>
                    </tr>
                </table>
                <table style="margin-left:720px;margin-top:20px;margin-right:20px;margin-bottom:20px;" class="smallerT">
                    <tr>
                        <td style="border: 1px solid black;"><p class="answer">11. Placene takse:</p></td>
                        <td style="border: 1px solid black;"><p class="answer">Dinara:</p></td>
                    </tr>
                    <tr>
                        <td style="border: 1px solid black;"><p class="answer">a)osnovna taksa</p></td>
                        <td style="border: 1px solid black;"><p class="answer"><xsl:value-of select="z1:takse/z1:osnovna_taksa"></xsl:value-of> </p></td>
                    </tr>
                    <tr>
                        <td style="border: 1px solid black;"><p class="answer">b)za ______ klasa</p><p class="answer">c) za graficko resenje</p></td>
                        <td style="border: 1px solid black;"><p class="answer"><xsl:value-of select="z1:takse/z1:za_klase"></xsl:value-of> </p>
                            <p class="answer"><xsl:value-of select="z1:takse/z1:za_graficko_resenje"></xsl:value-of> </p></td>
                    </tr>
                </table>
                

                <table style="margin-left:720px;margin-top:20px;margin-right:20px;margin-bottom:20px;" class="smallerT">
                    <tr><td style="border: 1px solid black;"><div style="text-align:center;"><p class="answer"> POPUNJAVA ZAVOD</p></div></td></tr>
                    <tr><td style="border: 1px solid black;"><div style="text-align:center;"><p class="question"> <b>Prilozi uz zahtev:</b></p></div></td></tr>
                    <xsl:for-each select="z1:zavod/z1:prilozi/z1:prilog">
                        <tr>
                            <td style="border: 1px solid black;text-align:center;" ><p style="font-size:15px;"><xsl:value-of select="self::z1:prilog"></xsl:value-of></p></td>
                        </tr>
                    </xsl:for-each>
                    <tr></tr>
   
                    <tr>
                        <td style="text-align:center;"><p class="question">Broj prijave žiga: <b><xsl:value-of select="z1:zavod/z1:broj_prijave"></xsl:value-of></b></p></td>
                    </tr>   
                    <tr>
                        <td style="text-align:center;"><p class="answer">Datum podnosenja: <b><xsl:value-of select="z1:zavod/z1:datum_podnosenja"></xsl:value-of></b></p></td>
                    </tr>
  
                
                </table>
                
            </body>
            
        </html>
        
        
    </xsl:template>
    
</xsl:stylesheet>