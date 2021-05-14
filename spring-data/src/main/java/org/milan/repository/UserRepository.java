package org.milan.repository;

import org.milan.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for User entity
 *
 * @author Milan Rathod
 */
@Repository
public interface UserRepository extends MongoRepository<User, Integer> {

    List<User> findAllByNameContainingOrTeamNameContaining(String name, String teamName);

}
