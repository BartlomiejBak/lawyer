package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bartekbak.lawyer.dto.TaskDTO;

import javax.persistence.*;
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
    private String description;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "task_contact",
            joinColumns = @JoinColumn(name = "task"),
            inverseJoinColumns = @JoinColumn(name = "contact")
    )
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
                .contactList(contactList.stream().map(Contact::toDto).collect(Collectors.toCollection(ArrayList::new)))
                .build();
    }

    public static Task fromDto(TaskDTO dto) {
        return Task.builder()
                .taskId(dto.getTaskId())
                .priority(dto.isPriority())
                .deadline(dto.getDeadline())
                .description(dto.getDescription())
                .contactList(dto.getContactList().stream().map(Contact::fromDto).collect(Collectors.toCollection(ArrayList::new)))
                .build();
    }

}
