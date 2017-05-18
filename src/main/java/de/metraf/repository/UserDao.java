package de.metraf.repository;

import de.metraf.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by metraf on 18.05.17.
 */
public interface UserDao extends MongoRepository<User, Integer> {
    User findByName(String name);
}
