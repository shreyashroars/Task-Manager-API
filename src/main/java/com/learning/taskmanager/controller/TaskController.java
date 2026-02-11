package com.learning.taskmanager.controller;

import com.learning.taskmanager.model.Task;
import com.learning.taskmanager.model.TaskStatus;
import com.learning.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*") // Allow frontend to connect (for development)
public class TaskController {
    
    private final TaskService taskService;
    
    // Constructor injection (recommended over @Autowired)
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    
    /**
     * GET /api/tasks - Get all tasks (with optional status filter)
     * Example: GET /api/tasks?status=TODO
     */
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(
            @RequestParam(required = false) TaskStatus status) {
        List<Task> tasks = taskService.getAllTasks(status);
        return ResponseEntity.ok(tasks);
    }
    
    /**
     * GET /api/tasks/{id} - Get a single task by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }
    
    /**
     * POST /api/tasks - Create a new task
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }
    
    /**
     * PUT /api/tasks/{id} - Update an existing task
     */
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(id, task);
        return ResponseEntity.ok(updatedTask);
    }
    
    /**
     * DELETE /api/tasks/{id} - Delete a task
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Task deleted successfully");
        response.put("id", id.toString());
        return ResponseEntity.ok(response);
    }
    
    /**
     * GET /api/tasks/stats - Get task statistics
     */
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> getTaskStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("total", taskService.getAllTasks(null).size() * 1L);
        stats.put("todo", taskService.countByStatus(TaskStatus.TODO));
        stats.put("inProgress", taskService.countByStatus(TaskStatus.IN_PROGRESS));
        stats.put("done", taskService.countByStatus(TaskStatus.DONE));
        return ResponseEntity.ok(stats);
    }
}
