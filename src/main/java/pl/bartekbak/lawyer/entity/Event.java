package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bartekbak.lawyer.dto.EventDTO;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "event")
public class Event {

    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventId;

    @Column(name = "title")
    private String title;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "description")
    @Lob
    private String description;

    @Deprecated
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "lawsuit_event_id", referencedColumnName = "case_id")
    private Lawsuit relatedLawsuit;

    public EventDTO toDto() {
        return EventDTO.builder()
                .eventId(eventId)
                .title(title)
                .dateTime(dateTime)
                .description(description)
                .build();
    }

    public static Event fromDto(EventDTO dto) {
        return Event.builder()
                .eventId(dto.getEventId())
                .title(dto.getTitle())
                .dateTime(dto.getDateTime())
                .description(dto.getDescription())
                .build();
    }
}
