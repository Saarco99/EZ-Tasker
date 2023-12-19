package com.saar.eztasker.repo;
import com.saar.eztasker.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Additional query methods can be added here if needed
    Task findById(long id);

    Task save(Task task);

    void deleteById(long id);

    void delete(Task task);

    boolean existsById(long id);

    boolean existsByTitle(String title);

    boolean existsByDescription(String description);

    boolean existsByCompleted(boolean completed);

    boolean existsByUserId(long userId);

    boolean existsByUserUsername(String username);

    boolean existsByUserEmail(String email);

    boolean existsByUserPassword(String password);

    boolean existsByUserFirstName(String firstName);
}
