package pl.bartekbak.lawyer.entity;

import lombok.*;
import org.hibernate.Hibernate;
import pl.bartekbak.lawyer.dto.LawsuitDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "lawsuit")
public class Lawsuit {

    @Id
    @Column(name = "lawsuit_id")
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "contact_role_lawsuit",
            joinColumns = @JoinColumn(name = "lawsuit"),
            inverseJoinColumns = @JoinColumn(name = "contact_role")
    )
    @Builder.Default
    @ToString.Exclude
    private Set<ContactRole> contacts = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "lawsuit_task",
            joinColumns = @JoinColumn(name = "lawsuit"),
            inverseJoinColumns = @JoinColumn(name = "task")
    )
    @Builder.Default
    @ToString.Exclude
    private Set<Task> taskList = new HashSet<>();

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(
            name = "event_lawsuit",
            joinColumns = @JoinColumn(name = "lawsuit"),
            inverseJoinColumns = @JoinColumn(name = "event")
    )
    @Builder.Default
    @ToString.Exclude
    private Set<Event> eventSet = new HashSet<>();

    public void addTask(Task task) {
        this.taskList.add(task);
    }

//    TODO fix
//    public void addPlaintiff(Contact contact) {
//        this.contactList.add(contact);
//    }
//
//    public void addDefendant(Contact contact) {
//        this.contactList.add(contact);
//    }

    public void addEvent(Event event) {
        this.eventSet.add(event);
    }

    // TODO fix
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
//                .contactList(contactList.stream().map(Contact::toDto).collect(Collectors.toList()))
                .taskList(taskList.stream().map(Task::toDto).collect(Collectors.toList()))
//                .plaintiff(plaintiff.stream().map(Contact::toDto).collect(Collectors.toList()))
//                .defendant(defendant.stream().map(Contact::toDto).collect(Collectors.toList()))
                .eventSet(eventSet.stream().map(Event::toDto).collect(Collectors.toSet()))
                .build();
    }

    // TODO fix
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
//                .contactList(dto.getContactList().stream().map(Contact::fromDto).collect(Collectors.toList()))
//                .taskList(dto.getTaskList().stream().map(Task::fromDto).collect(Collectors.toList()))
//                .plaintiff(dto.getPlaintiff().stream().map(Contact::fromDto).collect(Collectors.toList()))
//                .defendant(dto.getDefendant().stream().map(Contact::fromDto).collect(Collectors.toList()))
                .eventSet(dto.getEventSet().stream().map(Event::fromDto).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Lawsuit lawsuit = (Lawsuit) o;
        return Objects.equals(lawsuitId, lawsuit.lawsuitId);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
