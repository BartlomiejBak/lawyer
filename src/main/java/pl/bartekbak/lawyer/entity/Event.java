package pl.bartekbak.lawyer.entity;

import lombok.*;
import org.hibernate.Hibernate;
import pl.bartekbak.lawyer.dto.EventDTO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Event event = (Event) o;
        return Objects.equals(eventId, event.eventId);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
