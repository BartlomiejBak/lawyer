package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.dto.NoteDTO;

import java.util.List;
import java.util.UUID;

public interface NoteService {
    List<NoteDTO> findAllNotes();

    NoteDTO findNoteById(UUID id);

    void saveNote(NoteDTO note);

    void deleteNoteById(UUID id);
}
