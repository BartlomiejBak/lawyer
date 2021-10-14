package pl.bartekbak.lawyer.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import pl.bartekbak.lawyer.dto.ContactDTO;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Contact {

    @JsonProperty("contact_id")
    private int contactId;

    private String name;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String email;

    @JsonProperty("alt_email")
    private String altEmail;

    private String phone;

    @JsonProperty("alt_phone")
    private String altPhone;

    @JsonProperty("company_name")
    private String companyName;

    private String pesel;

    private String nip;

    private String regon;

    private String krs;

    @JsonProperty("date_created")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dateCreated;

    @JsonProperty("modified")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
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
                .address(Objects.nonNull(address) ? address.toDto() : null)
                .correspondenceAddress(Objects.nonNull(correspondenceAddress) ? correspondenceAddress.toDto() : null)
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
