package pl.bartekbak.lawyer.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
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

    @OneToOne
    private Address address;
    @ManyToMany
    private List<Lawsuit> lawsuitList;

    // private List<Field> fieldList;
    @ManyToMany
    private List<Task> taskList;
}
