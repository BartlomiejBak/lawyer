package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bartekbak.lawyer.dto.PaymentDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "payment")
public class Payment {

    @Id
    @Column(name = "payment_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int paymentId;

    @Column(name = "payment_value")
    private double paymentValue;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "paid")
    private boolean paid = false;

    @Column(name = "paid_date")
    private LocalDate paidDate;

    @Column(name = "comment")
    private String comment;

    @Column(name = "us")
    private boolean us;

    @Column(name = "incoming")
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
