package pl.bartekbak.lawyer.dto;

import javax.validation.constraints.Future;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class PaymentDTO {

    private int paymentId;

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
