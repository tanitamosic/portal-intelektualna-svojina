package xml.z1.Z1.controller;

import xml.z1.Z1.dto.XmlDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface XMLController {
    public ResponseEntity<XmlDto> getFinishedDocument(@RequestBody XmlDto dto) throws Exception;
}
