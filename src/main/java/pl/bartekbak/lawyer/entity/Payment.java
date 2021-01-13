package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private int id;
    @Column(name = "payment_value")
    private double paymentValue;
    @Column(name = "payment_date")
    private LocalDate paymentDate;
    /*private boolean paid = false;
    private LocalDate paidDate;
    private String comment;
    private boolean us;
    private boolean incoming;*/

}
