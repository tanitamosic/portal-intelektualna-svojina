package xml.entryService.main.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import xml.entryService.main.service.UserService;

@RestController
public class EntryController {

    @Autowired
    private UserService userService;
}
