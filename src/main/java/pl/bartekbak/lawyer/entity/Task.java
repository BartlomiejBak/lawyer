package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bartekbak.lawyer.dto.TaskDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    private int taskId;

    private boolean priority;

    private LocalDateTime deadline;

    private String description;

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
