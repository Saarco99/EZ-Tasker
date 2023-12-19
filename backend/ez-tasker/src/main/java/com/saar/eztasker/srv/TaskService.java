package com.saar.eztasker.srv;
import com.saar.eztasker.model.Task;
import com.saar.eztasker.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
        private final TaskRepository taskRepository;

        @Autowired
        public TaskService(TaskRepository taskRepository) {
            this.taskRepository = taskRepository;
        }

        // Service methods

        public List<Task> getAllTasks() {
            return taskRepository.findAll();
        }

        public Task getTaskById(Long taskId) {
            return taskRepository.findById(taskId).orElse(null);
        }

        public Task createTask(Task task) {
            return taskRepository.save(task);
        }

        public Task updateTask(Long taskId, Task updatedTask) {
            Task existingTask = taskRepository.findById(taskId).orElse(null);

            if (existingTask != null) {
                existingTask.setTitle(updatedTask.getTitle());
                existingTask.setDescription(updatedTask.getDescription());
                existingTask.setCompleted(updatedTask.isCompleted());

                return taskRepository.save(existingTask);
            }

            return null;
        }

        public void deleteTask(Long taskId) {
            taskRepository.deleteById(taskId);
        }
}