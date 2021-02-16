package pl.bartekbak.lawyer.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.bartekbak.lawyer.entity.Payment;
import pl.bartekbak.lawyer.service.PaymentService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private static final String PAYMENT_ADD_FORM = "payments/add-payment-form";

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/list")
    public String listAllPayments(Model model) {
        List<Payment> paymentList = paymentService.findAllPayments();
        model.addAttribute("payments", paymentList);
        return "payments/list-payments";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Payment payment = new Payment();
        model.addAttribute("payment", payment);
        return PAYMENT_ADD_FORM;
    }

    @GetMapping("/{paymentId}/edit")
    public String showFormForUpdate(@PathVariable int paymentId, Model model) {
        Payment payment = paymentService.findPaymentById(paymentId);
        model.addAttribute(payment);
        return PAYMENT_ADD_FORM;
    }

    @PostMapping("/save")
    public String savePayment(@Valid @ModelAttribute("payment") Payment payment, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError ->
                log.debug(objectError.toString()));
            return PAYMENT_ADD_FORM;
        }
        paymentService.savePayment(payment);
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("paymentId") int id) {
        paymentService.deletePaymentById(id);
        return "redirect:list";
    }

    @GetMapping("/{paymentId}")
    public ModelAndView showPayment(@PathVariable int paymentId) {
        ModelAndView mav = new ModelAndView("payments/payment-details");
        mav.addObject(paymentService.findPaymentById(paymentId));
        return mav;
    }
}
