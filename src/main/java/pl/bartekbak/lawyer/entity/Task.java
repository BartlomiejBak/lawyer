package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bartekbak.lawyer.dto.TaskDTO;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private LocalDateTime deadline;

    @Column(name = "description")
    @Lob
    private String description;

    @ManyToMany(mappedBy = "taskList")
    @Builder.Default
    private List<Lawsuit> lawsuitList = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @Builder.Default
    private List<Contact> contactList = new ArrayList<>();

    public void addContact(Contact contact) {
        this.contactList.add(contact);
    }

    public TaskDTO toDto() {
        return TaskDTO.builder()
                .taskId(taskId)
                .priority(priority)
                .deadline(deadline)
                .description(description)
                .lawsuitList(lawsuitList.stream().map(Lawsuit::toDto).collect(Collectors.toCollection(ArrayList::new)))
                .contactList(contactList.stream().map(Contact::toDto).collect(Collectors.toCollection(ArrayList::new)))
                .build();
    }

    public static Task fromDto(TaskDTO dto) {
        return Task.builder()
                .taskId(dto.getTaskId())
                .priority(dto.isPriority())
                .deadline(dto.getDeadline())
                .description(dto.getDescription())
                .lawsuitList(dto.getLawsuitList().stream().map(Lawsuit::fromDto).collect(Collectors.toCollection(ArrayList::new)))
                .contactList(dto.getContactList().stream().map(Contact::fromDto).collect(Collectors.toCollection(ArrayList::new)))
                .build();
    }

}
