package pl.bartekbak.lawyer.controller.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bartekbak.lawyer.dto.PaymentDTO;
import pl.bartekbak.lawyer.exceptions.ResourceNotFoundException;
import pl.bartekbak.lawyer.service.spring.data.PaymentServiceSpringData;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentRestController {

    private final PaymentServiceSpringData service;

    public PaymentRestController(PaymentServiceSpringData service) {
        this.service = service;
    }

    @GetMapping
    public List<PaymentDTO> getAllPayments() {
        return service.findAllPayments();
    }

    @GetMapping("/{paymentId}")
    public PaymentDTO getPayment(@PathVariable int paymentId) {
        PaymentDTO payment = service.findPaymentById(paymentId);
        if (payment == null) {
            throw new ResourceNotFoundException("No such Id in database");
        }
        return payment;
    }

    @PostMapping
    public PaymentDTO addPayment(@RequestBody PaymentDTO payment) {
        payment.setPaymentId(0);
        service.savePayment(payment);
        return payment;
    }

    @PutMapping
    public PaymentDTO updatePayment(@RequestBody PaymentDTO payment) {
        service.savePayment(payment);
        return payment;
    }

    @DeleteMapping("/{paymentId}")
    public String deletePayment(@PathVariable int paymentId) {
        PaymentDTO payment = service.findPaymentById(paymentId);
        if (payment == null) {
            throw new ResourceNotFoundException("No such Id in database");
        }
        service.deletePaymentById(paymentId);
        return "Payment successfully deleted";
    }
}
