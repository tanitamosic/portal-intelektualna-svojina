package xml.z1.Z1.controller;

import xml.z1.Z1.dto.XmlDto;
import xml.z1.Z1.service.Z1Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
