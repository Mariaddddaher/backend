package savvy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import savvy.entities.Candidate;
import savvy.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
public class ExampleController {

    @Autowired
    UserService userService;

    @PostMapping("/add-candidate")
    public void addCandidate(@RequestBody Candidate candidate) {
        userService.saveCandidate(candidate);

    }

    @GetMapping("/users")
    public List<Candidate> getAllUsers() {
        return userService.getAllCandidates();
    }

    @GetMapping("/{id}")
    public Optional<Candidate> getCandidateById(@PathVariable Integer id) {
        return userService.getCandidateById(id);
    }

    @PutMapping("/update/{id}")
    public Candidate updateUser(@PathVariable Integer id, @RequestBody Candidate candidate) {
        return userService.updateCandidate(id, candidate);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCandidate(@PathVariable Integer id) {
        userService.deleteCandidate(id);
        return "User deleted with id: " + id;
    }
}

