package pl.bartekbak.lawyer.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cases")
public class Case {
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

    @ManyToMany(mappedBy = "caseList")
    private List<Contact> contactList;
    @ManyToMany
    private List<Task> taskList;

}
