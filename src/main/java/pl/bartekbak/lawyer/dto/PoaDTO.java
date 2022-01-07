package pl.bartekbak.lawyer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class PoaDTO {

    private UUID poaId;

    @Size(max = 50)
    private String poaType;

    @Size(max = 150)
    private String payment;

    private boolean kpc;

    private boolean termination;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate endDate;

    private boolean terminationNotificationDuty;

    private boolean terminationNotificationDutyCompleted;

}
