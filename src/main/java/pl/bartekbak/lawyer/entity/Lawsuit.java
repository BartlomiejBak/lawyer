package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
    private String additionalInfo;

    @ManyToMany(mappedBy = "lawsuitList")
    private List<Contact> contactList;
    @ManyToMany
    @JoinTable(
            name = "lawsuit_task",
            joinColumns = @JoinColumn(name = "lawsuit_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private List<Task> taskList;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "lawsuit_id_plaintiff")
    private List<Contact> plaintiff;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "lawsuit_id_defendant")
    private List<Contact> defendant;

    public Lawsuit(int id, String name, String caseSide) {
        this.id = id;
        this.name = name;
        this.caseSide = caseSide;
    }
}
