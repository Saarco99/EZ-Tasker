package com.saar.eztasker.model;

import java.util.HashMap;

public class User {
    private long id;
    private String username;
    HashMap<Long, Task> tasks;

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

    public HashMap<Long, Task> getTasks() {
        return tasks;
    }

    public void setTasks(HashMap<Long, Task> tasks) {
        this.tasks = tasks;
    }

    public User(long id, String username) {
        this.id = id;
        this.username = username;
        this.tasks = new HashMap<>();
    }


}
