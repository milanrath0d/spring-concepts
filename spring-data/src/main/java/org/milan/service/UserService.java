package org.milan.service;

import org.milan.model.User;
import org.milan.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for user
 *
 * @author Milan Rathod
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(User user) {
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User get(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public List<User> findByNameOrTeamName(String name, String teamName) {
        return userRepository.findAllByNameContainingOrTeamNameContaining(name, teamName);
    }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
