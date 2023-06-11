import { Component } from '@angular/core';
import { RequestService } from "../../services/request.service";
// import * as Xonomy from 'xonomy'
declare const Xonomy: any;

@Component({
  selector: 'app-a1',
  templateUrl: './a1.component.html',
  styleUrls: ['./a1.component.scss']
})
export class A1Component {

  constructor(private requestService: RequestService) {
  }

  ngOnInit() {
    this.initializeXonomy();
  }

  initializeXonomy(): void {
    const xmlData = `<zahtev>
    <brojPrijave></brojPrijave>
    <podaci_o_naslovu_izvonog_dela_naslov></podaci_o_naslovu_izvonog_dela_naslov>
    <podaci_o_naslovu_izvonog_dela_autori>
<!--        <autor>-->
<!--            <adresa>-->
<!--                <mesto></mesto>-->
<!--                <postanski_broj></postanski_broj>-->
<!--                <ulica></ulica>-->
<!--                <broj></broj>-->
<!--            </adresa>-->
<!--            <kontakt>-->
<!--                <telefon></telefon>-->
<!--                <email></email>-->
<!--            </kontakt>-->
<!--            <ime></ime>-->
<!--            <prezime></prezime>-->
<!--            <godina_smrti></godina_smrti>-->
<!--        </autor>-->
    </podaci_o_naslovu_izvonog_dela_autori>
    <podnosilac_zahteva>
        <adresa>
            <mesto></mesto>
            <postanski_broj></postanski_broj>
            <ulica></ulica>
            <broj></broj>
        </adresa>
        <kontakt>
            <email></email>
        </kontakt>
        <naziv_preduzeca></naziv_preduzeca>
        <pib></pib>
    </podnosilac_zahteva>
    <pseudonim_podnosioca></pseudonim_podnosioca>
    <punomocnik>
        <adresa>
            <mesto></mesto>
            <postanski_broj></postanski_broj>
            <ulica></ulica>
            <broj></broj>
        </adresa>
        <kontakt>
            <telefon></telefon>
            <email></email>
        </kontakt>
        <ime></ime>
        <prezime></prezime>
    </punomocnik>
    <naslov_dela></naslov_dela>
    <vrsta_dela></vrsta_dela>
    <forma_dela></forma_dela>
    <autori>
<!--        <autor>-->
<!--            <adresa>-->
<!--                <mesto></mesto>-->
<!--                <postanski_broj></postanski_broj>-->
<!--                <ulica></ulica>-->
<!--                <broj></broj>-->
<!--            </adresa>-->
<!--            <kontakt>-->
<!--                <telefon></telefon>-->
<!--                <email></email>-->
<!--            </kontakt>-->
<!--            <ime></ime>-->
<!--            <prezime></prezime>-->
<!--            <godina_smrti></godina_smrti>-->
<!--        </autor>-->
    </autori>
    <nacin_koriscenja_dela></nacin_koriscenja_dela>
    <delo_stvoreno_u_radnom_odnosu></delo_stvoreno_u_radnom_odnosu>
    <prilozi_uz_zahtev_opis_dela></prilozi_uz_zahtev_opis_dela>
    <prilozi_uz_zahtev_format_primera></prilozi_uz_zahtev_format_primera>
    <prilozi_uz_zahtev_naziv_fajla></prilozi_uz_zahtev_naziv_fajla>
    <datum_podnosenja_zahteva></datum_podnosenja_zahteva>
</zahtev>
    
    `;
    let docSpec = {
      validate: function (jsElement: any) {
        for (let i = 0; i < jsElement.children.length; i++) {
          let jsChild = jsElement.children[i];
          if (jsChild.type == "element") { //if element
            docSpec.validate(jsChild); //recursion
          } else if (jsChild.type == "text") {
            if (jsChild.value == "") {
              Xonomy.warnings.push({
                // htmlID: jsAttribute.htmlID,
                text: "This attribute must not be empty."
              }
              );

            }
          }
        }
      },
      elements: {
        // adresa
        "ulica": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        },
        "broj": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        },
        "mesto": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        },
        "postanskiBroj": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        },

        // kontakt
        "email": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        },
        "telefon": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        },
        "faks": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        },
        "brojPrijave": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        }, "datum": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        }, "ime": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        }, "prezime": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        },
        "naziv_preduzeca": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        }, "pib": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        },
        "podaci_o_naslovu_izvonog_dela_naslov": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        },
        "pseudonim_podnosioca": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        }, "naslov_dela": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        }, "vrsta_dela": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        }, "forma_dela": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        }, "nacin_koriscenja_dela": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        }, "datum_podnosenja_zahteva": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        }, "delo_stvoreno_u_radnom_odnosu": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askPicklist,
          askerParameter: ["True", "False"]
        }, "prilozi_uz_zahtev_opis_dela": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        }, "prilozi_uz_zahtev_format_primera": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        }, "prilozi_uz_zahtev_naziv_fajla": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        }, "podnosilac_zahteva": {
          menu: [
            {
              caption: "Postavi na <TFizickoLice>",
              action: function (jsElement: any) {
                let xml = "<ime></ime>";
                let xml2 = "<prezime></prezime>"
                Xonomy.newElementChild(jsElement, xml);
                Xonomy.newElementChild(jsElement, xml2);
                Xonomy.refresh();
              },
              hideIf: function (jsElement: any) {
                return jsElement.hasChildElement("ime") || jsElement.hasChildElement("prezime");
              },
            },
            {
              caption: "Postavi na <TPravnoLice>",
              action: function (jsElement: any) {
                let xml = "<naziv_preduzeca></naziv_preduzeca>";
                let xml2 = "<pib></pib>"
                Xonomy.newElementChild(jsElement, xml);
                Xonomy.newElementChild(jsElement, xml2);
                Xonomy.refresh();
              },
              hideIf: function (jsElement: any) {
                return jsElement.hasChildElement("naziv_preduzeca") || jsElement.hasChildElement("pib");
              }
            },
          ]
        }, "punomocnik": {
          menu: [
            {
              caption: "Postavi na <TFizickoLice>",
              action: function (jsElement: any) {
                let xml = "<ime></ime>";
                let xml2 = "<prezime></prezime>"
                Xonomy.newElementChild(jsElement, xml);
                Xonomy.newElementChild(jsElement, xml2);
                Xonomy.refresh();
              },
              hideIf: function (jsElement: any) {
                return jsElement.hasChildElement("ime") || jsElement.hasChildElement("prezime");
              },
            },
            {
              caption: "Postavi na <TPravnoLice>",
              action: function (jsElement: any) {
                let xml = "<naziv_preduzeca></naziv_preduzeca>";
                let xml2 = "<pib></pib>"
                Xonomy.newElementChild(jsElement, xml);
                Xonomy.newElementChild(jsElement, xml2);
                Xonomy.refresh();
              },
              hideIf: function (jsElement: any) {
                return jsElement.hasChildElement("naziv_preduzeca") || jsElement.hasChildElement("pib");
              }
            },
          ]
        }, "podaci_o_naslovu_izvonog_dela_autori": {
          menu: [{
            caption: "Dodaj <autor/>",
            action: Xonomy.newElementChild,
            actionParameter: "<autor><adresa>mesto></mesto><postanski_broj></postanski_broj><ulica></ulica><broj></broj</adresa><kontakt><email></email></kontakt><ime></ime><prezime></prezime><godina_smrti></godina_smrti></autor>",
          }]
        }, "autor": {
          menu: [{
            caption: "Ukloni <autor>",
            action: Xonomy.deleteElement,
            actionParameter: "<autor/>",
          }
          ]
        }, "godina_smrti": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        }, "autori": {
          menu: [{
            caption: "Dodaj <autor/>",
            action: Xonomy.newElementChild,
            actionParameter: "<autor><adresa>mesto></mesto><postanski_broj></postanski_broj><ulica></ulica><broj></broj</adresa><kontakt><email></email></kontakt><ime></ime><prezime></prezime><godina_smrti></godina_smrti></autor>",
          }]
        }
      }
    };
    const editorElement = document.getElementById('xml-editor');
    Xonomy.render(xmlData, editorElement, docSpec);
  }

  saveXmlChanges(): void {
    let modifiedXml = Xonomy.harvest(); // Retrieve the modified XML
    modifiedXml = modifiedXml.replaceAll("xml:space='preserve'", '')
    const request = this.requestService.postRequest(modifiedXml, 'A1');
    request.subscribe((res) => {
      alert('Zahtev uspesno poslat');
      location.reload();
    }, (err) => {
      alert('Doslo je do greske.')
    })
  }

  back() {
    location.reload()
  }
}
