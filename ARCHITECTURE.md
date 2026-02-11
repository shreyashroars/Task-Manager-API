# Task Manager API - Architecture Diagram

```
┌─────────────────────────────────────────────────────────────────┐
│                         FRONTEND (Browser)                       │
│  ┌──────────────────────────────────────────────────────────┐   │
│  │  index.html (Bootstrap + Vanilla JavaScript)             │   │
│  │  - Task form                                              │   │
│  │  - Task list display                                      │   │
│  │  - Statistics dashboard                                   │   │
│  │  - Filter buttons                                         │   │
│  └──────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────┘
                              │
                              │ HTTP Requests (Fetch API)
                              │ GET, POST, PUT, DELETE
                              ▼
┌─────────────────────────────────────────────────────────────────┐
│                    SPRING BOOT APPLICATION                       │
│                                                                  │
│  ┌────────────────────────────────────────────────────────┐    │
│  │         CONTROLLER LAYER (@RestController)             │    │
│  │  ┌──────────────────────────────────────────────────┐  │    │
│  │  │         TaskController.java                      │  │    │
│  │  │                                                   │  │    │
│  │  │  @GetMapping("/api/tasks")                       │  │    │
│  │  │  @GetMapping("/api/tasks/{id}")                  │  │    │
│  │  │  @PostMapping("/api/tasks")                      │  │    │
│  │  │  @PutMapping("/api/tasks/{id}")                  │  │    │
│  │  │  @DeleteMapping("/api/tasks/{id}")               │  │    │
│  │  │  @GetMapping("/api/tasks/stats")                 │  │    │
│  │  │                                                   │  │    │
│  │  │  Handles: Request/Response, Validation           │  │    │
│  │  └──────────────────────────────────────────────────┘  │    │
│  └────────────────────────────────────────────────────────┘    │
│                              │                                   │
│                              │ Calls service methods             │
│                              ▼                                   │
│  ┌────────────────────────────────────────────────────────┐    │
│  │           SERVICE LAYER (@Service)                     │    │
│  │  ┌──────────────────────────────────────────────────┐  │    │
│  │  │         TaskService.java                         │  │    │
│  │  │                                                   │  │    │
│  │  │  + getAllTasks(status)                           │  │    │
│  │  │  + getTaskById(id)                               │  │    │
│  │  │  + createTask(task)                              │  │    │
│  │  │  + updateTask(id, task)                          │  │    │
│  │  │  + deleteTask(id)                                │  │    │
│  │  │  + countByStatus(status)                         │  │    │
│  │  │                                                   │  │    │
│  │  │  Business Logic & Data Management                │  │    │
│  │  └──────────────────────────────────────────────────┘  │    │
│  └────────────────────────────────────────────────────────┘    │
│                              │                                   │
│                              │ Uses model classes                │
│                              ▼                                   │
│  ┌────────────────────────────────────────────────────────┐    │
│  │           MODEL LAYER (Domain Objects)                 │    │
│  │  ┌──────────────────────────────────────────────────┐  │    │
│  │  │         Task.java                                │  │    │
│  │  │                                                   │  │    │
│  │  │  - Long id                                       │  │    │
│  │  │  - String title (@NotBlank, @Size)              │  │    │
│  │  │  - String description (@Size)                   │  │    │
│  │  │  - TaskStatus status (enum)                     │  │    │
│  │  │  - LocalDateTime createdAt                      │  │    │
│  │  │  - LocalDateTime updatedAt                      │  │    │
│  │  └──────────────────────────────────────────────────┘  │    │
│  │  ┌──────────────────────────────────────────────────┐  │    │
│  │  │         TaskStatus.java (Enum)                   │  │    │
│  │  │                                                   │  │    │
│  │  │  - TODO                                          │  │    │
│  │  │  - IN_PROGRESS                                   │  │    │
│  │  │  - DONE                                          │  │    │
│  │  └──────────────────────────────────────────────────┘  │    │
│  └────────────────────────────────────────────────────────┘    │
│                              │                                   │
│                              │ Stored in                         │
│                              ▼                                   │
│  ┌────────────────────────────────────────────────────────┐    │
│  │         DATA STORAGE (In-Memory)                       │    │
│  │  ┌──────────────────────────────────────────────────┐  │    │
│  │  │  List<Task> tasks = new ArrayList<>()           │  │    │
│  │  │  AtomicLong idCounter                            │  │    │
│  │  │                                                   │  │    │
│  │  │  (Will be replaced with database in Project 2)  │  │    │
│  │  └──────────────────────────────────────────────────┘  │    │
│  └────────────────────────────────────────────────────────┘    │
│                                                                  │
│  ┌────────────────────────────────────────────────────────┐    │
│  │      EXCEPTION HANDLING (@RestControllerAdvice)        │    │
│  │  ┌──────────────────────────────────────────────────┐  │    │
│  │  │  GlobalExceptionHandler.java                     │  │    │
│  │  │                                                   │  │    │
│  │  │  @ExceptionHandler(ResourceNotFoundException)   │  │    │
│  │  │  @ExceptionHandler(MethodArgumentNotValid...)    │  │    │
│  │  │  @ExceptionHandler(Exception)                    │  │    │
│  │  │                                                   │  │    │
│  │  │  Returns: ErrorResponse with proper HTTP codes   │  │    │
│  │  └──────────────────────────────────────────────────┘  │    │
│  └────────────────────────────────────────────────────────┘    │
│                                                                  │
└─────────────────────────────────────────────────────────────────┘


═══════════════════════════════════════════════════════════════════
                          REQUEST FLOW
═══════════════════════════════════════════════════════════════════

Example: Creating a new task

1. USER ACTION (Frontend)
   └─→ User fills form and clicks "Add Task"
   
2. HTTP REQUEST
   └─→ POST http://localhost:8080/api/tasks
       Content-Type: application/json
       Body: {"title": "Learn Spring Boot", "description": "...", "status": "TODO"}
   
3. CONTROLLER (@RestController)
   └─→ TaskController.createTask(@Valid @RequestBody Task task)
       ├─→ Validates input (@Valid triggers validation)
       └─→ Calls service layer
   
4. SERVICE (@Service)
   └─→ TaskService.createTask(task)
       ├─→ Generates ID (idCounter.getAndIncrement())
       ├─→ Sets timestamps (createdAt, updatedAt)
       ├─→ Sets default status if null
       └─→ Adds to ArrayList
   
5. RESPONSE
   └─→ Returns Task object
       └─→ Spring converts to JSON automatically
       └─→ HTTP 201 Created
   
6. FRONTEND UPDATE
   └─→ Receives response
       ├─→ Clears form
       ├─→ Reloads task list
       └─→ Updates statistics


═══════════════════════════════════════════════════════════════════
                       KEY SPRING BOOT CONCEPTS
═══════════════════════════════════════════════════════════════════

1. DEPENDENCY INJECTION
   ┌──────────────────────────────────────────────────────┐
   │  @RestController                                      │
   │  public class TaskController {                        │
   │      private final TaskService taskService;           │
   │                                                        │
   │      // Constructor injection (Spring injects here)   │
   │      public TaskController(TaskService taskService) { │
   │          this.taskService = taskService;              │
   │      }                                                 │
   │  }                                                     │
   └──────────────────────────────────────────────────────┘

2. AUTO-CONFIGURATION
   ┌──────────────────────────────────────────────────────┐
   │  @SpringBootApplication  ← Combines 3 annotations:    │
   │  - @Configuration        (Bean definitions)           │
   │  - @EnableAutoConfiguration (Auto-config magic)       │
   │  - @ComponentScan        (Finds @Controller, etc.)    │
   └──────────────────────────────────────────────────────┘

3. REQUEST MAPPING
   ┌──────────────────────────────────────────────────────┐
   │  @GetMapping("/api/tasks/{id}")                       │
   │  public ResponseEntity<Task> getTaskById(             │
   │      @PathVariable Long id) {                         │
   │      // id extracted from URL                         │
   │  }                                                     │
   └──────────────────────────────────────────────────────┘

4. VALIDATION
   ┌──────────────────────────────────────────────────────┐
   │  @PostMapping("/api/tasks")                           │
   │  public ResponseEntity<Task> createTask(              │
   │      @Valid @RequestBody Task task) {                 │
   │      // @Valid triggers validation                    │
   │      // Checks @NotBlank, @Size, etc.                 │
   │  }                                                     │
   └──────────────────────────────────────────────────────┘


═══════════════════════════════════════════════════════════════════
                         TECHNOLOGY STACK
═══════════════════════════════════════════════════════════════════

Backend:
  • Java 17
  • Spring Boot 3.2.2
  • Spring Web (REST API)
  • Spring Validation (Bean Validation)
  • Lombok (Boilerplate reduction)
  • Spring DevTools (Hot reload)

Frontend:
  • HTML5
  • CSS3 (with gradients)
  • Vanilla JavaScript (ES6+)
  • Bootstrap 5.3.2
  • Fetch API (AJAX)

Build Tool:
  • Maven 3.6+

Testing:
  • Postman (API testing)
  • Browser DevTools (Frontend debugging)


═══════════════════════════════════════════════════════════════════
                      FILE ORGANIZATION
═══════════════════════════════════════════════════════════════════

Layered Architecture Pattern:

controller/     → Handles HTTP requests/responses
                  (Presentation Layer)
                  
service/        → Contains business logic
                  (Business Layer)
                  
model/          → Domain objects/entities
                  (Data Layer)
                  
exception/      → Custom exceptions & handlers
                  (Cross-cutting concern)


═══════════════════════════════════════════════════════════════════
                    WHAT YOU'VE LEARNED
═══════════════════════════════════════════════════════════════════

✓ Spring Boot project structure
✓ Maven dependency management
✓ RESTful API design principles
✓ HTTP methods and status codes
✓ Dependency Injection (DI) and Inversion of Control (IoC)
✓ Layered architecture (Controller-Service-Model)
✓ Request/Response handling
✓ Bean Validation
✓ Global exception handling
✓ CORS configuration
✓ Lombok for reducing boilerplate
✓ Frontend-Backend integration
✓ API testing with Postman

═══════════════════════════════════════════════════════════════════
