import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {SearchService} from "../../services/search.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-userhome',
  templateUrl: './userhome.component.html',
  styleUrls: ['./userhome.component.css']
})
export class UserhomeComponent {
  rawSearchParam: string = '';
  rawSearchDoctype: string = 'A1';
  selectedOperator: string = 'OR';
  advancedSearchParam1: string = '';
  advancedSearchParam2: string = '';
  advancedSearchDoctype: string = 'A1';
  requestType: string = 'A1';
  requestsAndResolutions: any[] = [];
  advSearchMetadataTypes: { [key: string]: string[] } = {
    'A1': [],  // TODO: ovde dodajete svoj metadatatype enume
    'P1': ['VRSTA_PRIJAVE', 'EMAIL_PRONALAZACA', 'EMAIL_PODNOSIOCA', 'EMAIL_POSREDNIKA'],  // Ovo je moj MetadataType enum na backendu
    'Z1': []  // TODO: ovde dodajete svoj metadatatype enume
  }
  metadataParamType1: string = '';
  metadataParamType2: string = '';

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
      this.fillRequestsAndResolutions(values);
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

  async advancedSearch() {
    const request1: Observable<Object> = this.searchService.post(this.advancedSearchParam1, this.metadataParamType1, this.advancedSearchDoctype);
    const request2: Observable<Object> = this.searchService.post(this.advancedSearchParam2, this.metadataParamType2, this.advancedSearchDoctype);

    const response1: any = await request1.toPromise();
    const response2: any = await request2.toPromise();

    let collection1: HTMLCollection = response1.getElementsByTagName('item');
    let collection2: HTMLCollection = response2.getElementsByTagName('item');

    let finalResult = this.applyLogicOperator(collection1, collection2);
    console.log('Search result:', finalResult);

    this.fillRequestsAndResolutions(finalResult);
  }

  fillRequestsAndResolutions(array: any[]) {
    this.clearTable()
    array.forEach((e) => {
      let endpointPrefix = e.at(0).toLowerCase() + '1'
      let obj = {
        request: e,
        resolution: 'Resenje-' + e,
        url: 'http://localhost:4200/' + endpointPrefix + '/download'
      }
      this.requestsAndResolutions.push(obj);
    })
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

  applyLogicOperator(collection1: HTMLCollection, collection2: HTMLCollection) {
    let result: Set<String> = new Set<String>();
    switch (this.selectedOperator) {
      case 'OR': {
        for (let i = 0; i < collection1.length; i++) {
          result.add(collection1[i].textContent as string);
        }
        for (let i = 0; i < collection2.length; i++) {
          const item = collection2[i].textContent;
          result.add(item as string);
        }
        break;
      }
      case 'NOT': {
        for (let i = 0; i < collection1.length; i++) {
          const item = collection1[i].textContent as string;
          if (item !== null && !this.collectionContains(collection2, item)) {
            result.add(item);
          }
        }
        break;
      }
      case 'AND': {
        for (let i = 0; i < collection1.length; i++) {
          const item = collection1[i].textContent as string;
          if (item !== null && this.collectionContains(collection2, item)) {
            result.add(item);
          }
        }
        break;
      }
    }
    return Array.from(result);
  }

  collectionContains(collection: HTMLCollection, ele: string): boolean {
    for (let i = 0; i < collection.length; i++) {
      const e = collection[i].textContent;
      if (e === ele) return true;
    }
    return false;
  }
}
