package com.saar.eztasker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.HashMap;
import java.util.Map;

@Entity
public class User {
    @Id
    private long id;

    private String username;

    @OneToMany
    private Map<Long, Task> tasks;  // (task id, task)

    public User() {
        // Default constructor
        this.tasks = new HashMap<>();
    }

    public User(long id, String username) {
        this.id = id;
        this.username = username;
        this.tasks = new HashMap<>();
    }

    // Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<Long, Task> getTasks() {
        return tasks;
    }

    public void setTasks(Map<Long, Task> tasks) {
        this.tasks = tasks;
    }
}
