package com.saar.eztasker.controller;

import com.saar.eztasker.repo.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.saar.eztasker.model.User;
import com.saar.eztasker.srv.UserService;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    //simple get check
    @GetMapping("/check")
    public ResponseEntity<String> check() {
        //write ok to page
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }//whats the enttity then? its /users/check

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody String userLoginRequest) {
        String username = userLoginRequest;

        User user = userRepository.findByUsername(username);

        if (user != null) {
            return new ResponseEntity<>("Exist", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("NotExist", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        // Validate user input
        if (user == null ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User registeredUser = userService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User existingUser = userService.getUserById(id);

        if (existingUser != null) {
            BeanUtils.copyProperties(user, existingUser, "id");
            User updatedUser = userService.updateUser(existingUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            // User not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
