package pl.bartekbak.lawyer.service.spring.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dao.NoteRepository;
import pl.bartekbak.lawyer.entity.Note;
import pl.bartekbak.lawyer.service.NoteService;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceSpringData implements NoteService {
    NoteRepository noteRepository;

    @Autowired
    public NoteServiceSpringData(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> findAllNotes() {
        return noteRepository.findAllByOrderByTitleAsc();
    }

    @Override
    public Note findNoteById(int id) {
        Optional<Note> result = noteRepository.findById(id);
        Note note = null;
        if (result.isPresent()) {
            note = result.get();
        } else {
            throw new RuntimeException("Note id not found");
        }
        return note;

    }

    @Override
    public void saveNote(Note note) {
        noteRepository.save(note);
    }

    @Override
    public void deleteNoteById(int id) {
        noteRepository.deleteById(id);
    }
}
