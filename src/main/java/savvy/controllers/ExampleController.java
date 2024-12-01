package savvy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import savvy.entities.Candidate;
import savvy.service.UserService;

import java.io.IOException;
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
    public Optional<Candidate> getCandidateById(@PathVariable String id) {
        return userService.getCandidateById(id);
    }

    @PutMapping("/update/{id}")
    public Candidate updateUser(@PathVariable String id, @RequestBody Candidate candidate) {
        return userService.updateCandidate(id, candidate);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCandidate(@PathVariable String id) {
        userService.deleteCandidate(id);
        return "User deleted with id: " + id;
    }

    @PostMapping("/{candidateId}/uploadPDF")
    public ResponseEntity<String> uploadPDF(@PathVariable String candidateId, @RequestParam("file") MultipartFile file) {
        try {
            userService.uploadPDFToCandidate(candidateId, file);
            return ResponseEntity.ok("PDF uploaded and attached to candidate successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload PDF.");
        }
    }

}

