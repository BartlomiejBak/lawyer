package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
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
    @Future
    private LocalDate paymentDate;

    @Column(name = "paid")
    private boolean paid = false;

    @Column(name = "paid_date")
    @Past
    private LocalDate paidDate;

    @Column(name = "comment")
    @Size(max = 255)
    private String comment;

    @Column(name = "us")
    private boolean us;

    @Column(name = "incoming")
    private boolean incoming;

}
