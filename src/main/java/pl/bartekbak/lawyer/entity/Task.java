package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "task")
public class Task {

    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "priority")
    private boolean priority;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @Column(name = "description")
    @Lob
    private String description;

    @ManyToMany(mappedBy = "taskList")
    @Builder.Default
    private List<Lawsuit> lawsuitList = new ArrayList<>();

    @ManyToMany(mappedBy = "taskList")
    @Builder.Default
    private List<Contact> contactList = new ArrayList<>();

}
