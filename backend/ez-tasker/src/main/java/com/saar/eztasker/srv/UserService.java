package com.saar.eztasker.srv;
import com.saar.eztasker.model.User;
import com.saar.eztasker.model.UserRequest;
import com.saar.eztasker.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

        private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User registerUser(User user) {//auto casting to user by username from header

        return userRepository.save(user);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }


    // Service methods
}