package pl.bartekbak.lawyer.repository.jooq;

import org.springframework.stereotype.Repository;
import pl.bartekbak.lawyer.entity.Note;
import pl.bartekbak.lawyer.repository.NoteRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class NoteRepositoryImpl implements NoteRepository {
    @Override
    public List<Note> list() {
        return List.of();
    }

    @Override
    public Optional<Note> noteById(int id) {
        return Optional.empty();
    }

    @Override
    public void add(Note note) {

    }

    @Override
    public void deleteById(int id) {

    }
}
