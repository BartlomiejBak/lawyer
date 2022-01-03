package pl.bartekbak.lawyer.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import pl.bartekbak.lawyer.dto.EventDTO;
import pl.bartekbak.lawyer.generate.jooq.tables.records.DbEventRecord;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Event {

    @JsonProperty("event_id")
    private UUID eventId;

    private String title;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonProperty("date_time")
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
