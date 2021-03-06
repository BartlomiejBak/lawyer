package pl.bartekbak.lawyer.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bartekbak.lawyer.dto.TaskDTO;
import pl.bartekbak.lawyer.exceptions.ResourceNotFoundException;
import pl.bartekbak.lawyer.service.spring.data.TaskServiceSpringData;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskRestController {
    TaskServiceSpringData service;

    @Autowired
    public TaskRestController(TaskServiceSpringData service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<TaskDTO> getAllTasks() {
        return service.findAllTasks();
    }

    @GetMapping("/id/{taskId}")
    public TaskDTO getTask(@PathVariable int taskId) {
        TaskDTO task = service.findTaskById(taskId);
        if (task == null) {
            throw new ResourceNotFoundException("No such Id in database");
        }
        return task;
    }

    @PostMapping("/register")
    public TaskDTO addTask(@RequestBody TaskDTO task) {
        task.setTaskId(0);
        service.saveTask(task);
        return task;
    }

    @PutMapping("/register")
    public TaskDTO updateTask(@RequestBody TaskDTO task) {
        service.saveTask(task);
        return task;
    }

    @DeleteMapping("/remove/{taskId}")
    public String deleteTask(@PathVariable int taskId) {
        TaskDTO task = service.findTaskById(taskId);
        if (task == null) {
            throw new ResourceNotFoundException("No such Id in database");
        }
        service.deleteTaskById(taskId);
        return "Task successfully deleted";
    }
}
