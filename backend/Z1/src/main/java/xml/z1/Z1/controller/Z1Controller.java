package xml.z1.Z1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xml.z1.Z1.dto.SearchDTO;
import xml.z1.Z1.dto.Z1DTO;
import xml.z1.Z1.model.Z1Resenje;
import xml.z1.Z1.model.Z1Zahtev;
import xml.z1.Z1.service.Z1Service;
import xml.z1.Z1.service.SparqlService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/z1")
public class Z1Controller {

    @Autowired
    Z1Service z1Service;
    @Autowired
    SparqlService sparqlService;

    @PostMapping(value="/post-z1", consumes="application/xml", produces="application/xml")
    public ResponseEntity<String> postZ1zahtev(@RequestBody Z1DTO dto) {
        try {
            z1Service.createZ1Zahtev(dto);
        } catch (Exception e) {
            System.out.println(e.getClass());
            e.printStackTrace();
            return new ResponseEntity<>("nije uspelo", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("uspelo", HttpStatus.OK);
    }

    @PostMapping(value="/resi-z1", consumes="application/xml", produces="application/xml")
    public ResponseEntity<String> postZ1resenje(@RequestBody Z1Resenje resenje) {
        try {
            z1Service.createZ1Resenje(resenje);
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
            if (null == dto.getSearchParam() || dto.getSearchParam().isBlank() || null == dto.getTipMetapodatka()) {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
            }
            return new ResponseEntity<>(sparqlService.search(dto.getSearchParam(), dto.getTipMetapodatka()), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="text-search/{searchParam}", produces="application/xml")
    public ResponseEntity<List<String>> advancedSearchQuery(@PathVariable String searchParam) {
        return new ResponseEntity<>(z1Service.conductTextBasedSearch(searchParam), HttpStatus.OK);
    }
}
