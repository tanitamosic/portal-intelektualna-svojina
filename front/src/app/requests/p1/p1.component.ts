import {Component} from '@angular/core';
import {RequestService} from "../../services/request.service";
// import * as Xonomy from 'xonomy'
declare const Xonomy: any;

@Component({
  selector: 'app-p1',
  templateUrl: './p1.component.html',
  styleUrls: ['./p1.component.scss']
})
export class P1Component {

  constructor(private requestService: RequestService) {
  }

  ngOnInit() {
    this.initializeXonomy();
  }

  initializeXonomy(): void {
    const xmlData = `<zahtev>
    <srpskiNazivPronalaska></srpskiNazivPronalaska>
    <engleskiNazivPronalaska></engleskiNazivPronalaska>
    <podnosilacJePronalazac></podnosilacJePronalazac>
    <podnosilacPrijave>
        <kontakt>
            <email></email>
            <telefon></telefon>
            <faks></faks>
        </kontakt>
        <adresa>
            <ulica></ulica>
            <broj></broj>
            <mesto></mesto>
            <postanskiBroj></postanskiBroj>
        </adresa>
    </podnosilacPrijave>
    <pronalazac>
        <kontakt>
            <email></email>
            <telefon></telefon>
            <faks></faks>
        </kontakt>
        <adresa>
            <ulica></ulica>
            <broj></broj>
            <mesto></mesto>
            <postanskiBroj></postanskiBroj>
        </adresa>
    </pronalazac>
    <pronalazacZeliBitiNaveden></pronalazacZeliBitiNaveden>
    <vrstaPosrednika></vrstaPosrednika>
    <posrednik>
        <kontakt>
            <email></email>
            <telefon></telefon>
            <faks></faks>
        </kontakt>
        <adresa>
            <ulica></ulica>
            <broj></broj>
            <mesto></mesto>
            <postanskiBroj></postanskiBroj>
        </adresa>
    </posrednik>
    <adresaZaDostavljanje>
        <ulica></ulica>
        <broj></broj>
        <mesto></mesto>
        <postanskiBroj></postanskiBroj>
    </adresaZaDostavljanje>
    <nacinDostavljanja></nacinDostavljanja>
    <vrstaPrijave></vrstaPrijave>
    <brojPrvobitnePrijave></brojPrvobitnePrijave>
    <datumPodnosenjaPrvobitnePrijave></datumPodnosenjaPrvobitnePrijave>
    <ranijePrijave>
<!--        <ranijaPrijava>-->
<!--            <datum></datum>-->
<!--            <brojPrijave></brojPrijave>-->
<!--            <drzavaIliOrganizacija></drzavaIliOrganizacija>-->
<!--        </ranijaPrijava>-->
    </ranijePrijave>
    <imaDodatnogLista></imaDodatnogLista>
    <dodatniList></dodatniList>
</zahtev>`;
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

        "srpskiNazivPronalaska": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        },
        "engleskiNazivPronalaska": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        },
        "podnosilacJePronalazac": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askPicklist,
          askerParameter: ["true", "false"],
        },
        "podnosilacPrijave": {
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
        },
        "pronalazac": {
          menu: [{
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
            }
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
        },
        "pronalazacZeliBitiNaveden": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askPicklist,
          askerParameter: ["true", "false"],
        },
        "vrstaPosrednika": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askPicklist,
          askerParameter: ["Punomocnik za zastupanje", "Punomocnik za prijem pismena", "Zajednicki predstavnik"],
        },
        "posrednik": {
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
        },
        "adresaZaDostavljanje": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        },
        "nacinDostavljanja": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askPicklist,
          askerParameter: ["Iskljucivo elektronskim putem u formi elektronskog dokumenta", "U papirnoj formi"],
        },
        "vrstaPrijave": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askPicklist,
          askerParameter: ["Dopunska prijava", "Izdvojena prijava"],
        },
        "brojPrvobitnePrijave": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        },
        "datumPodnosenjaPrvobitnePrijave": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        },
        "imaDodatnogLista": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askPicklist,
          askerParameter: ["true", "false"],
        },
        "dodatniList": {
          hasText: true,
          oneliner: false,
          asker: Xonomy.askString,
          isReadOnly: function (jsElement: any) {
            // Determine if the second tag should be read-only based on the value of the first tag
            let firstTagValue = jsElement.parent().getChildElements('imaDodatnogLista')[0].text;
            if (firstTagValue === 'false') {
              jsElement.children = []; // Clear the text content
            }
            return firstTagValue === 'true' || firstTagValue === 'false';
          },

        }, "ranijePrijave": {
          menu: [{
            caption: "Dodaj <ranijaPrijava/>",
            action: Xonomy.newElementChild,
            actionParameter: "<ranijaPrijava><brojPrijave></brojPrijave><datum></datum><drzavaIliOrganizacija></drzavaIliOrganizacija></ranijaPrijava>",
          }]
        }, "ranijaPrijava": {
          menu: [{
            caption: "Ukloni <ranijaPrijava>",
            action: Xonomy.deleteElement,
            actionParameter: "<ranijaPrijava/>",
          }
          ]
        }, "brojPrijave": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        }, "datum": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        }, "drzavaIliOrganizacija": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        },
        "ime": {
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
      }
    }

    const editorElement = document.getElementById('xml-editor');
    Xonomy.render(xmlData, editorElement, docSpec);
  }

  saveXmlChanges(): void {
    let modifiedXml = Xonomy.harvest(); // Retrieve the modified XML
    modifiedXml = modifiedXml.replaceAll("xml:space='preserve'", '')
    const request = this.requestService.postRequest(modifiedXml, 'P1');
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
