import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {SearchService} from "../../services/search.service";

@Component({
  selector: 'app-userhome',
  templateUrl: './userhome.component.html',
  styleUrls: ['./userhome.component.css']
})
export class UserhomeComponent {
  rawSearchParam: string = '';
  rawSearchDoctype: string = 'A1';
  selectedOperator: string = 'AND';
  advancedSearchParam1: string = '';
  advancedSearchParam2: string = '';
  advancedSearchDoctype: string = 'A1';
  requestType: string = 'A1';
  requestsAndResolutions: any[] = [];

  constructor(private route: Router, private searchService: SearchService) {
  }

  logout() {
    localStorage.clear();
    this.route.navigate(['/']).then(r => {location.reload()});
  }

  rawSearch() {
    const request = this.searchService.get(this.rawSearchParam, this.rawSearchDoctype);
    request.subscribe((res) => {
      console.log(res);
      const values = this.getResponseData(res);
      values.forEach((e) => {
        let obj = {
          request: e,
          resolution: 'Resenje-' + e,
          url: 'http://localhost:4200' + this.searchService.getUrlPrefix(this.rawSearchDoctype) + 'download'
        }
        this.requestsAndResolutions.push(obj);
      })
      console.log(values); // ['some_string1', 'some_string2']
    }, (err) =>{
      console.log(err);
      alert('Nesto je puklo.')
    })
  }

  private getResponseData(res: Object) {
    const xmlResponse = res as XMLDocument;
    const serializer = new XMLSerializer();
    const xmlString = serializer.serializeToString(xmlResponse);
    console.log(xmlString);
    // Create a DOMParser instance
    const parser = new DOMParser();
    // Parse the XML string
    const xmlDoc = parser.parseFromString(xmlString, 'text/xml');
    // Get the list of <item> elements
    const itemElements = xmlDoc.getElementsByTagName('item');
    // Create an array to store the extracted values
    const values: string[] = [];
    // Iterate over the <item> elements and extract their values
    for (let i = 0; i < itemElements.length; i++) {
      const itemElement = itemElements[i];
      const value = itemElement.textContent as string;
      values.push(value);
    }
    return values;
  }

  advancedSearch() {

  }

  createRequest() {
    switch (this.requestType) {
      case 'A1': break;
      case 'P1': {
        this.route.navigateByUrl('/create-p1-request').then(r => {})
        break;
      }
      case 'Z1': break;
    }
  }

  clearTable() {
    this.requestsAndResolutions = [];
  }
}
