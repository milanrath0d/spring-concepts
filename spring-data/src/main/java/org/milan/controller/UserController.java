package org.milan.controller;

import org.milan.model.User;
import org.milan.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for user
 *
 * @author Milan Rathod
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public void create(@RequestBody User user) {
        userService.create(user);
    }

    @GetMapping("/get/all")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/get/{id}")
    public User get(@PathVariable int id) {
        return userService.get(id);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable int id) {
        userService.delete(id);
    }

    /**
     * Find list of users for which contains user or teamName fields
     *
     * @param name     name
     * @param teamName team name
     * @return list of users
     */
    @GetMapping("search")
    public List<User> findByNameOrTeamName(@RequestParam String name, @RequestParam String teamName) {
        return userService.findByNameOrTeamName(name, teamName);
    }
}
