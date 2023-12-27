package com.saar.eztasker.repo;

import com.saar.eztasker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Existing methods
    Optional<User> findByUsername(String username);
    void deleteById(long id);
    boolean existsById(long id);
    boolean existsByUsername(String username);
}
