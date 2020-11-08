package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.entity.Note;

import java.util.List;

public interface NoteService {
    public List<Note> findAllNotes();

    public Note findNoteById(int id);

    public void saveNote(Note note);

    public void deleteNoteById(int id);
}
