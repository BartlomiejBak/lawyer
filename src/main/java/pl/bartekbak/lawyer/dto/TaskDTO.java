package pl.bartekbak.lawyer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class TaskDTO {

    private UUID taskId;

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

}
