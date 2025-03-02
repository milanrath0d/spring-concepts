package org.milan.repository;

import org.milan.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Repository for user
 *
 * @author Milan Rathod
 */
public interface UserRepository extends MongoRepository<User, Integer> {

    Optional<User> findByUserName(String userName);
}
