package com.saar.eztasker.controller;

import com.saar.eztasker.model.UserRequest;
import com.saar.eztasker.repo.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.saar.eztasker.model.User;
import com.saar.eztasker.srv.UserService;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private UserService userService;
    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userService = new UserService(userRepository);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("id/{username}")
    public ResponseEntity<Long> getIdByUsername(@PathVariable String username) {
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get().getId(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    //simple get check
    @GetMapping("/check")
    public ResponseEntity<String> check() {

        //write ok to page
        return new ResponseEntity<>("ok", HttpStatus.OK);

    }//whats the enttity then? its /users/check

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody String userLoginRequest) {
        String username = userLoginRequest;

        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            return new ResponseEntity<>("Exist", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("NotExist", HttpStatus.OK);//create new user
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        // Validate user input
        if (user == null ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.OK);//user already exist,login instead
        }else {
            User newUser = userService.registerUser(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username).get();

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User existingUser = userService.getUserById(id).get();

        if (existingUser != null) {
            BeanUtils.copyProperties(user, existingUser);
            User updatedUser = userService.updateUser(existingUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            // User not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
