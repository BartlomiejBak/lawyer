package pl.bartekbak.lawyer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bartekbak.lawyer.dto.NoteDTO;
import pl.bartekbak.lawyer.generate.jooq.tables.records.DbNoteRecord;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Note {

    private UUID noteId;

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
    
    public static Note fromDbRecord(DbNoteRecord record) {
        return Note.builder()
                .noteId(record.getNoteId())
                .title(record.getTitle())
                .text(record.getText())
                .build();
    }
}
