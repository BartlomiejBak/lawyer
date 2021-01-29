package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.entity.Note;

import java.util.List;

public interface NoteService {
    List<Note> findAllNotes();

    Note findNoteById(int id);

    void saveNote(Note note);

    void deleteNoteById(int id);
}
