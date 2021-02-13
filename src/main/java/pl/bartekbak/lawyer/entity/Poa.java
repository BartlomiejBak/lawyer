package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "poa")
public class Poa {

    @Id
    @Column(name = "poa_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int poaId;

    @Column(name = "type")
    @Size(max = 50)
    private String type;

    @Column(name = "payment")
    @Size(max = 150)
    private String payment;

    @Column(name = "kpc")
    private boolean kpc;

    @Column(name = "termination")
    private boolean termination;

    @Column(name = "start_date")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate startDate;

    @Column(name = "end_date")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate endDate;

    @Column(name = "notification_duty")
    private boolean terminationNotificationDuty;

    @Column(name = "duty_completed")
    private boolean terminationNotificationDutyCompleted;
}
