package savvy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import savvy.entities.Candidate;
import savvy.entities.Candidate;

public interface UserInterface extends MongoRepository<Candidate, Integer> {
}
