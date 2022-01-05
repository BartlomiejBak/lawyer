package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.dto.PaymentDTO;

import java.util.List;
import java.util.UUID;

public interface PaymentService {
    List<PaymentDTO> findAllPayments();

    PaymentDTO findPaymentById(UUID id);

    void savePayment(PaymentDTO payment);

    void deletePaymentById(UUID id);
}
