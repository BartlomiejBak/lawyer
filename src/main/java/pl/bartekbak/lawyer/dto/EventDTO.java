package pl.bartekbak.lawyer.dto;

import org.springframework.format.annotation.DateTimeFormat;
import pl.bartekbak.lawyer.entity.Lawsuit;

import javax.persistence.Lob;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class EventDTO {

    private int eventId;

    @Size(min = 2, max = 50)
    private String title;

    @DateTimeFormat
    private LocalDateTime dateTime;

    @Lob
    @Size(max = 1500)
    private String description;

    private Lawsuit relatedLawsuit;
}
