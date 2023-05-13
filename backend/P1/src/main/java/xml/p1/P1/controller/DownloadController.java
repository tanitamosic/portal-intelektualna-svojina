package xml.p1.P1.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
@RequestMapping(value = "/download")
public class DownloadController {

    @GetMapping(value="rdf/{filename}", produces=MediaType.APPLICATION_XHTML_XML_VALUE)
    public ResponseEntity<InputStreamResource> downloadRDF(@PathVariable String filename) {
        try {
            return generateResponse("src/main/resources/data/rdf/", filename, ".rdf", MediaType.APPLICATION_XHTML_XML);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="json/{filename}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InputStreamResource> downloadJSON(@PathVariable String filename) {
        try {
            return generateResponse("src/main/resources/data/rdf/json/", filename, ".json", MediaType.APPLICATION_JSON);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="xml/{filename}", produces=MediaType.APPLICATION_XHTML_XML_VALUE)
    public ResponseEntity<InputStreamResource> downloadXML(@PathVariable String filename) {
        try {
            return generateResponse("src/main/resources/data/xml/", filename, ".xml", MediaType.APPLICATION_XHTML_XML);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="xhtml/{filename}", produces=MediaType.APPLICATION_XHTML_XML_VALUE)
    public ResponseEntity<InputStreamResource> downloadXHTML(@PathVariable String filename) {
        try {
            return generateResponse("src/main/resources/static/xhtml/", filename, ".xhtml", MediaType.APPLICATION_XHTML_XML);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="pdf/{filename}", produces=MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> downloadPDF(@PathVariable String filename) {
        try {
            return generateResponse("src/main/resources/static/pdf/", filename, ".pdf", MediaType.APPLICATION_PDF);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity<InputStreamResource> generateResponse(String pathToFile, String filename, String fileExtension, MediaType type) throws FileNotFoundException {
        File file = new File(pathToFile.concat(filename).concat(fileExtension));
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=".concat(filename).concat(fileExtension));

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(type)
                .body(resource);
    }
}
