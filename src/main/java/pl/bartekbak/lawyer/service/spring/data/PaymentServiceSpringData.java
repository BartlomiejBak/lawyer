package pl.bartekbak.lawyer.service.spring.data;

import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dao.PaymentRepository;
import pl.bartekbak.lawyer.dto.PaymentDTO;
import pl.bartekbak.lawyer.entity.Payment;
import pl.bartekbak.lawyer.service.PaymentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentServiceSpringData implements PaymentService {

    PaymentRepository repository;

    public PaymentServiceSpringData(PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PaymentDTO> findAllPayments() {
        return repository.findAllByOrderByPaymentDateAsc()
                .stream()
                .map(Payment::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentDTO findPaymentById(int id) {
        Optional<Payment> result = repository.findById(id);
        PaymentDTO payment;
        if (result.isPresent()) {
            payment = result.get().toDto();
        } else {
            throw new RuntimeException("Id not found");
        }
        return payment;
    }

    @Override
    public void savePayment(PaymentDTO payment) {
        repository.save(Payment.fromDto(payment));
    }

    @Override
    public void deletePaymentById(int id) {
        repository.deleteById(id);
    }
}
