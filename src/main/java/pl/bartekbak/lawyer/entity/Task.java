package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private String description;

    @ManyToMany(mappedBy = "taskList")
    private List<Lawsuit> lawsuitList;
    @ManyToMany(mappedBy = "taskList")
    private List<Contact> contactList;

    //add tags
}
