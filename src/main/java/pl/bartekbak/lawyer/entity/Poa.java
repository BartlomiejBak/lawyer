package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bartekbak.lawyer.dto.PoaDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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

    public PoaDTO toDto() {
        return PoaDTO.builder()
                .poaId(poaId)
                .type(type)
                .payment(payment)
                .kpc(kpc)
                .termination(termination)
                .startDate(startDate)
                .endDate(endDate)
                .terminationNotificationDuty(terminationNotificationDuty)
                .terminationNotificationDutyCompleted(terminationNotificationDutyCompleted)
                .build();
    }

    public static Poa fromDto(PoaDTO dto) {
        return Poa.builder()
                .poaId(dto.getPoaId())
                .type(dto.getType())
                .payment(dto.getPayment())
                .kpc(dto.isKpc())
                .termination(dto.isTermination())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .terminationNotificationDuty(dto.isTerminationNotificationDuty())
                .terminationNotificationDutyCompleted(dto.isTerminationNotificationDutyCompleted())
                .build();
    }
}
