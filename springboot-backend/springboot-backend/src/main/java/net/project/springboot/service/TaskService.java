package net.project.springboot.service;

import net.project.springboot.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    String addTask(Task task);
    List<Task> getAllTasks();
    String updateTask(Task taskUpdateDTO);
    String deleteTask(int id);
}
