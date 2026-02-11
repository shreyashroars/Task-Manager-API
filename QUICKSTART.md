# 🚀 Quick Start Guide - Task Manager API

## ✅ Project Created Successfully!

Your Spring Boot project structure is ready. Here's what we've built:

### 📦 What's Included

1. **Backend (Spring Boot)**
   - ✅ REST API with full CRUD operations
   - ✅ Task model with validation
   - ✅ Service layer for business logic
   - ✅ Global exception handling
   - ✅ In-memory storage (ArrayList)

2. **Frontend (HTML + JavaScript)**
   - ✅ Modern UI with Bootstrap 5
   - ✅ Task management interface
   - ✅ Statistics dashboard
   - ✅ Status filtering

## 🔧 Prerequisites to Install

Before running the project, you need:

### 1. **Java Development Kit (JDK) 17+**
   - Download: https://adoptium.net/
   - Install and verify: `java -version`

### 2. **Maven (Build Tool)**
   
   **Option A: Install Maven**
   - Download: https://maven.apache.org/download.cgi
   - Extract and add to PATH
   - Verify: `mvn -version`

   **Option B: Use IntelliJ IDEA (Recommended for Beginners)**
   - Download: https://www.jetbrains.com/idea/download/ (Community Edition is free)
   - IntelliJ has built-in Maven support - no separate installation needed!

### 3. **IDE (Choose One)**
   - **IntelliJ IDEA** (Recommended) - Best for Spring Boot
   - **VS Code** with Java extensions
   - **Eclipse** with Spring Tools

## 🏃 How to Run

### Method 1: Using IntelliJ IDEA (Easiest)

1. **Open IntelliJ IDEA**
2. Click **File → Open**
3. Navigate to `c:\Projects\spring-boot-prj\task-manager-api`
4. Select the folder and click **OK**
5. Wait for IntelliJ to import the project (it will download dependencies automatically)
6. Find `TaskManagerApiApplication.java` in the project explorer
7. Right-click on it and select **Run 'TaskManagerApiApplication'**
8. Wait for the application to start (you'll see "Started TaskManagerApiApplication" in console)
9. Open browser: **http://localhost:8080**

### Method 2: Using Command Line (After Installing Maven)

```bash
# Navigate to project
cd c:\Projects\spring-boot-prj\task-manager-api

# Build the project (first time only)
mvn clean install

# Run the application
mvn spring-boot:run
```

### Method 3: Using VS Code

1. Install Java Extension Pack
2. Open folder: `c:\Projects\spring-boot-prj\task-manager-api`
3. Press `F5` or click Run → Start Debugging
4. Select "Java" when prompted

## 🎯 Testing the Application

### 1. **Web Interface**
   - Open: http://localhost:8080
   - You should see the Task Manager UI
   - Try adding, updating, and deleting tasks

### 2. **API Testing with Browser**
   - Get all tasks: http://localhost:8080/api/tasks
   - Get stats: http://localhost:8080/api/tasks/stats

### 3. **API Testing with Postman** (Recommended)
   
   Download Postman: https://www.postman.com/downloads/
   
   **Test Endpoints:**
   
   ```
   # Create a task
   POST http://localhost:8080/api/tasks
   Headers: Content-Type: application/json
   Body:
   {
     "title": "Learn Spring Boot",
     "description": "Complete the task manager project",
     "status": "TODO"
   }
   
   # Get all tasks
   GET http://localhost:8080/api/tasks
   
   # Get task by ID
   GET http://localhost:8080/api/tasks/1
   
   # Update task
   PUT http://localhost:8080/api/tasks/1
   Headers: Content-Type: application/json
   Body:
   {
     "title": "Learn Spring Boot",
     "description": "Complete the task manager project",
     "status": "IN_PROGRESS"
   }
   
   # Delete task
   DELETE http://localhost:8080/api/tasks/1
   
   # Get statistics
   GET http://localhost:8080/api/tasks/stats
   ```

## 📚 Understanding the Code

### Key Files to Study (in order):

1. **TaskManagerApiApplication.java** - Entry point
2. **Task.java** - Data model
3. **TaskStatus.java** - Enum for status
4. **TaskService.java** - Business logic
5. **TaskController.java** - REST endpoints
6. **GlobalExceptionHandler.java** - Error handling

### Important Annotations to Learn:

- `@SpringBootApplication` - Marks the main class
- `@RestController` - Makes a class handle HTTP requests
- `@Service` - Marks a service class
- `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping` - HTTP methods
- `@RequestBody` - Maps JSON to Java object
- `@PathVariable` - Extracts value from URL
- `@RequestParam` - Extracts query parameter
- `@Valid` - Triggers validation
- `@RestControllerAdvice` - Global exception handler

## 🎓 Learning Tasks

After getting the app running, try these exercises:

### Beginner:
1. ✅ Add a new task via the UI
2. ✅ Test all CRUD operations with Postman
3. ✅ Try to create a task with title less than 3 characters (should fail)
4. ✅ Filter tasks by different statuses

### Intermediate:
1. 🔨 Add a new field `priority` (LOW, MEDIUM, HIGH) to Task
2. 🔨 Add a new endpoint to get tasks by priority
3. 🔨 Add a `dueDate` field
4. 🔨 Update the frontend to show the new fields

### Advanced:
1. 🚀 Add pagination (limit, offset)
2. 🚀 Add sorting (by date, title)
3. 🚀 Add search functionality (search by title/description)
4. 🚀 Add task assignment (assign to user)

## 🐛 Common Issues & Solutions

### Issue: Port 8080 already in use
**Solution:** Change port in `application.properties`
```properties
server.port=8081
```

### Issue: Lombok not working in IDE
**Solution:** 
- IntelliJ: Install Lombok plugin + Enable annotation processing
- VS Code: Install Lombok extension

### Issue: "Cannot resolve symbol" errors
**Solution:** 
- IntelliJ: File → Invalidate Caches → Restart
- Reimport Maven project

### Issue: Frontend can't connect to API
**Solution:**
- Make sure Spring Boot is running (check console)
- Check if you can access http://localhost:8080/api/tasks in browser
- Check browser console for CORS errors

## 📖 Next Steps

1. **Master this project first**
   - Understand every file
   - Test all endpoints
   - Try the learning tasks above

2. **Read about Spring Boot concepts**
   - Dependency Injection
   - IoC Container
   - Bean lifecycle
   - Auto-configuration

3. **Move to Project 2**
   - Add database (JPA + PostgreSQL/MySQL)
   - Learn about entities and relationships
   - Implement pagination and sorting

## 💡 Pro Tips

1. **Use DevTools** - Your app auto-reloads when you save files
2. **Check logs** - Console shows helpful error messages
3. **Use Postman Collections** - Save your API tests
4. **Read error messages** - They usually tell you exactly what's wrong
5. **Experiment** - Break things and fix them to learn

## 🎉 Congratulations!

You've created your first Spring Boot REST API! This is a solid foundation for learning more advanced concepts.

**Happy Coding! 🚀**

---

## 📞 Need Help?

- Spring Boot Docs: https://docs.spring.io/spring-boot/docs/current/reference/html/
- Baeldung Tutorials: https://www.baeldung.com/spring-boot
- Stack Overflow: Tag your questions with `spring-boot`
