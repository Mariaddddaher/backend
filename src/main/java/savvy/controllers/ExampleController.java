package savvy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import savvy.entities.User;
import savvy.repository.UserInterface;

@RestController
public class ExampleController {

@Autowired
UserInterface userinter;

    @PostMapping ("/addUser")
    public void addUser(@RequestBody User user) {
        userinter.save(user);

    }
}
