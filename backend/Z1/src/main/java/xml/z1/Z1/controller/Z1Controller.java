package xml.z1.Z1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xml.z1.Z1.dto.SearchDTO;
import xml.z1.Z1.dto.Z1DTO;
import xml.z1.Z1.model.Z1Resenje;
import xml.z1.Z1.model.Z1Zahtev;
import xml.z1.Z1.service.Z1Service;
import xml.z1.Z1.service.SparqlService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(value = "/z1")
public class Z1Controller {

    @Autowired
    Z1Service z1Service;
    @Autowired
    SparqlService sparqlService;

    private static final String IMAGE_UPLOAD_DIR = "src/main/resources/static/img";
    private static final String FILE_UPLOAD_DIR = "src/main/resources/static/files";

    @PostMapping(value="/post-z1", consumes="application/xml", produces="application/xml")
    public ResponseEntity<List<String>> postZ1zahtev(@RequestBody Z1DTO dto) {
        try {
            List<String> retval = new ArrayList<>();
            retval.add(z1Service.createZ1Zahtev(dto));
            return ResponseEntity.ok().body(retval);
        } catch (Exception e) {
            System.out.println(e.getClass());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value="/resi", consumes="application/xml", produces="application/xml")
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

    @GetMapping(value="/resenje-postoji/{broj}", produces="application/xml")
    public ResponseEntity<Boolean> doesDecisionExist(@PathVariable String broj) {
        try {
            return new ResponseEntity<>(z1Service.doesDecisionExist(broj), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getClass());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
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

    @PostMapping("/upload-image/")
    public ResponseEntity<String> uploadImage(@RequestPart MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        String fileName = generateRandomFileName(file.getOriginalFilename());
        String filePath = IMAGE_UPLOAD_DIR + "/" + fileName;

        try {
            saveFile(file, filePath);
            return ResponseEntity.ok().body(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        }
    }

    @PostMapping(value = "/upload-file/{fileName}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestPart MultipartFile file, @PathVariable String fileName) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String filePath = fileName + "." + extension;

        try {
            saveFile(file, filePath);
            return ResponseEntity.ok().body(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }

    private String generateRandomFileName(String originalFileName) {
        String extension = StringUtils.getFilenameExtension(originalFileName);
        String randomName = UUID.randomUUID().toString();
        return randomName + "." + extension;
    }
    private void saveFile(MultipartFile file, String filePath) throws IOException {
        Path directory = Path.of(FILE_UPLOAD_DIR);
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }

        Path targetPath = directory.resolve(filePath);
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @GetMapping("/empty")
    public ResponseEntity<String> empty(){
        try {
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(value="all-requests", produces="application/xml")
    public ResponseEntity<List<String>> getAll() {
        try {
            return new ResponseEntity<>(z1Service.getAllRequests(), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
