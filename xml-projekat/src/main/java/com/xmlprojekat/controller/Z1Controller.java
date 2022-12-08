package com.xmlprojekat.controller;

import com.xmlprojekat.dto.XMLDto;
import com.xmlprojekat.service.Z1Service;
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
    public ResponseEntity<XMLDto> getFinishedDocument(@RequestBody XMLDto dto) throws Exception{
        String response = service.applyZavod(dto);
        return new ResponseEntity<XMLDto>(new XMLDto(response), HttpStatus.OK);
    }
}
