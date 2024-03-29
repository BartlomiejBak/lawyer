package pl.bartekbak.lawyer.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.bartekbak.lawyer.dto.TaskDTO;
import pl.bartekbak.lawyer.service.TaskService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private static final String TASK_ADD_FORM = "tasks/add-task-form";

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/list")
    public String listAllTasks(Model model) {
        List<TaskDTO> taskList = taskService.findAllTasks();
        model.addAttribute("tasks", taskList);
        return "tasks/list-tasks";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        TaskDTO task = new TaskDTO();
        model.addAttribute("task", task);
        return TASK_ADD_FORM;
    }

    @GetMapping("/{taskId}/edit")
    public String showFormForUpdate(@PathVariable UUID taskId, Model model) {
        TaskDTO task = taskService.findTaskById(taskId);
        model.addAttribute("task", task);
        return TASK_ADD_FORM;
    }

    @PostMapping("/save")
    public String saveTask(@Valid @ModelAttribute("task") TaskDTO task, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError ->
                log.debug(objectError.toString()));
            return TASK_ADD_FORM;
        }
        taskService.saveTask(task);
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("taskId") UUID id) {
        taskService.deleteTaskById(id);
        return "redirect:list";
    }

    @GetMapping("/{taskId}")
    public ModelAndView showTask(@PathVariable UUID taskId) {
        ModelAndView mav = new ModelAndView("tasks/task-details");
        mav.addObject(taskService.findTaskById(taskId));
        return mav;
    }

}
