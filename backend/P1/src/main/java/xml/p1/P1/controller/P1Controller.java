package xml.p1.P1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xml.p1.P1.dto.P1DTO;
import xml.p1.P1.dto.SearchDTO;
import xml.p1.P1.model.P1Resenje;
import xml.p1.P1.model.P1Zahtev;
import xml.p1.P1.service.P1Service;
import xml.p1.P1.service.SparqlService;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(value = "/")
public class P1Controller {

    @Autowired
    P1Service p1Service;
    @Autowired
    SparqlService sparqlService;

    @PostMapping(value="/post-p1", consumes="application/xml", produces="application/xml")
    public ResponseEntity<String> postP1zahtev(@RequestBody P1DTO dto) {
        try {
            p1Service.createP1Zahtev(dto);
        } catch (Exception e) {
            System.out.println(e.getClass());
            e.printStackTrace();
            return new ResponseEntity<>("nije uspelo", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("uspelo", HttpStatus.OK);
    }

    @PostMapping(value="/resi-p1", consumes="application/xml", produces="application/xml")
    public ResponseEntity<String> postP1resenje(@RequestBody P1Resenje resenje) {
        try {
            p1Service.createP1Resenje(resenje);
        } catch (Exception e) {
            System.out.println(e.getClass());
            e.printStackTrace();
            return new ResponseEntity<>("nije uspelo", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("uspelo", HttpStatus.OK);
    }

    @PostMapping(value="/advanced-search", consumes="application/xml", produces="application/xml")
    public ResponseEntity<List<String>> textSearchQuery(@RequestBody SearchDTO dto) {
        try {
            return new ResponseEntity<>(sparqlService.search(dto.getSearchParam(), dto.getTipMetapodatka()), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="text-search/{searchParam}", produces="application/xml")
    public ResponseEntity<List<P1Zahtev>> advancedSearchQuery(@PathVariable String searchParam) {
        return new ResponseEntity<>(p1Service.conductTextBasedSearch(searchParam), HttpStatus.OK);
    }
}
