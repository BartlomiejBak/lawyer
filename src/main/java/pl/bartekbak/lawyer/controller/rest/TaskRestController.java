package pl.bartekbak.lawyer.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bartekbak.lawyer.entity.Task;
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
    public List<Task> getAllTasks() {
        return service.findAllTasks();
    }

    @GetMapping("/id/{taskId}")
    public Task getTask(@PathVariable int taskId) {
        Task task = service.findTaskById(taskId);
        if (task == null) {
            throw new RuntimeException("No such Id in database");
        }
        return task;
    }

    @PostMapping("/register")
    public Task addTask(@RequestBody Task task) {
        task.setTaskId(0);
        service.saveTask(task);
        return task;
    }

    @PutMapping("/register")
    public Task updateTask(@RequestBody Task task) {
        service.saveTask(task);
        return task;
    }

    @DeleteMapping("/remove/{taskId}")
    public String deleteTask(@PathVariable int taskId) {
        Task task = service.findTaskById(taskId);
        if (task == null) {
            throw new RuntimeException("No such Id in database");
        }
        service.deleteTaskById(taskId);
        return "Task successfully deleted";
    }
}
