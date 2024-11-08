package savvy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import savvy.entities.Candidate;
import savvy.service.UserService;

@RestController
public class ExampleController {

    @Autowired
    UserService userService;

    @PostMapping("/add-candidate")
    public void addCandidate(@RequestBody Candidate candidate) {
        userService.saveCandidate(candidate);

    }
}
