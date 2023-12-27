package com.saar.eztasker.model;

import com.saar.eztasker.constant.Status;

import jakarta.persistence.*;
import jakarta.servlet.ServletContext;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private User user;

    private String title;

    @Lob
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDate dueDate;

    private Timestamp createdAt;

    // Constructors, getters, and setters

    public Task() {
    }
    public Task(TaskRequest taskRequest, User user) {
        this.user =  user;
        this.title = taskRequest.getTaskName();
        this.description = taskRequest.getTaskDescription();
        this.status = Status.Pending;
        this.dueDate = taskRequest.getDueDate();
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public Task(User username, String title, String description, Status status, LocalDate dueDate, Timestamp createdAt) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
    }

    // Id
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }


    // UserId
    // Title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Status
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // Due Date
    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    // Created At
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUser(User user) {

    }
}
