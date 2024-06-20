package net.project.springboot.controller;

import net.project.springboot.entity.Task;
import net.project.springboot.service.TaskService;
import net.project.springboot.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/task")
@CrossOrigin
public class TaskController {
    @Autowired
    private TaskService taskService;
    @PostMapping(path = "/save")
    public String saveTask(@RequestBody Task task)
    {
        if (task.getTaskTitle() == null || task.getTaskTitle().isEmpty()) {
            return String.valueOf(ResponseEntity.badRequest().body("Task title cannot be empty"));
        }
        if (task.getTaskDescription() == null || task.getTaskDescription().isEmpty()) {
            return String.valueOf(ResponseEntity.badRequest().body("Task title cannot be empty"));
        }
        if (task.getDueDate() == null ) {
            return String.valueOf(ResponseEntity.badRequest().body("Task title cannot be empty"));
        }

        String id = taskService.addTask(task);
        return ResponseEntity.ok("Task saved successfully").toString();
    }

    @GetMapping(path = "/getAllTasks")
    public List<Task> getAllTasks() {
        List<Task> taskList = taskService.getAllTasks();
        return taskList;
    }

    @GetMapping(path = "/test")
    public String getString() {
        return "raji";
    }

    @PutMapping(path = "/updateTask")

    public String updateTask(@RequestBody Task taskUpdateDTO) {
        return taskService.updateTask(taskUpdateDTO);
    }

    @DeleteMapping(path = "/deleteTask/{id}")
    public String deleteTask(@PathVariable(value = "id") int id) {
       return taskService.deleteTask(id);
    }
}

	
