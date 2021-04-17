package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.dto.NoteDTO;

import java.util.List;

public interface NoteService {
    List<NoteDTO> findAllNotes();

    NoteDTO findNoteById(int id);

    void saveNote(NoteDTO note);

    void deleteNoteById(int id);
}
