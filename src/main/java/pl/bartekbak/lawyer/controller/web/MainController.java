package pl.bartekbak.lawyer.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bartekbak.lawyer.dto.LawsuitDTO;
import pl.bartekbak.lawyer.dto.PaymentDTO;
import pl.bartekbak.lawyer.dto.TaskDTO;
import pl.bartekbak.lawyer.service.LawsuitService;
import pl.bartekbak.lawyer.service.PaymentService;
import pl.bartekbak.lawyer.service.TaskService;

import java.util.List;

@Controller
@RequestMapping("/main")
public class MainController {

    private final TaskService taskService;
    private final LawsuitService lawsuitService;
    private final PaymentService paymentService;

    public MainController(TaskService taskService, LawsuitService lawsuitService, PaymentService paymentService) {
        this.taskService = taskService;
        this.lawsuitService = lawsuitService;
        this.paymentService = paymentService;
    }

    @GetMapping("/menu")
    public String showMenu(Model model) {
        List<TaskDTO> taskList = taskService.findAllTasks();
        List<LawsuitDTO> lawsuitList = lawsuitService.findAllLawsuits();
        List<PaymentDTO> paymentList = paymentService.findAllPayments();
        model.addAttribute("tasks", taskList);
        model.addAttribute("lawsuits", lawsuitList);
        model.addAttribute("payments", paymentList);
        return "menu";
    }
}
