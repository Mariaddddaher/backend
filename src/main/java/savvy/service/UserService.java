package savvy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import savvy.entities.Candidate;
import savvy.repository.UserInterface;

@Service
public class UserService {

    @Autowired
    UserInterface userInterface;

    public void saveCandidate(Candidate candidate){
       userInterface.save(candidate);
    }
}
