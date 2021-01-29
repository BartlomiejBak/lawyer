package pl.bartekbak.lawyer.service.spring.data;

import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dao.PaymentRepository;
import pl.bartekbak.lawyer.entity.Payment;
import pl.bartekbak.lawyer.service.PaymentService;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceSpringData implements PaymentService {

    PaymentRepository repository;

    public PaymentServiceSpringData(PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Payment> findAllPayments() {
        return repository.findAllByOrderByPaymentDateAsc();
    }

    @Override
    public Payment findPaymentById(int id) {
        Optional<Payment> result = repository.findById(id);
        Payment payment = null;
        if (result.isPresent()) {
            payment = result.get();
        } else {
            throw new RuntimeException("Id not found");
        }
        return payment;
    }

    @Override
    public void savePayment(Payment payment) {
        repository.save(payment);
    }

    @Override
    public void deletePaymentById(int id) {
        repository.deleteById(id);
    }
}
