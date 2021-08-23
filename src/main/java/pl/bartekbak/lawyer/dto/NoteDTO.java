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
public class NoteDTO {

    private int noteId;

    @Size(min = 2, max = 50)
    private String title;

    @Size(max = 1500)
    private String text;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoteDTO noteDTO = (NoteDTO) o;

        if (getNoteId() != noteDTO.getNoteId()) return false;
        if (getTitle() != null ? !getTitle().equals(noteDTO.getTitle()) : noteDTO.getTitle() != null) return false;
        return getText() != null ? getText().equals(noteDTO.getText()) : noteDTO.getText() == null;
    }

    @Override
    public int hashCode() {
        int result = getNoteId();
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getText() != null ? getText().hashCode() : 0);
        return result;
    }
}
