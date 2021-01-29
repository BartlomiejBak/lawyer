package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.entity.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> findAllPayments();

    Payment findPaymentById(int id);

    void savePayment(Payment payment);

    void deletePaymentById(int id);
}
