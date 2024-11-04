package savvy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import savvy.entities.User;

public interface UserInterface extends MongoRepository<User, Integer> {
}
