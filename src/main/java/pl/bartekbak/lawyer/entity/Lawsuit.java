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
    private List<Task> taskList;
    @OneToMany
    private List<Contact> plaintiff;
    @OneToMany
    private List<Contact> defendant;

    public Lawsuit(int id, String name, String caseSide) {
        this.id = id;
        this.name = name;
        this.caseSide = caseSide;
    }
}
