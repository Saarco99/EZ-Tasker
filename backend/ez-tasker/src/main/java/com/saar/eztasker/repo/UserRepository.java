package com.saar.eztasker.repo;
import com.saar.eztasker.model.Task;
import com.saar.eztasker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Task, Long> {
    // Additional query methods can be added here if needed
    User findByUsername(String username);

    User save(User user);

    User findById(long id);

    void deleteById(long id);

    void deleteByUsername(String username);

    void delete(User user);

    boolean existsByUsername(String username);

    boolean existsById(long id);




}
