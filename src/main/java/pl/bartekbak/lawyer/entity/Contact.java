package pl.bartekbak.lawyer.entity;

import lombok.*;
import pl.bartekbak.lawyer.dto.ContactDTO;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Contact {

    private int contactId;

    private String name;

    private String firstName;

    private String lastName;

    private String email;

    private String altEmail;

    private String phone;

    private String altPhone;

    private String companyName;

    private String pesel;

    private String nip;

    private String regon;

    private String krs;

    private LocalDateTime dateCreated;

    private LocalDateTime dateModified;

    @ToString.Exclude
    private Address address;

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

}
