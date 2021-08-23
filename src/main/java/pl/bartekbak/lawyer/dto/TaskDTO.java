package pl.bartekbak.lawyer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TaskDTO {

    private int taskId;

    private boolean priority;

    @Future
    private LocalDateTime deadline;

    @Size(max = 1500)
    private String description;

    @Builder.Default
    private List<ContactDTO> contactList = new ArrayList<>();

    public void addContact(ContactDTO contactDTO) {
        this.contactList.add(contactDTO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskDTO taskDTO = (TaskDTO) o;

        if (taskId != taskDTO.taskId) return false;
        if (priority != taskDTO.priority) return false;
        if (!Objects.equals(deadline, taskDTO.deadline)) return false;
        return Objects.equals(description, taskDTO.description);
    }

    @Override
    public int hashCode() {
        int result = taskId;
        result = 31 * result + (priority ? 1 : 0);
        result = 31 * result + (deadline != null ? deadline.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
