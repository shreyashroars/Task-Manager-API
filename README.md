# Task Manager API - Spring Boot Project 1

A RESTful API for managing tasks with a simple web interface. This is the first project in the Spring Boot learning roadmap.

## 🎯 Learning Objectives

This project teaches you:
- ✅ Spring Boot project structure
- ✅ RESTful API design with `@RestController`
- ✅ Dependency Injection with `@Service`
- ✅ Request/Response handling (`@RequestBody`, `@PathVariable`, `@RequestParam`)
- ✅ Input validation with Jakarta Validation
- ✅ Global exception handling with `@RestControllerAdvice`
- ✅ In-memory data storage
- ✅ CORS configuration for frontend integration

## 🚀 Technologies Used

- **Java 17**
- **Spring Boot 3.2.2**
- **Maven**
- **Lombok** (reduce boilerplate)
- **Spring Boot DevTools** (hot reload)
- **Bootstrap 5** (frontend)

## 📁 Project Structure

```
task-manager-api/
├── src/
│   ├── main/
│   │   ├── java/com/learning/taskmanager/
│   │   │   ├── controller/
│   │   │   │   └── TaskController.java       # REST endpoints
│   │   │   ├── service/
│   │   │   │   └── TaskService.java          # Business logic
│   │   │   ├── model/
│   │   │   │   ├── Task.java                 # Task entity
│   │   │   │   └── TaskStatus.java           # Enum (TODO, IN_PROGRESS, DONE)
│   │   │   ├── exception/
│   │   │   │   ├── ResourceNotFoundException.java
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   └── ErrorResponse.java
│   │   │   └── TaskManagerApiApplication.java # Main class
│   │   └── resources/
│   │       ├── application.properties         # Configuration
│   │       └── static/
│   │           └── index.html                 # Frontend UI
│   └── test/
└── pom.xml                                    # Maven dependencies
```

## 🔧 Prerequisites

- Java 17 or higher
- Maven 3.6+
- Your favorite IDE (IntelliJ IDEA recommended)

## 🏃 How to Run

### 1. Navigate to project directory
```bash
cd c:\Projects\spring-boot-prj\task-manager-api
```

### 2. Build the project (downloads dependencies)
```bash
mvn clean install
```

### 3. Run the application
```bash
mvn spring-boot:run
```

The application will start on **http://localhost:8080**

### 4. Access the application
- **Frontend UI**: http://localhost:8080
- **API Base URL**: http://localhost:8080/api/tasks

## 📡 API Endpoints

### Get All Tasks
```http
GET /api/tasks
GET /api/tasks?status=TODO
```

### Get Task by ID
```http
GET /api/tasks/{id}
```

### Create Task
```http
POST /api/tasks
Content-Type: application/json

{
  "title": "Learn Spring Boot",
  "description": "Complete Project 1",
  "status": "TODO"
}
```

### Update Task
```http
PUT /api/tasks/{id}
Content-Type: application/json

{
  "title": "Learn Spring Boot",
  "description": "Complete Project 1",
  "status": "IN_PROGRESS"
}
```

### Delete Task
```http
DELETE /api/tasks/{id}
```

### Get Statistics
```http
GET /api/tasks/stats
```

## 🧪 Testing with Postman

1. Download [Postman](https://www.postman.com/downloads/)
2. Import the endpoints above
3. Start making requests!

### Example: Create a Task
```
POST http://localhost:8080/api/tasks
Headers: Content-Type: application/json
Body:
{
  "title": "My First Task",
  "description": "Testing the API",
  "status": "TODO"
}
```

## ✨ Features Implemented

- ✅ Create task with validation (title required, 3-100 chars)
- ✅ Get all tasks
- ✅ Get task by ID
- ✅ Update task
- ✅ Delete task
- ✅ Filter tasks by status (TODO, IN_PROGRESS, DONE)
- ✅ Task statistics endpoint
- ✅ Global exception handling
- ✅ Input validation with meaningful error messages
- ✅ CORS enabled for frontend
- ✅ Responsive web UI with Bootstrap

## 🎨 Frontend Features

- Modern gradient design
- Real-time statistics dashboard
- Add tasks with validation
- Filter by status
- Update task status with dropdown
- Delete tasks with confirmation
- Responsive layout

## 📚 Key Concepts Learned

### 1. **Dependency Injection**
```java
@RestController
public class TaskController {
    private final TaskService taskService;
    
    // Constructor injection (recommended)
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
}
```

### 2. **Request Mapping**
```java
@GetMapping("/api/tasks")           // GET request
@PostMapping("/api/tasks")          // POST request
@PutMapping("/api/tasks/{id}")      // PUT request
@DeleteMapping("/api/tasks/{id}")   // DELETE request
```

### 3. **Validation**
```java
@NotBlank(message = "Title is required")
@Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
private String title;
```

### 4. **Exception Handling**
```java
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        // Return proper error response
    }
}
```

## 🔄 Next Steps

After completing this project:

1. **Experiment**: Try adding new fields (priority, dueDate)
2. **Test**: Use Postman to test all endpoints
3. **Understand**: Review each file and understand what it does
4. **Document**: Add comments to explain complex parts
5. **Move to Project 2**: Add database integration with JPA

## 🐛 Troubleshooting

### Port 8080 already in use
```bash
# Change port in application.properties
server.port=8081
```

### Lombok not working
- Install Lombok plugin in your IDE
- Enable annotation processing in IDE settings

### Frontend can't connect to API
- Make sure Spring Boot is running
- Check browser console for errors
- Verify CORS is enabled in TaskController

## 📖 Resources

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Web Guide](https://spring.io/guides/gs/rest-service/)
- [Lombok Documentation](https://projectlombok.org/)

## 🎓 What You've Learned

Congratulations! You've built your first Spring Boot REST API. You now understand:
- How Spring Boot auto-configuration works
- RESTful API design principles
- Dependency injection and IoC
- Request/response handling
- Validation and exception handling
- Full-stack integration (backend + frontend)

**Time to move to Project 2 and add a real database! 🚀**
