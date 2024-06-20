package net.project.springboot.service;
import net.project.springboot.entity.Task;
import net.project.springboot.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.lang.*;

@Service("taskServiceImpl")
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public String addTask(Task task) {
        taskRepository.save(task);
        return task.getTaskTitle();
    }
    @Override
    public List<Task> getAllTasks() {
        List<Task> getAllTasks = taskRepository.findAll();
        return getAllTasks;
    }

    @Override
    public String updateTask(Task taskUpdateDTO) {
        if (taskRepository.existsById(taskUpdateDTO.getTaskId())){
            Task task = taskRepository.findById(taskUpdateDTO.getTaskId()).orElse(null);
            if (task != null) {
                task.setTaskTitle(taskUpdateDTO.getTaskTitle());
                task.setTaskDescription(taskUpdateDTO.getTaskDescription());
                task.setDueDate(taskUpdateDTO.getDueDate());
                taskRepository.save(task);
                return task.getTaskTitle();
            }
        } else {
            System.out.println("Task ID does not exist");
            return "Task ID does not exist";
        }
        return null;
    }

      @Override
      public String deleteTask(int id) {
    if(taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return "Task ID deleted";
        } else {
           return "Task ID does not exist";
        }
    }
}
