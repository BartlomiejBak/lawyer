package pl.bartekbak.lawyer.entity;

import lombok.*;
import pl.bartekbak.lawyer.dto.EventDTO;
import pl.bartekbak.lawyer.generate.jooq.tables.records.DbEventRecord;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    private int eventId;

    private String title;

    private LocalDateTime dateTime;

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

    public static Event fromDbRecord(DbEventRecord record) {
        return Event.builder()
                .eventId(record.getEventId())
                .title(record.getTitle())
                .dateTime(record.getDateTime())
                .description(record.getDescription())
                .build();
    }
}
