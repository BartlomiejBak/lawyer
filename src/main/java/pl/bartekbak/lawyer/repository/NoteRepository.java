package pl.bartekbak.lawyer.repository;

import pl.bartekbak.lawyer.entity.Note;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NoteRepository {

    List<Note> list();
    Optional<Note> noteById(UUID id);
    void add(Note note);
    void update(Note note);
    void deleteById(UUID id);
}
