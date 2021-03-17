package pl.bartekbak.lawyer.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.time.LocalDate;

public class PoaDTO {

    private int poaId;

    @Size(max = 50)
    private String type;

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
