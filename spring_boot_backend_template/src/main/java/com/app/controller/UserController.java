package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.UserRepository;
import com.app.exception.UserNotFoundException;
import com.app.pojos.User;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userrepo;

    @PostMapping("/User")
    public User newUser(@RequestBody User user) {
        return userrepo.save(user);
    }

    @GetMapping("/Users")
    public List<User> getAllUsers() {
        return userrepo.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        return userrepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable Long id) {
        if (!userrepo.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userrepo.deleteById(id);
        return "User with id " + id + " has been deleted successfully";
    }

    @PutMapping("/user/{id}")
    public User updateUser(@RequestBody User updatedUser, @PathVariable Long id) {
        return userrepo.findById(id)
                .map(user -> {
                    user.setName(updatedUser.getName());
                    user.setEmail(updatedUser.getEmail());
                    user.setUsername(updatedUser.getUsername());
                    return userrepo.save(user);
                })
                .orElseThrow(() -> new UserNotFoundException(id));
    }

}
