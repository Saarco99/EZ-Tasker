package com.saar.eztasker.controller;
import com.saar.eztasker.model.Task;
import com.saar.eztasker.srv.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
        private final TaskService taskService;

        @Autowired
        public TaskController(TaskService taskService) {
            this.taskService = taskService;
        }

        // Controller methods

        @GetMapping
        public List<Task> getAllTasks() {
            return taskService.getAllTasks();
        }

        @GetMapping("/{taskId}")
        public Task getTaskById(@PathVariable Long taskId) {
            return taskService.getTaskById(taskId);
        }

        @PostMapping
        public Task createTask(@RequestBody Task task) {
            return taskService.createTask(task);
        }

        @PutMapping("/{taskId}")
        public Task updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
            return taskService.updateTask(taskId, updatedTask);
        }

        @DeleteMapping("/{taskId}")
        public void deleteTask(@PathVariable Long taskId) {
            taskService.deleteTask(taskId);
        }
}