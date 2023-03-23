package xml.z1.Z1.controller;

import org.springframework.web.bind.annotation.*;
import xml.z1.Z1.dto.XmlDto;
import xml.z1.Z1.service.Z1Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(value = "api/xml", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class Z1Controller implements XMLController {

    public Z1Controller(Z1Service service) {
        this.service = service;
    }
    public Z1Service service;


    // TODO: hendlanje exceptiona
    @PostMapping()
    public ResponseEntity<XmlDto> getFinishedDocument(@RequestBody XmlDto dto) throws Exception{
        String response = service.applyZavod(dto);
        return new ResponseEntity<XmlDto>(new XmlDto(response), HttpStatus.OK);
    }
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
