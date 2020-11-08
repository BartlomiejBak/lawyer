package pl.bartekbak.lawyer.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
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
}
