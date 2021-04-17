package pl.bartekbak.lawyer.service.spring.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dao.NoteRepository;
import pl.bartekbak.lawyer.dto.NoteDTO;
import pl.bartekbak.lawyer.entity.Note;
import pl.bartekbak.lawyer.service.NoteService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteServiceSpringData implements NoteService {
    NoteRepository noteRepository;

    @Autowired
    public NoteServiceSpringData(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<NoteDTO> findAllNotes() {
        return noteRepository.findAllByOrderByTitleAsc()
                .stream()
                .map(Note::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public NoteDTO findNoteById(int id) {
        Optional<Note> result = noteRepository.findById(id);
        NoteDTO note;
        if (result.isPresent()) {
            note = result.get().toDto();
        } else {
            throw new RuntimeException("Note id not found");
        }
        return note;

    }

    @Override
    public void saveNote(NoteDTO note) {
        noteRepository.save(Note.fromDto(note));
    }

    @Override
    public void deleteNoteById(int id) {
        noteRepository.deleteById(id);
    }
}
