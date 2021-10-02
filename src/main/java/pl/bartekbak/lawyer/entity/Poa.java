package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bartekbak.lawyer.dto.PoaDTO;
import pl.bartekbak.lawyer.generate.jooq.tables.records.DbPoaRecord;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Poa {

    private int poaId;

    private String type;

    private String payment;

    private boolean kpc;

    private boolean termination;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean terminationNotificationDuty;

    private boolean terminationNotificationDutyCompleted;

    public PoaDTO toDto() {
        return PoaDTO.builder()
                .poaId(poaId)
                .poaType(type)
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
                .type(dto.getPoaType())
                .payment(dto.getPayment())
                .kpc(dto.isKpc())
                .termination(dto.isTermination())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .terminationNotificationDuty(dto.isTerminationNotificationDuty())
                .terminationNotificationDutyCompleted(dto.isTerminationNotificationDutyCompleted())
                .build();
    }

    public static Poa fromDbRecord(DbPoaRecord record) {
        return Poa.builder()
                .poaId(record.getPoaId())
                .type(record.getType())
                .payment(record.getPayment())
                .kpc(record.getKpc())
                .termination(record.getTermination())
                .startDate(record.getStartDate())
                .endDate(record.getEndDate())
                .terminationNotificationDuty(record.getNotificationDuty())
                .terminationNotificationDutyCompleted(record.getDutyCompleted())
                .build();
    }
}
