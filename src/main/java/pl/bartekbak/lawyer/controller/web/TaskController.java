package pl.bartekbak.lawyer.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.bartekbak.lawyer.entity.Task;
import pl.bartekbak.lawyer.service.TaskService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
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
        model.addAttribute("task", task);
        return "tasks/add-task-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("taskId") int id, Model model) {
        Task task = service.findTaskById(id);
        model.addAttribute(task);
        return "tasks/add-task-form";
    }

    @PostMapping("/save")
    public String saveTask(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return "tasks/add-task-form";
        }
        service.saveTask(task);
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("taskId") int id) {
        service.deleteTaskById(id);
        return "redirect:list";
    }

}
