package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bartekbak.lawyer.dto.PaymentDTO;
import pl.bartekbak.lawyer.generate.jooq.tables.records.DbPaymentRecord;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    private UUID paymentId;

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
    
    public static Payment fromDbRecord(DbPaymentRecord record) {
        return Payment.builder()
                .paymentId(record.getPaymentId())
                .paymentValue(record.getPaymentValue())
                .paymentDate(record.getPaymentDate())
                .paid(record.getPaid())
                .paidDate(record.getPaidDate())
                .comment(record.getComment())
                .us(record.getUs())
                .incoming(record.getIncoming())
                .build();
    }
}
