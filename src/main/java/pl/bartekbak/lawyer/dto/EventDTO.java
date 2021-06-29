package pl.bartekbak.lawyer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Lob;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class EventDTO {

    private int eventId;

    @Size(min = 2, max = 50)
    private String title;

    @DateTimeFormat
    private LocalDateTime dateTime;

    @Lob
    @Size(max = 1500)
    private String description;

    @Deprecated
    private LawsuitDTO relatedLawsuit;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventDTO eventDTO = (EventDTO) o;

        return getTitle() != null ? getTitle().equals(eventDTO.getTitle()) : eventDTO.getTitle() == null;
    }

    @Override
    public int hashCode() {
        return getTitle() != null ? getTitle().hashCode() : 0;
    }
}
