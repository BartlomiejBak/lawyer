package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bartekbak.lawyer.dto.LawsuitDTO;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "lawsuit")
public class Lawsuit {

    @Id
    @Column(name = "case_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lawsuitId;

    @Column(name = "name")
    private String name;

    @Column(name = "cas_side")
    private String caseSide;

    @Column(name = "input_date")
    private LocalDate inputDate;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "signature")
    private String signature;

    @Column(name = "claim_amount")
    private double claimAmount;

    @Column(name = "add_info")
    @Lob
    private String additionalInfo;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Contact> contactList = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "lawsuit_task",
            joinColumns = @JoinColumn(name = "lawsuit_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    @Builder.Default
    private List<Task> taskList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "lawsuit_id_plaintiff")
    @Builder.Default
    private List<Contact> plaintiff = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "lawsuit_id_defendant")
    @Builder.Default
    private List<Contact> defendant = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "lawsuit_id_event")
    @Builder.Default
    private Set<Event> eventSet = new HashSet<>();

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void addPlaintiff(Contact contact) {
        this.contactList.add(contact);
    }

    public void addDefendant(Contact contact) {
        this.contactList.add(contact);
    }

    public void addEvent(Event event) {
        this.eventSet.add(event);
    }

    public LawsuitDTO toDto() {
        return LawsuitDTO.builder()
                .lawsuitId(lawsuitId)
                .name(name)
                .caseSide(caseSide)
                .inputDate(inputDate)
                .deadline(deadline)
                .signature(signature)
                .claimAmount(claimAmount)
                .additionalInfo(additionalInfo)
                .contactList(contactList.stream().map(Contact::toDto).collect(Collectors.toList()))
                .taskList(taskList.stream().map(Task::toDto).collect(Collectors.toList()))
                .plaintiff(plaintiff.stream().map(Contact::toDto).collect(Collectors.toList()))
                .defendant(defendant.stream().map(Contact::toDto).collect(Collectors.toList()))
                .eventSet(eventSet.stream().map(Event::toDto).collect(Collectors.toSet()))
                .build();
    }

    public static Lawsuit fromDto(LawsuitDTO dto) {
        return Lawsuit.builder()
                .lawsuitId(dto.getLawsuitId())
                .name(dto.getName())
                .caseSide(dto.getCaseSide())
                .inputDate(dto.getInputDate())
                .deadline(dto.getDeadline())
                .signature(dto.getSignature())
                .claimAmount(dto.getClaimAmount())
                .additionalInfo(dto.getAdditionalInfo())
                .contactList(dto.getContactList().stream().map(Contact::fromDto).collect(Collectors.toList()))
                .taskList(dto.getTaskList().stream().map(Task::fromDto).collect(Collectors.toList()))
                .plaintiff(dto.getPlaintiff().stream().map(Contact::fromDto).collect(Collectors.toList()))
                .defendant(dto.getDefendant().stream().map(Contact::fromDto).collect(Collectors.toList()))
                .eventSet(dto.getEventSet().stream().map(Event::fromDto).collect(Collectors.toSet()))
                .build();
    }
}
