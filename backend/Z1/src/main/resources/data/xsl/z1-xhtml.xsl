<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:proj="http://localhost:3030/tipovi"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs" version="2.0">
    <xsl:output method="xml" />
    <xsl:template match="/z1:zahtev">
        <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
            <head>
                <title>Zahtev za priznanje Ziga</title>
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
                
                <center>
                	<h3>Zahtev za priznanje HTML Ziga</h3>
                	<h5>Zavod za intelektualnu svojinu, Kneginje Ljubice broj 5, 11000 Beograd</h5>
                </center>
                
                <table>
                	<tr>
                		<th colspan="3" style="border-top:1px solid black; border-left:1px solid black;">1. Podnosilac prijave</th>
                	</tr>
						<tr>
						 <td colspan="3" style="border-left:1px solid black;">
							<xsl:value-of select="concat(z1:podnosilac/proj:naziv_preduzeca, ', ')"></xsl:value-of> <xsl:value-of select="concat(z1:podnosilac/proj:pib, ', ')" ></xsl:value-of> <xsl:value-of select="concat(z1:podnosilac/proj:ime, ' ')"></xsl:value-of> <xsl:value-of select="concat(z1:podnosilac/proj:prezime, ', ')"></xsl:value-of>
							<br/>      
							<xsl:value-of select="z1:podnosilac/proj:adresa/*" separator=" "></xsl:value-of>
	                     </td>
					 	</tr>
					     <tr>
						 	<td style="border-left:1px solid black;">telefon: <xsl:value-of select="z1:podnosilac/proj:kontakt/proj:telefon" /></td>
						    <td>e-mail: <xsl:value-of select="z1:podnosilac/proj:kontakt/proj:email" /></td>
						    <td>faks: <xsl:value-of select="z1:podnosilac/proj:kontakt/proj:faks" /></td>
						 </tr>
					 <tr><th colspan="3" style="border-left:1px solid black;">2. Punomocnik</th></tr>
						<tr>
						 <td colspan="3" style="border-left:1px solid black;">
							<xsl:value-of select="z1:punomocnik/proj:naziv_preduzeca"></xsl:value-of> <xsl:value-of select="z1:punomocnik/proj:pib" ></xsl:value-of> <xsl:value-of select="z1:punomocnik/proj:ime"></xsl:value-of> <xsl:value-of select="z1:punomocnik/proj:prezime"></xsl:value-of>
							<br/>    
							<xsl:value-of select="z1:punomocnik/proj:adresa/*" separator=" "></xsl:value-of>
	                     </td>
					 	</tr>
					     <tr>
						 	<td style="border-left:1px solid black;">telefon: <xsl:value-of select="z1:punomocnik/proj:kontakt/proj:telefon" /></td>
						    <td>e-mail: <xsl:value-of select="z1:punomocnik/proj:kontakt/proj:email" /></td>
						    <td>faks: <xsl:value-of select="z1:punomocnik/proj:kontakt/proj:faks" /></td>
						 </tr>
					 <tr><th colspan="3" style="border-left:1px solid black;">3. Predstavnik</th></tr>
						<tr>
						 <td colspan="3" style="border-left:1px solid black;">
							<xsl:value-of select="z1:zajednicki_predstavnik/proj:naziv_preduzeca"></xsl:value-of> <xsl:value-of select="z1:zajednicki_predstavnik/proj:pib" ></xsl:value-of> <xsl:value-of select="z1:zajednicki_predstavnik/proj:ime"></xsl:value-of> <xsl:value-of select="z1:zajednicki_predstavnik/proj:prezime"></xsl:value-of>
							<br/>
							<xsl:value-of select="z1:zajednicki_predstavnik/proj:adresa/*" separator=",&#xa;"></xsl:value-of>
	                     </td>
					 	</tr>
					     <tr>
						 	<td style="border-left:1px solid black;">telefon: <xsl:value-of select="z1:zajednicki_predstavnik/proj:kontakt/proj:telefon" /></td>
						    <td>e-mail: <xsl:value-of select="z1:zajednicki_predstavnik/proj:kontakt/proj:email" /></td>
						    <td>faks: <xsl:value-of select="z1:zajednicki_predstavnik/proj:kontakt/proj:faks" /></td>
						 </tr>
					 </table>
			 <table>
				  <tr>
				  	<th colspan="2" style="border-left:1px solid black;">4. Prijava se odnosi za:</th>
				  	<th colspan="2" rowspan="2" style="border-top:1px solid black;" >c) izgled znaka:</th>
				  </tr>
				  <tr>
					<th style="border-left:1px solid black;">a)</th>
					<xsl:value-of select="z1:podaci_o_zigu/z1:vrsta_ziga"></xsl:value-of>
    			  </tr>
				  <tr>
				 	<th style="border-left:1px solid black;"> b)</th>
				 	<xsl:value-of select="z1:podaci_o_zigu/z1:format_ziga"></xsl:value-of>
				 	<td colspan="2" rowspan="5">
				 		<xsl:element name="img">
			 				<xsl:attribute name="width">100%</xsl:attribute>
			 				<xsl:attribute name="maxWidth">250px</xsl:attribute>
			 				<xsl:attribute name="maxHeight">250px</xsl:attribute>
			              	<xsl:attribute name="src">
								<xsl:value-of select="z1:podaci_o_zigu/z1:izgled_ziga"></xsl:value-of>
			              	</xsl:attribute>
			            </xsl:element>
				 	</td>
			      </tr>
			      
			      <xsl:if test="z1:podaci_o_zigu/z1:boja">
			      	<tr><td colspan="2" style="border-left:1px solid black;">
			      		<b>5. Naznacenje boje, odnosno boja iz kojih se znak sastoji:</b> <br/>
				  		<xsl:value-of select="z1:podaci_o_zigu/z1:boja"></xsl:value-of>
		      		</td></tr>
			      </xsl:if>
			      
			      <xsl:if test="z1:podaci_o_zigu/z1:transliteracija">
			      	<tr><td colspan="2" style="border-left:1px solid black;">
			      		<b>6. Transliteracija znaka:</b> <br/>
						  <xsl:value-of select="z1:podaci_o_zigu/z1:transliteracija"></xsl:value-of>
			  		</td></tr>
			      </xsl:if>
			      
				  <xsl:if test="z1:podaci_o_zigu/z1:prevod">
			      	<tr><td colspan="2" style="border-left:1px solid black;">
			      		<b>7. Prevod znaka</b> <br/>
						<xsl:value-of select="z1:podaci_o_zigu/z1:prevod"></xsl:value-of>
			  		</td></tr>
			      </xsl:if>
			      
				  <xsl:if test="z1:podaci_o_zigu/z1:opis">
			      	<tr><td colspan="2" style="border-left:1px solid black;">
			      		<b>8. Opis znaka</b> <br/>
						<xsl:value-of select="z1:podaci_o_zigu/z1:opis"></xsl:value-of>
			  		</td></tr>
			      </xsl:if>
			      
			      </table>
			 <table>
			      
					<tr><th colspan="3" style="border-left:1px solid black;">9. Brojevi klasa robe i usluga prema Nicanskoj klasifikaciji</th></tr>
					<xsl:for-each select="z1:podaci_o_zigu/z1:klasa">
						<tr>
							<td colspan="3" style="border:1px solid black;"><xsl:value-of select="self::z1:klasa"></xsl:value-of>, </td>
						</tr>
					</xsl:for-each>
					<xsl:if test="//sz:Zatrazeno_pravo_prvenstva_i_osnov">
					<tr><td colspan="3" style="border-left:1px solid black;">
						<b>10. Zatrazeno pravo prvenstva i osnov</b> <br/>
						<xsl:value-of select="z1:podaci_o_zigu/z1:pravo_prvenstva"></xsl:value-of>
					</td></tr>
					</xsl:if>
			      
			      <tr>
				  		<th style="border-left:1px solid black;">11. Placene takse</th>
				  		<th>Dinara</th>
				  		<th rowspan="5" style="vertical-align: top; text-align: center;">Potpis podnosioca zahteva</th>
			  		</tr>
			  		<tr>
				  		<td style="border-left:1px solid black;">a) Osnovna taksa</td>
				  		<td><xsl:value-of select="z1:takse/z1:osnovna_taksa"></xsl:value-of></td>
			  		</tr>
			  		<tr>
				  		<td style="border-left:1px solid black;">b) za <xsl:value-of select="count(z1:podaci_o_zigu/z1:klasa)" /> klasa</td>
				  		<td><xsl:value-of select="z1:takse/z1:za_klase"></xsl:value-of></td>
			  		</tr>
			  		<tr>
				  		<td style="border-left:1px solid black;">c) Za graficko resenje</td>
				  		<td><xsl:value-of select="z1:takse/z1:za_graficko_resenje"></xsl:value-of></td>
			  		</tr>
			  		<tr>
				  		<td style="border-left:1px solid black;">UKUPNO</td>
				  		<td><xsl:value-of select="sum(z1:takse/z1:osnovna_taksa | z1:takse/z1:za_klase | z1:takse/z1:za_graficko_resenje)" /></td>
			  		</tr>
				</table>
				
				<br/>
				
				<center>
                	<h4>POPUNJAVA ZAVOD</h4>
                </center>
				
				<table>			
				  	<tr>
						<th colspan="2" style="border-top:1px solid black; border-left:1px solid black;">Prilozi uz zahtev:</th>
						<td rowspan="7" style="text-align: center; border-top:1px solid black;">Broj prijave ziga: <br/>
							<xsl:value-of select="z1:zavod/z1:broj_prijave"></xsl:value-of><br/><br/>
							Datum podnosenja: <br/>
							<xsl:value-of select="z1:zavod/z1:datum_podnosenja"></xsl:value-of></td>
				  	</tr>
					<xsl:for-each select="z1:zavod/z1:prilozi/z1:prilog">
						<tr>
							<td style="border-left:1px solid black;">
								<xsl:value-of select="self::z1:prilog"></xsl:value-of>
							</td>
						</tr>
					</xsl:for-each>
				</table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>