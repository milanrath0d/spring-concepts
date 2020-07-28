package org.milan.repository;

import org.milan.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User repository
 *
 * @author Milan Rathod
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
