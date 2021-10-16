package pl.bartekbak.lawyer.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;
import pl.bartekbak.lawyer.dto.LawsuitDTO;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Lawsuit {

    @JsonProperty("lawsuit_id")
    private int lawsuitId;

    private String name;

    @JsonProperty("case_side")
    private String caseSide;

    @JsonProperty("input_date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate inputDate;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate deadline;

    private String signature;

    @JsonProperty("claim_amount")
    private double claimAmount;

    @JsonProperty("additional_info")
    private String additionalInfo;

    @Builder.Default
    private List<ContactRole> contacts = new ArrayList<>();

    @Builder.Default
    private List<Task> tasklist = new ArrayList<>();

    @Builder.Default
    @JsonProperty("event_set")
    private List<Event> eventSet = new ArrayList<>();

    public void addTask(Task task) {
        this.tasklist.add(task);
    }

    public void addContact(Contact contact) {
        this.contacts.add(ContactRole.builder()
                .contact(contact)
                .role(UserRole.CONTACT.value)
                .build());
    }

    public void addPlaintiff(Contact contact) {
        this.contacts.add(ContactRole.builder()
                        .contact(contact)
                        .role(UserRole.PLAINTIFF.value)
                .build());
    }

    public void addDefendant(Contact contact) {
        this.contacts.add(ContactRole.builder()
                .contact(contact)
                .role(UserRole.DEFENDANT.value)
                .build());
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
                .contactList(
                        contacts.stream()
                        .filter(c -> UserRole.valueOf(c.getRole()).equals(UserRole.CONTACT))
                        .map(ContactRole::getContact)
                        .map(Contact::toDto)
                        .collect(Collectors.toList())
                )
                .taskList(tasklist.stream().map(Task::toDto).collect(Collectors.toList()))
                .plaintiff(
                        contacts.stream()
                        .filter(c -> UserRole.valueOf(c.getRole()).equals(UserRole.PLAINTIFF))
                        .map(ContactRole::getContact)
                        .map(Contact::toDto)
                        .collect(Collectors.toList())
                )
                .defendant(
                        contacts.stream()
                        .filter(c -> UserRole.valueOf(c.getRole()).equals(UserRole.DEFENDANT))
                        .map(ContactRole::getContact)
                        .map(Contact::toDto)
                        .collect(Collectors.toList())
                )
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
                .contacts(new ArrayList<>(contacts(dto)))
                .tasklist(dto.getTaskList().stream().map(Task::fromDto).collect(Collectors.toList()))
                .eventSet(dto.getEventSet().stream().map(Event::fromDto).collect(Collectors.toList()))
                .build();
    }

    private static Set<ContactRole> contacts(LawsuitDTO lawsuit) {
        Set<ContactRole> newContacts = new HashSet<>();
        newContacts.addAll(lawsuit.getContactList().stream()
                .map(c -> ContactRole.builder().contact(Contact.fromDto(c)).role(UserRole.CONTACT.value).build())
                .collect(Collectors.toSet()));
        newContacts.addAll(lawsuit.getPlaintiff().stream()
                .map(c -> ContactRole.builder().contact(Contact.fromDto(c)).role(UserRole.PLAINTIFF.value).build())
                .collect(Collectors.toSet()));
        newContacts.addAll(lawsuit.getDefendant().stream()
                .map(c -> ContactRole.builder().contact(Contact.fromDto(c)).role(UserRole.DEFENDANT.value).build())
                .collect(Collectors.toSet()));
        return newContacts;
    }
}
