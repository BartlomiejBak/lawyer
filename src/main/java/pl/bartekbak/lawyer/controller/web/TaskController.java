package pl.bartekbak.lawyer.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.bartekbak.lawyer.entity.Task;
import pl.bartekbak.lawyer.service.TaskService;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String listAllTasks(Model model) {
        List<Task> taskList = service.findAllTasks();
        model.addAttribute("task", taskList);
        return "tasks/list-tasks";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Task task = new Task();
        model.addAttribute("tasks", task);
        return "tasks/add-task-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("taskId") int id, Model model) {
        Task task = service.findTaskById(id);
        model.addAttribute(task);
        return "tasks/add-task-form";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute("task") Task task) {
        service.saveTask(task);

        //redirect prevents multiple saving
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("taskId") int id) {
        service.deleteTaskById(id);
        return "redirect:list";
    }

}
