package xml.p1.P1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xmldb.api.base.XMLDBException;
import xml.p1.P1.dto.P1DTO;
import xml.p1.P1.model.P1Zahtev;
import xml.p1.P1.service.P1Service;

import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


@RestController
@RequestMapping(value = "/")
public class P1Controller {

    @Autowired
    P1Service p1Service;

    @PostMapping(value="/post-p1", consumes="application/xml", produces="application/xml")
    public ResponseEntity<String> getCSRRequests(@RequestBody P1DTO dto) {
        try {
            p1Service.createP1Zahtev(dto);
        } catch (Exception e) {
            System.out.println(e.getClass());
            e.printStackTrace();
            return new ResponseEntity<>("nije uspelo", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("uspelo", HttpStatus.OK);
    }

    @PostMapping(value="/search", consumes="application/xml", produces="application/xml")
    public ResponseEntity<P1Zahtev> searchQuery() {
        return null;
    }
}
