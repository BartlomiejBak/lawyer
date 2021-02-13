package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.pl.NIP;
import org.hibernate.validator.constraints.pl.PESEL;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
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
    @Size(min = 2, max = 50)
    private String name;

    @Column(name = "first_name")
    @Size(max = 50)
    private String firstName;

    @Column(name = "last_name")
    @Size(max = 50)
    private String lastName;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "alt_email")
    @Email
    private String altEmail;

    @Column(name = "phone")
    @Size(max = 20)
    private String phone;

    @Column(name = "alt_phone")
    @Size(max = 20)
    private String altPhone;

    @Column(name = "company_name")
    @Size(max = 100)
    private String companyName;

    @Column(name = "pesel")
    @PESEL
    private String pesel;

    @Column(name = "nip")
    @NIP
    private String nip;

    @Column(name = "regon")
    @REGON
    private String regon;

    @Column(name = "krs")
    @Size(max = 10)
    private String krs;

    @Column(name = "date_created")
    @DateTimeFormat
    private LocalDateTime dateCreated;

    @Column(name = "modified")
    @DateTimeFormat
    private LocalDateTime dateModified;

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
