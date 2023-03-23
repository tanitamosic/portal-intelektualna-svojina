package xml.z1.Z1.controller;

import com.xmlprojekat.dto.XMLDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface XMLController {
    public ResponseEntity<XMLDto> getFinishedDocument(@RequestBody XMLDto dto) throws Exception;
}
