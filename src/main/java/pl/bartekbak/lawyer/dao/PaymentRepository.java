package pl.bartekbak.lawyer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bartekbak.lawyer.entity.Payment;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    public List<Payment> findAllByOrderByPaymentDateAsc();
}
