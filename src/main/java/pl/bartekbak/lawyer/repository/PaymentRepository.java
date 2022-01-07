package pl.bartekbak.lawyer.repository;

import pl.bartekbak.lawyer.entity.Payment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository {

    List<Payment> list();
    Optional<Payment> paymentById(UUID id);
    void add(Payment payment);
    void update(Payment payment);
    void deleteById(UUID id);
}
