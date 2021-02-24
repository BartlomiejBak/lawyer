package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.Size;
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
    private int taskId;

    @Column(name = "priority")
    private boolean priority;

    @Column(name = "deadline")
    @Future
    private LocalDateTime deadline;

    @Column(name = "description")
    @Lob
    @Size(max = 1500)
    private String description;

    @ManyToMany(mappedBy = "taskList")
    @Builder.Default
    private List<Lawsuit> lawsuitList = new ArrayList<>();

    @ManyToMany(mappedBy = "taskList")
    @Builder.Default
    private List<Contact> contactList = new ArrayList<>();

}
