package pl.bartekbak.lawyer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class LawsuitDTO {

    private UUID lawsuitId;

    @Size(min = 2, max = 50)
    private String name;

    @Size(min = 2, max = 50)
    private String caseSide;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate inputDate;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate deadline;

    @Size(min = 2, max = 50)
    private String signature;

    @PositiveOrZero
    private double claimAmount;

    @Size
    private String additionalInfo;

    @Builder.Default
    private List<ContactDTO> contactList = new ArrayList<>();
    @Builder.Default
    private List<TaskDTO> taskList = new ArrayList<>();
    @Builder.Default
    private List<ContactDTO> plaintiff = new ArrayList<>();
    @Builder.Default
    private List<ContactDTO> defendant = new ArrayList<>();
    @Builder.Default
    private Set<EventDTO> eventSet = new HashSet<>();

    public void addContact(ContactDTO contact) {
        this.contactList.add(contact);
    }

    public void addTask(TaskDTO task) {
        this.taskList.add(task);
    }

    public void addPlaintiff(ContactDTO contact) {
        this.contactList.add(contact);
    }

    public void addDefendant(ContactDTO contact) {
        this.contactList.add(contact);
    }

    public void addEvent(EventDTO event) {
        this.eventSet.add(event);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LawsuitDTO that = (LawsuitDTO) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getCaseSide() != null ? !getCaseSide().equals(that.getCaseSide()) : that.getCaseSide() != null)
            return false;
        return getSignature() != null ? getSignature().equals(that.getSignature()) : that.getSignature() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getCaseSide() != null ? getCaseSide().hashCode() : 0);
        result = 31 * result + (getSignature() != null ? getSignature().hashCode() : 0);
        return result;
    }
}
