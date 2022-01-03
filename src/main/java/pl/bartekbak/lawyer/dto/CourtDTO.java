package pl.bartekbak.lawyer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CourtDTO {

    private UUID courtId;

    @Size(min = 2, max = 50)
    private String name;

    @Builder.Default
    private AddressDTO address = AddressDTO.builder().build();

    @Size(max = 255)
    private String description;

    @Size(max = 20)
    private String phone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourtDTO courtDTO = (CourtDTO) o;

        if (getCourtId() != null ? !getCourtId().equals(courtDTO.getCourtId()) : courtDTO.getCourtId() != null)
            return false;
        if (getName() != null ? !getName().equals(courtDTO.getName()) : courtDTO.getName() != null) return false;
        if (getDescription() != null ? !getDescription().equals(courtDTO.getDescription()) : courtDTO.getDescription() != null)
            return false;
        return getPhone() != null ? getPhone().equals(courtDTO.getPhone()) : courtDTO.getPhone() == null;
    }

    @Override
    public int hashCode() {
        int result = getCourtId() != null ? getCourtId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        return result;
    }
}
