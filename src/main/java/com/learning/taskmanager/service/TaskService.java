package com.learning.taskmanager.service;

import com.learning.taskmanager.exception.ResourceNotFoundException;
import com.learning.taskmanager.model.Task;
import com.learning.taskmanager.model.TaskStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class TaskService {
    
    // In-memory storage (will be replaced with database)
    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);
    
    /**
     * Get all tasks, optionally filtered by status
     */
    public List<Task> getAllTasks(TaskStatus status) {
        if (status == null) {
            return new ArrayList<>(tasks);
        }
        return tasks.stream()
                .filter(task -> task.getStatus() == status)
                .collect(Collectors.toList());
    }
    
    /**
     * Get a single task by ID
     */
    public Task getTaskById(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
    }
    
    /**
     * Create a new task
     */
    public Task createTask(Task task) {
        task.setId(idCounter.getAndIncrement());
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        
        if (task.getStatus() == null) {
            task.setStatus(TaskStatus.TODO);
        }
        
        tasks.add(task);
        return task;
    }
    
    /**
     * Update an existing task
     */
    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = getTaskById(id);
        
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setStatus(updatedTask.getStatus());
        existingTask.setUpdatedAt(LocalDateTime.now());
        
        return existingTask;
    }
    
    /**
     * Delete a task
     */
    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        tasks.remove(task);
    }
    
    /**
     * Get task count by status
     */
    public long countByStatus(TaskStatus status) {
        return tasks.stream()
                .filter(task -> task.getStatus() == status)
                .count();
    }
}
