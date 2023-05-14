package xml.entryService.main.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xml.entryService.main.model.User;
import xml.entryService.main.service.UserService;

@RestController
public class EntryController {

    @Autowired
    private UserService userService;

    @PostMapping(path="/login", consumes = "application/xml",produces = "application/xml")
    public ResponseEntity<User> login(@RequestBody String email, @RequestBody String password) {
        return ResponseEntity.ok(userService.Authenticate(email, password));
    }

    @PostMapping(path="/register", consumes = "application/xml",produces = "application/xml")
    public ResponseEntity<User> register(@RequestBody String email, @RequestBody String password, @RequestBody String name, @RequestBody String surname, @RequestBody String role) {
        return ResponseEntity.ok(userService.Register(email, password, name, surname, role));
    }
}
