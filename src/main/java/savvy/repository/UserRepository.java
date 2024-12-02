package savvy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import savvy.entities.Candidate;


public interface UserRepository extends MongoRepository<Candidate, String> {
}
