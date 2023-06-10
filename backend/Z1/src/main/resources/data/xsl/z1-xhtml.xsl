<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:proj="http://localhost:3030/tipovi"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs" version="2.0">
    <xsl:output method="xml" />
    <xsl:template match="/zahtev">
        <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
            <head>
                <title>Zahtev za priznanje Ziga</title>
                <style type="text/css">
                    table {
						border-collapse: collapse;
						margin: 10px;
						width: 100%;
						border:0px;
                    }
                    th, td {
						text-align: left;
						padding: 3px;
						border:1px;
						border-bottom:1px solid black;
						border-right:1px solid black;
                    }

                    tr{
                    	border:1px;
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
							<xsl:value-of select="concat(podnosilac/proj:naziv_preduzeca, ', ')"></xsl:value-of> <xsl:value-of select="concat(podnosilac/proj:pib, ', ')" ></xsl:value-of> <xsl:value-of select="concat(podnosilac/proj:ime, ' ')"></xsl:value-of> <xsl:value-of select="concat(podnosilac/proj:prezime, ', ')"></xsl:value-of>
							<br/>      
							<xsl:value-of select="podnosilac/proj:adresa/*" separator=" "></xsl:value-of>
	                     </td>
					 	</tr>
					     <tr>
						 	<td style="border-left:1px solid black;">telefon: <xsl:value-of select="podnosilac/proj:kontakt/proj:telefon" /></td>
						    <td>e-mail: <xsl:value-of select="podnosilac/proj:kontakt/proj:email" /></td>
						    <td>faks: <xsl:value-of select="podnosilac/proj:kontakt/proj:faks" /></td>
						 </tr>
					 <tr><th colspan="3" style="border-left:1px solid black;">2. Punomocnik</th></tr>
						<tr>
						 <td colspan="3" style="border-left:1px solid black;">
							<xsl:value-of select="punomocnik/proj:naziv_preduzeca"></xsl:value-of> <xsl:value-of select="punomocnik/proj:pib" ></xsl:value-of> <xsl:value-of select="punomocnik/proj:ime"></xsl:value-of> <xsl:value-of select="punomocnik/proj:prezime"></xsl:value-of>
							<br/>    
							<xsl:value-of select="punomocnik/proj:adresa/*" separator=" "></xsl:value-of>
	                     </td>
					 	</tr>
					     <tr>
						 	<td style="border-left:1px solid black;">telefon: <xsl:value-of select="punomocnik/proj:kontakt/proj:telefon" /></td>
						    <td>e-mail: <xsl:value-of select="punomocnik/proj:kontakt/proj:email" /></td>
						    <td>faks: <xsl:value-of select="punomocnik/proj:kontakt/proj:faks" /></td>
						 </tr>
					 <tr><th colspan="3" style="border-left:1px solid black;">3. Predstavnik</th></tr>
						<tr>
						 <td colspan="3" style="border-left:1px solid black;">
							<xsl:value-of select="zajednickiPredstavnik/proj:naziv_preduzeca"></xsl:value-of> <xsl:value-of select="zajednickiPredstavnik/proj:pib" ></xsl:value-of> <xsl:value-of select="zajednickiPredstavnik/proj:ime"></xsl:value-of> <xsl:value-of select="zajednickiPredstavnik/proj:prezime"></xsl:value-of>
							<br/>
							<xsl:value-of select="zajednickiPredstavnik/proj:adresa/*" separator=",&#xa;"></xsl:value-of>
	                     </td>
					 	</tr>
					     <tr>
						 	<td style="border-left:1px solid black;">telefon: <xsl:value-of select="zajednickiPredstavnik/proj:kontakt/proj:telefon" /></td>
						    <td>e-mail: <xsl:value-of select="zajednickiPredstavnik/proj:kontakt/proj:email" /></td>
						    <td>faks: <xsl:value-of select="zajednickiPredstavnik/proj:kontakt/proj:faks" /></td>
						 </tr>
					 </table>
			 <table>
				  <tr>
				  	<th colspan="2" style="border-left:1px solid black;">4. Prijava se odnosi za:</th>
				  	<th colspan="2" rowspan="2" style="border-top:1px solid black;" >c) izgled znaka:</th>
				  </tr>
				  <tr>
					<th style="border-left:1px solid black;">a) </th>
					<xsl:value-of select="podaci_o_zigu/vrsta_ziga"/>
    			  </tr>
				  <tr>
				 	<th style="border-left:1px solid black;"> b)</th>
				 	<xsl:value-of select="podaci_o_zigu/format_ziga"/>
				 	<td colspan="2" rowspan="5">
						<img style="width: 250px; height: 250px;" src="http://localhost:8085/z1/download/img/{podaci_o_zigu/izgled_ziga}" alt="Zig" />
				 	</td>
			      </tr>
			      
			      <xsl:if test="podaci_o_zigu/boja">
			      	<tr><td colspan="2" style="border-left:1px solid black;">
			      		<b>5. Naznacenje boje, odnosno boja iz kojih se znak sastoji:</b> <br/>
				  		<xsl:value-of select="podaci_o_zigu/boja"></xsl:value-of>
		      		</td></tr>
			      </xsl:if>
			      
			      <xsl:if test="podaci_o_zigu/transliteracija">
			      	<tr><td colspan="2" style="border-left:1px solid black;">
			      		<b>6. Transliteracija znaka:</b> <br/>
						  <xsl:value-of select="podaci_o_zigu/transliteracija"></xsl:value-of>
			  		</td></tr>
			      </xsl:if>
			      
				  <xsl:if test="podaci_o_zigu/prevod">
			      	<tr><td colspan="2" style="border-left:1px solid black;">
			      		<b>7. Prevod znaka</b> <br/>
						<xsl:value-of select="podaci_o_zigu/prevod"></xsl:value-of>
			  		</td></tr>
			      </xsl:if>
			      
				  <xsl:if test="podaci_o_zigu/opis">
			      	<tr><td colspan="2" style="border-left:1px solid black;">
			      		<b>8. Opis znaka</b> <br/>
						<xsl:value-of select="podaci_o_zigu/opis"></xsl:value-of>
			  		</td></tr>
			      </xsl:if>
			      
			      </table>
			 <table>
			      
					<tr><th colspan="3" style="border-left:1px solid black;">9. Brojevi klasa robe i usluga prema Nicanskoj klasifikaciji</th></tr>
					<xsl:for-each select="podaci_o_zigu/klasa">
						<tr>
							<td colspan="3" style="border:1px solid black;"><xsl:value-of select="self::klasa"></xsl:value-of>, </td>
						</tr>
					</xsl:for-each>
					<tr><td colspan="3" style="border-left:1px solid black;">
						<b>10. Zatrazeno pravo prvenstva i osnov</b> <br/>
						<xsl:value-of select="podaci_o_zigu/pravo_prvenstva"></xsl:value-of>
					</td></tr>
			      
			      <tr>
				  		<th style="border-left:1px solid black;">11. Placene takse</th>
				  		<th>Dinara</th>
				  		<th rowspan="5" style="vertical-align: top; text-align: center;">Potpis podnosioca zahteva</th>
			  		</tr>
			  		<tr>
				  		<td style="border-left:1px solid black;">a) Osnovna taksa</td>
				  		<td><xsl:value-of select="takse/osnovna_taksa"></xsl:value-of></td>
			  		</tr>
			  		<tr>
				  		<td style="border-left:1px solid black;">b) za <xsl:value-of select="count(podaci_o_zigu/klasa)" /> klasa</td>
				  		<td><xsl:value-of select="takse/za_klase"></xsl:value-of></td>
			  		</tr>
			  		<tr>
				  		<td style="border-left:1px solid black;">c) Za graficko resenje</td>
				  		<td><xsl:value-of select="takse/za_graficko_resenje"></xsl:value-of></td>
			  		</tr>
			  		<tr>
				  		<td style="border-left:1px solid black;">UKUPNO</td>
				  		<td><xsl:value-of select="sum(takse/osnovna_taksa | takse/za_klase | takse/za_graficko_resenje)" /></td>
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
							<xsl:value-of select="zavod/brojPrijave"></xsl:value-of><br/><br/>
							Datum podnosenja: <br/>
							<xsl:value-of select="zavod/datumPodnosenja"></xsl:value-of></td>
				  	</tr>
					<xsl:for-each select="zavod/prilozi/prilog">
						<tr>
							<td style="border-left:1px solid black;">
								<xsl:value-of select="self::prilog"></xsl:value-of>
							</td>
						</tr>
					</xsl:for-each>
				</table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>