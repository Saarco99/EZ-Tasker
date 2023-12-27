package com.saar.eztasker.controller;
import com.saar.eztasker.model.Task;
import com.saar.eztasker.model.TaskRequest;
import com.saar.eztasker.srv.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/tasks")
public class TaskController {
    public TaskService getTaskService() {
        return taskService;
    }

    private final TaskService taskService;

        @Autowired
        public TaskController(TaskService taskService) {
            this.taskService = taskService;
        }

        // Controller methods

        @GetMapping("/all")
        public List<Task> getAllTasks() {
            return taskService.getAllTasks();
        }

        @GetMapping("/all/{userId}")
        public List<Task> getAllTasksByUserId(@PathVariable Long userId) {
            return taskService.getAllTasksByUserId(userId);
        }

        @GetMapping("/one/{taskId}")
        public Task getTaskById(@PathVariable Long taskId) {
            return taskService.getTaskById(taskId);
        }

        @PostMapping("/addtask")
        public Task createTask(@RequestBody TaskRequest taskRequest) {
            return taskService.createTask(taskRequest);
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
