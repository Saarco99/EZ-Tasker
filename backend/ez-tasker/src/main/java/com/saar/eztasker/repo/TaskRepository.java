package com.saar.eztasker.repo;

import com.saar.eztasker.model.Task;
import com.saar.eztasker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findByUserId(long userId);
    List<Task> findByTitle(String title);
    List<Task> findByDescriptionContaining(String keyword);
    List<Task> findByCreatedAtAfter(Timestamp specificTime);
    List<Task> findAllByUserId(long userId);
}
