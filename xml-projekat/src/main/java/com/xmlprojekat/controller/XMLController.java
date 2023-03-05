package com.xmlprojekat.controller;

import com.xmlprojekat.dto.XMLDto;
import com.xmlprojekat.service.XMLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.Scanner;

public class XMLController {

    @Autowired
    private XMLService service;

    @GetMapping("/readXML")
    public String readXML() {
        String input_xml = "src/main/resources/generated_xml/a-1.xml";
//        String input_xml = "src/main/resources/data/xml/a-1.xml";
//        String input_xml = "src/main/resources/data/xml/contacts.xml";
        String output_rdf = "src/main/rdf/metadata.rdf";

        try {
            //FileInputStream fileInputStream = new FileInputStream(input_xml);
            //String content = new Scanner(fileInputStream).useDelimiter("\\Z").next();
            //System.out.println(content);
            return service.storeXML(new FileInputStream(new File(input_xml)), new FileOutputStream(new File(output_rdf))).toString();
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


    // TODO: hendlanje exceptiona
    @PostMapping()
    public ResponseEntity<XMLDto> getFinishedDocument(@RequestBody XMLDto dto) throws Exception{
        String response = service.applyZavod(dto);
        return new ResponseEntity<XMLDto>(new XMLDto(response), HttpStatus.OK);
    }
}
