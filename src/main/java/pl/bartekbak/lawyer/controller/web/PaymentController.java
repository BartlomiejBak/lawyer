package pl.bartekbak.lawyer.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.bartekbak.lawyer.dto.PaymentDTO;
import pl.bartekbak.lawyer.service.PaymentService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

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
        List<PaymentDTO> paymentList = paymentService.findAllPayments();
        model.addAttribute("payments", paymentList);
        return "payments/list-payments";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        PaymentDTO payment = new PaymentDTO();
        model.addAttribute("payment", payment);
        return PAYMENT_ADD_FORM;
    }

    @GetMapping("/{paymentId}/edit")
    public String showFormForUpdate(@PathVariable UUID paymentId, Model model) {
        PaymentDTO payment = paymentService.findPaymentById(paymentId);
        model.addAttribute("payment", payment);
        return PAYMENT_ADD_FORM;
    }

    @PostMapping("/save")
    public String savePayment(@Valid @ModelAttribute("payment") PaymentDTO payment, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError ->
                log.debug(objectError.toString()));
            return PAYMENT_ADD_FORM;
        }
        paymentService.savePayment(payment);
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("paymentId") UUID id) {
        paymentService.deletePaymentById(id);
        return "redirect:list";
    }

    @GetMapping("/{paymentId}")
    public ModelAndView showPayment(@PathVariable UUID paymentId) {
        ModelAndView mav = new ModelAndView("payments/payment-details");
        mav.addObject(paymentService.findPaymentById(paymentId));
        return mav;
    }
}
