package pl.bartekbak.lawyer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CourtDTO {

    private int courtId;

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

        if (getCourtId() != courtDTO.getCourtId()) return false;
        return getName() != null ? getName().equals(courtDTO.getName()) : courtDTO.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getCourtId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}
