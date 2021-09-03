package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bartekbak.lawyer.dto.NoteDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Note {

    private int noteId;

    private String title;

    private String text;

    public NoteDTO toDto() {
        return NoteDTO.builder()
                .noteId(noteId)
                .title(title)
                .text(text)
                .build();
    }

    public static Note fromDto(NoteDTO dto) {
        return Note.builder()
                .noteId(dto.getNoteId())
                .title(dto.getTitle())
                .text(dto.getText())
                .build();
    }
}
