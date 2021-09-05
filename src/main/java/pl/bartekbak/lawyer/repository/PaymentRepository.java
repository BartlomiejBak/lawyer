package pl.bartekbak.lawyer.repository;

import pl.bartekbak.lawyer.entity.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository {

    List<Payment> list();
    Optional<Payment> paymentById(int id);
    void add(Payment payment);
    void deleteById(int id);
}
