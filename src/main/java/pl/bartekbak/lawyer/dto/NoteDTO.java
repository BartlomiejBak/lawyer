package pl.bartekbak.lawyer.dto;

import javax.persistence.Lob;
import javax.validation.constraints.Size;

public class NoteDTO {

    private int noteId;

    @Size(min = 2, max = 50)
    private String title;

    @Lob
    @Size(max = 1500)
    private String text;

}
