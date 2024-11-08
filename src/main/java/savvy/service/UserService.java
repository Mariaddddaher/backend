package savvy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import savvy.entities.Candidate;
import savvy.repository.UserInterface;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserInterface userInterface;

    public Candidate saveCandidate(Candidate candidate){
        return userInterface.save(candidate);
    }

    public List<Candidate> getAllCandidates() {
        return userInterface.findAll();
    }

    public Optional<Candidate> getCandidateById(Integer id) {
        return userInterface.findById(id);
    }

    public Candidate updateCandidate(Integer id, Candidate newCandidate) {
        return userInterface.findById(id).map(candidate -> {
            candidate.setName(newCandidate.getName());
            candidate.setAge(newCandidate.getAge());
            candidate.setAddress(newCandidate.getAddress());
            return userInterface.save(candidate);
        }).orElseThrow(() -> new RuntimeException("Candidate not found with id " + id));
    }

    public void deleteCandidate(Integer id) {
        userInterface.deleteById(id);
    }
    }

