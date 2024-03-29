package pl.bartekbak.lawyer.service.jooq;

import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dto.PaymentDTO;
import pl.bartekbak.lawyer.entity.Payment;
import pl.bartekbak.lawyer.repository.PaymentRepository;
import pl.bartekbak.lawyer.service.PaymentService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaymentServiceJooq implements PaymentService {

    PaymentRepository repository;

    public PaymentServiceJooq(PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PaymentDTO> findAllPayments() {
        return repository.list()
                .stream()
                .map(Payment::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentDTO findPaymentById(UUID id) {
        Optional<Payment> result = repository.paymentById(id);
        PaymentDTO payment;
        payment = result.map(Payment::toDto).orElse(null);
        return payment;
    }

    @Override
    public void savePayment(PaymentDTO payment) {
        repository.add(Payment.fromDto(payment));
    }

    @Override
    public void deletePaymentById(UUID id) {
        repository.deleteById(id);
    }
}
