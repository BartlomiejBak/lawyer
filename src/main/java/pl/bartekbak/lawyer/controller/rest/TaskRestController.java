package pl.bartekbak.lawyer.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bartekbak.lawyer.entity.Task;
import pl.bartekbak.lawyer.service.spring.data.TaskServiceSpringData;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskRestController {
    TaskServiceSpringData service;

    @Autowired
    public TaskRestController(TaskServiceSpringData service) {
        this.service = service;
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return service.findAllTasks();
    }

    @GetMapping("/tasks/{taskId}")
    public Task getTask(@PathVariable int taskId) {
        Task task = service.findTaskById(taskId);
        if (task == null) {
            throw new RuntimeException("No such Id in database");
        }
        return task;
    }

    @PostMapping("/tasks")
    public Task addTask(@RequestBody Task task) {
        task.setId(0);
        service.saveTask(task);
        return task;
    }

    @PutMapping("/tasks")
    public Task updateTask(@RequestBody Task task) {
        service.saveTask(task);
        return task;
    }

    @DeleteMapping("/tasks/{taskId}")
    public String deleteTask(@PathVariable int taskId) {
        Task task = service.findTaskById(taskId);
        if (task == null) {
            throw new RuntimeException("No such Id in database");
        }
        service.deleteTaskById(taskId);
        return "Task successfully deleted";
    }
}
