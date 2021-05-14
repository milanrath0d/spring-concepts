package org.milan.repository;

import org.milan.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for {@link Post}
 *
 * @author Milan Rathod
 */
@Repository
public interface PostRepository extends MongoRepository<Post, Long> {
}