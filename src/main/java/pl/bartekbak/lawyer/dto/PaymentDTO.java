package pl.bartekbak.lawyer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class PaymentDTO {

    private UUID paymentId;

    @Positive
    private double paymentValue;

    @Future
    private LocalDate paymentDate;

    private boolean paid = false;

    @Past
    private LocalDate paidDate;

    @Size(max = 255)
    private String comment;

    private boolean us;

    private boolean incoming;

}
