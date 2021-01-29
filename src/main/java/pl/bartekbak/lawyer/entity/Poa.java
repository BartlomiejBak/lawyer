package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private long id;
    @Column(name = "type")
    private String type;
    @Column(name = "payment")
    private String payment;
    @Column(name = "kpc")
    private boolean kpc;
    @Column(name = "termination")
    private boolean termination;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "notification_duty")
    private boolean terminationNotificationDuty;
    @Column(name = "duty_completed")
    private boolean terminationNotificationDutyCompleted;
}
