package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {
    List<PaymentDTO> findAllPayments();

    PaymentDTO findPaymentById(int id);

    void savePayment(PaymentDTO payment);

    void deletePaymentById(int id);
}
