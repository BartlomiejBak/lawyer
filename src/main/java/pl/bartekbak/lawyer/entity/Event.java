package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
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
    @Size(min = 2, max = 50)
    private String title;

    @Column(name = "date_time")
    @DateTimeFormat
    private LocalDateTime dateTime;

    @Column(name = "description")
    @Lob
    @Size(max = 1500)
    private String description;

    @ManyToOne
    @JoinColumn(name = "lawsuit_event_id", referencedColumnName = "case_id")
    private Lawsuit relatedLawsuit;
}
