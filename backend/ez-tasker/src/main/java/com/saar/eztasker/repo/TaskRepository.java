package com.saar.eztasker.repo;

import com.saar.eztasker.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Existing methods
    Task findById(long id);
    void deleteById(long id);
    boolean existsById(long id);
    boolean existsByUserId(long userId);
    void deleteByUserId(long userId);
    Task findByUserId(long userId);
    Task save(Task task);
    void delete(Task task);
    List<Task> findByTitle(String title);
    List<Task> findByDescriptionContaining(String keyword);
    List<Task> findByCompletedTrue();
    List<Task> findByCreatedAtAfter(Time specificTime);
}
