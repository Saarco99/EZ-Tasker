package com.saar.eztasker.repo;

import com.saar.eztasker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Existing methods
    User findById(long id);
    void deleteById(long id);
    boolean existsById(long id);
    User findByUsername(String username);

    boolean existsByUsername(String username);
}
