package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "contact")
public class Contact {

    @Id
    @Column(name = "contact_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "alt_email")
    private String altEmail;

    @Column(name = "phone")
    private String phone;

    @Column(name = "alt_phone")
    private String altPhone;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "pesel")
    private String pesel;

    @Column(name = "nip")
    private String nip;

    @Column(name = "regon")
    private String regon;

    @Column(name = "krs")
    private String krs;

    @Column(name = "date_created")
    private LocalDate dateCreated;

    @Column(name = "modified")
    private LocalDate dateModified;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "secondary_address_id", referencedColumnName = "address_id")
    private Address correspondenceAddress;

    @ManyToMany
    @JoinTable(
            name = "contact_lawsuit",
            joinColumns = @JoinColumn(name = "contact_id"),
            inverseJoinColumns = @JoinColumn(name = "lawsuit_id")
    )
    @Builder.Default
    private List<Lawsuit> lawsuitList = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "contact_task",
            joinColumns = @JoinColumn(name = "contact_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    @Builder.Default
    private List<Task> taskList = new ArrayList<>();

    public void addLawsuit(Lawsuit lawsuit) {
        this.lawsuitList.add(lawsuit);
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }
}
