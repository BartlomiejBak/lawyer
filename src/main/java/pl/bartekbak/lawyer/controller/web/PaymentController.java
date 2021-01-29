package pl.bartekbak.lawyer.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.bartekbak.lawyer.entity.Payment;
import pl.bartekbak.lawyer.service.PaymentService;

import java.util.List;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String listAllPayments(Model model) {
        List<Payment> paymentList = service.findAllPayments();
        model.addAttribute("payments", paymentList);
        return "payments/list-payments";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Payment payment = new Payment();
        model.addAttribute("payment", payment);
        return "payments/add-note-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("paymentId") int id, Model model) {
        Payment payment = service.findPaymentById(id);
        model.addAttribute(payment);
        return "payments/update-payment-form";
    }

    @PostMapping("/save")
    public String savePayment(@ModelAttribute("payment") Payment payment) {
        service.savePayment(payment);
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("paymentId") int id) {
        service.deletePaymentById(id);
        return "redirect:list";
    }
}
