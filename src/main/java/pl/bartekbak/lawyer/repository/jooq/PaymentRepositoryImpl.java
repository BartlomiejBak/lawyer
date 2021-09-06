package pl.bartekbak.lawyer.repository.jooq;

import org.springframework.stereotype.Repository;
import pl.bartekbak.lawyer.entity.Payment;
import pl.bartekbak.lawyer.repository.PaymentRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    @Override
    public List<Payment> list() {
        return List.of();
    }

    @Override
    public Optional<Payment> paymentById(int id) {
        return Optional.empty();
    }

    @Override
    public void add(Payment payment) {

    }

    @Override
    public void deleteById(int id) {

    }
}
