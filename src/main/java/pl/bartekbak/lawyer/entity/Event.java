package pl.bartekbak.lawyer.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "lawsuit_event_id", referencedColumnName = "case_id")
    private Lawsuit relatedLawsuit;
}
