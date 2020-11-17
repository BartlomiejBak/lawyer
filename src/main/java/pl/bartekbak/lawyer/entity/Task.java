package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private String description;

    @ManyToMany(mappedBy = "taskList")
    private List<Lawsuit> lawsuitList;
    @ManyToMany(mappedBy = "taskList")
    private List<Contact> contactList;

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public Task(boolean priority, LocalDateTime deadline, String description, List<Lawsuit> lawsuitList, List<Contact> contactList) {
        this.priority = priority;
        this.deadline = deadline;
        this.description = description;
        this.lawsuitList = lawsuitList;
        this.contactList = contactList;
    }

    //add tags
}
