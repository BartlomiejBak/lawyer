package pl.bartekbak.lawyer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ContactDTO {

    private UUID contactId;

    @Size(min = 2, max = 50)
    private String name;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Email
    private String email;

    @Email
    private String altEmail;

    @Size(max = 20)
    private String phone;

    @Size(max = 20)
    private String altPhone;

    @Size(max = 255)
    private String companyName;

    @Size(max = 11)
    private String pesel;

    @Size(max = 20)
    private String nip;

    @Size(max = 20)
    private String regon;

    @Size(max = 20)
    private String krs;

    @DateTimeFormat
    private LocalDateTime dateCreated;

    @DateTimeFormat
    private LocalDateTime dateModified;

    private AddressDTO address;

    private AddressDTO correspondenceAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactDTO that = (ContactDTO) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getFirstName() != null ? !getFirstName().equals(that.getFirstName()) : that.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(that.getLastName()) : that.getLastName() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(that.getEmail()) : that.getEmail() != null) return false;
        return getPhone() != null ? getPhone().equals(that.getPhone()) : that.getPhone() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        return result;
    }
}
