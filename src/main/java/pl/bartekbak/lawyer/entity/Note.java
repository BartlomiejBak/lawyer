package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bartekbak.lawyer.dto.NoteDTO;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "note")
public class Note {

    @Id
    @Column(name = "note_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int noteId;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
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
