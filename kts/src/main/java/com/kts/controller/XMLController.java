package com.kts.controller;

import com.kts.service.XMLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.Scanner;

@RestController
public class XMLController {

    @Autowired
    private XMLService xmlService;

    @GetMapping("/readXML")
    public String readXML() {
        String input_xml = "src/main/resources/data/xml/a-1.xml";
        //String input_xml = "src/main/resources/data/xml/contacts.xml";
        String output_rdf = "src/main/resources/data/rdf/metadata.rdf";

        try {
            //FileInputStream fileInputStream = new FileInputStream(input_xml);
            //String content = new Scanner(fileInputStream).useDelimiter("\\Z").next();
            //System.out.println(content);
            xmlService.storeXML(new FileInputStream(new File(input_xml)), new FileOutputStream(new File(output_rdf)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "OK";

    }

    @GetMapping("/print")
    public String print() throws FileNotFoundException {
        //read file content into a string
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/data/xml/a-1.xml");
        String content = new Scanner(fileInputStream).useDelimiter("\\Z").next();

        StreamSource source = new StreamSource(new StringReader(content));
        return content;

    }
}

