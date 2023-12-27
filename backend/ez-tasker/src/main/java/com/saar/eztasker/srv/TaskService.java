package com.saar.eztasker.srv;

import com.saar.eztasker.constant.Status;
import com.saar.eztasker.model.Task;
import com.saar.eztasker.model.TaskRequest;
import com.saar.eztasker.model.User;
import com.saar.eztasker.repo.TaskRepository;
import com.saar.eztasker.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId).orElse(null);
    }
    public Task createTask(TaskRequest taskRequest) {
        User user = userRepository.findByUsername(taskRequest.getUsername()).get();
        // Handle the case where the user is not found
        if (user == null) {
            throw new RuntimeException("User not found with username: " + taskRequest.getUsername());
        }

        Task task =new Task(taskRequest, user);
        user.getTasks().add(task);
        userRepository.save(user);
        return taskRepository.save(task);
    }


    public Task updateTask(Long taskId, Task updatedTask) {
        Optional<Task> existingTaskOptional = taskRepository.findById(taskId);

        if (existingTaskOptional.isPresent()) {
            Task existingTask = existingTaskOptional.get();
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            return taskRepository.save(existingTask);
        }

        return null;
    }

    public Task saveTask(Long userId, Task task) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found"));

        task.setUser(user);
        user.getTasks().add(task);
        userRepository.save(user);
        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }



    public List<Task> getAllTasksByUserId(Long userId) {
        return taskRepository.findAllByUserId(userId);

    }
}
