package xml.entryService.main.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xml.entryService.main.dto.LoginDto;
import xml.entryService.main.model.User;
import xml.entryService.main.service.UserService;

@RestController
@RequestMapping(value="/entry")
public class EntryController {

    @Autowired
    private UserService userService;

    @PostMapping(path="/login", consumes = "application/xml",produces = "application/xml")
    public ResponseEntity<User> login(@RequestBody LoginDto dto) {
        return ResponseEntity.ok(userService.Authenticate(dto.email, dto.password));
    }

    @PostMapping(path="/register", consumes = "application/xml",produces = "application/xml")
    public ResponseEntity<User> register(@RequestBody User u) {
        return ResponseEntity.ok(userService.Register(u.getEmail(), u.getPassword(), u.getName(), u.getSurname(), "USER"));
    }
}
