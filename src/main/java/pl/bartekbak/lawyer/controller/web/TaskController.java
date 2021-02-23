package pl.bartekbak.lawyer.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.bartekbak.lawyer.entity.Task;
import pl.bartekbak.lawyer.service.TaskService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private static final String TASK_ADD_FORM = "tasks/add-task-form";

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/list")
    public String listAllTasks(Model model) {
        List<Task> taskList = taskService.findAllTasks();
        model.addAttribute("tasks", taskList);
        return "tasks/list-tasks";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Task task = new Task();
        model.addAttribute("task", task);
        return TASK_ADD_FORM;
    }

    @GetMapping("/{taskId}/edit")
    public String showFormForUpdate(@PathVariable int taskId, Model model) {
        Task task = taskService.findTaskById(taskId);
        model.addAttribute(task);
        return TASK_ADD_FORM;
    }

    @PostMapping("/save")
    public String saveTask(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError ->
                log.debug(objectError.toString()));
            return TASK_ADD_FORM;
        }
        taskService.saveTask(task);
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("taskId") int id) {
        taskService.deleteTaskById(id);
        return "redirect:list";
    }

    @GetMapping("/{taskId}")
    public ModelAndView showTask(@PathVariable int taskId) {
        ModelAndView mav = new ModelAndView("tasks/task-details");
        mav.addObject(taskService.findTaskById(taskId));
        return mav;
    }

}
