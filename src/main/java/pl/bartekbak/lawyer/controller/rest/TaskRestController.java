package pl.bartekbak.lawyer.controller.rest;

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
import pl.bartekbak.lawyer.service.jooq.TaskServiceJooq;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
public class TaskRestController {

    private final TaskServiceJooq service;

    public TaskRestController(TaskServiceJooq service) {
        this.service = service;
    }

    @GetMapping
    public List<TaskDTO> getAllTasks() {
        return service.findAllTasks();
    }

    @GetMapping("/{taskId}")
    public TaskDTO getTask(@PathVariable UUID taskId) {
        TaskDTO task = service.findTaskById(taskId);
        if (task == null) {
            throw new ResourceNotFoundException("No such Id in database");
        }
        return task;
    }

    @PostMapping
    public TaskDTO addTask(@RequestBody TaskDTO task) {
        task.setTaskId(UUID.randomUUID());
        service.saveTask(task);
        return task;
    }

    @PutMapping
    public TaskDTO updateTask(@RequestBody TaskDTO task) {
        service.saveTask(task);
        return task;
    }

    @DeleteMapping("/{taskId}")
    public String deleteTask(@PathVariable UUID taskId) {
        TaskDTO task = service.findTaskById(taskId);
        if (task == null) {
            throw new ResourceNotFoundException("No such Id in database");
        }
        service.deleteTaskById(taskId);
        return "Task successfully deleted";
    }
}
