package org.milan.controller;

import org.milan.model.User;
import org.milan.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for user
 *
 * @author Milan Rathod
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody User user) {
        userService.create(user);
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        return userService.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        userService.delete(id);
    }

    /**
     * Find list of users which contains name or teamName fields
     *
     * @param name     name
     * @param teamName team name
     * @return list of users
     */
    @GetMapping("search")
    public List<User> findByNameOrTeamName(@RequestParam String name, @RequestParam String teamName) {
        return userService.findByNameOrTeamName(name, teamName);
    }

    @GetMapping(value = "/paging")
    public Page<User> findAllUsingPagination(Pageable pageable) {
        return userService.findAll(pageable);
    }
}
