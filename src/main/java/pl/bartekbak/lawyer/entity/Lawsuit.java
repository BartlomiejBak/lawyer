package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private int id;

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

    @ManyToMany(mappedBy = "lawsuitList")
    @Builder.Default
    private List<Contact> contactList = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "lawsuit_task",
            joinColumns = @JoinColumn(name = "lawsuit_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    @Builder.Default
    private List<Task> taskList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "lawsuit_id_plaintiff")
    @Builder.Default
    private List<Contact> plaintiff = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "lawsuit_id_defendant")
    @Builder.Default
    private List<Contact> defendant = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "lawsuit_id_event")
    @Builder.Default
    private Set<Event> eventSet = new HashSet<>();
}
