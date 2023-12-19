package com.saar.eztasker.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.saar.eztasker.model.User;
import com.saar.eztasker.srv.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user) {
        User existingUser = userService.getUserById(id);
        if (existingUser != null) {
            BeanUtils.copyProperties(user, existingUser, "id");
            return userService.updateUser(existingUser);
        } else {
            // handle the case where the user doesn't exist
        }
        return existingUser;
    }

}
