package org.milan.controller;

import org.milan.model.User;
import org.milan.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for user
 *
 * @author Milan Rathod
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

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
}
