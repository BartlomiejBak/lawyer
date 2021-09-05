package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bartekbak.lawyer.dto.PaymentDTO;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    private int paymentId;

    private double paymentValue;

    private LocalDate paymentDate;

    private boolean paid = false;

    private LocalDate paidDate;

    private String comment;

    private boolean us;

    private boolean incoming;

    public PaymentDTO toDto() {
        return PaymentDTO.builder()
                .paymentId(paymentId)
                .paymentValue(paymentValue)
                .paymentDate(paymentDate)
                .paid(paid)
                .paidDate(paidDate)
                .comment(comment)
                .us(us)
                .incoming(incoming)
                .build();
    }

    public static Payment fromDto(PaymentDTO dto) {
        return Payment.builder()
                .paymentId(dto.getPaymentId())
                .paymentValue(dto.getPaymentValue())
                .paymentDate(dto.getPaymentDate())
                .paid(dto.isPaid())
                .paidDate(dto.getPaidDate())
                .comment(dto.getComment())
                .us(dto.isUs())
                .incoming(dto.isIncoming())
                .build();
    }
}
