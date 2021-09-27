package pl.bartekbak.lawyer.repository;

import pl.bartekbak.lawyer.entity.Note;

import java.util.List;
import java.util.Optional;

public interface NoteRepository {

    List<Note> list();
    Optional<Note> noteById(int id);
    void add(Note note);
    void update(Note note);
    void deleteById(int id);
}
