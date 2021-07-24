package pl.bartekbak.lawyer.entity;

import lombok.*;
import org.hibernate.Hibernate;
import pl.bartekbak.lawyer.dto.ContactDTO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "contact")
public class Contact {

    @Id
    @Column(name = "contact_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactId;

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
    private LocalDateTime dateCreated;

    @Column(name = "modified")
    private LocalDateTime dateModified;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "address", referencedColumnName = "address_id")
    @ToString.Exclude
    private Address address;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "secondary_address", referencedColumnName = "address_id")
    @ToString.Exclude
    private Address correspondenceAddress;


    public ContactDTO toDto() {
        return ContactDTO.builder()
                .contactId(contactId)
                .name(name)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .altEmail(altEmail)
                .phone(phone)
                .altPhone(altPhone)
                .companyName(companyName)
                .pesel(pesel)
                .nip(nip)
                .regon(regon)
                .krs(krs)
                .dateCreated(dateCreated)
                .dateModified(dateModified)
                .address(address.toDto())
                .correspondenceAddress(correspondenceAddress.toDto())
                .build();
    }

    public static Contact fromDto(ContactDTO dto) {
        return Contact.builder()
                .contactId(dto.getContactId())
                .name(dto.getName())
                .firstName(dto.getName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .altEmail(dto.getAltEmail())
                .phone(dto.getPhone())
                .altPhone(dto.getAltPhone())
                .companyName(dto.getCompanyName())
                .pesel(dto.getPesel())
                .nip(dto.getNip())
                .regon(dto.getRegon())
                .krs(dto.getKrs())
                .dateCreated(dto.getDateCreated())
                .dateModified(dto.getDateModified())
                .address(Address.fromDto(dto.getAddress()))
                .correspondenceAddress(Address.fromDto(dto.getCorrespondenceAddress()))
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Contact contact = (Contact) o;
        return Objects.equals(contactId, contact.contactId);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
