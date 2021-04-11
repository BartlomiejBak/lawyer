package pl.bartekbak.lawyer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TagDTO {

    private int tagId;

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagDTO tagDTO = (TagDTO) o;

        if (getTagId() != tagDTO.getTagId()) return false;
        return getName() != null ? getName().equals(tagDTO.getName()) : tagDTO.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getTagId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}
