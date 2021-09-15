package pl.bartekbak.lawyer.service.jooq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dto.NoteDTO;
import pl.bartekbak.lawyer.entity.Note;
import pl.bartekbak.lawyer.exceptions.ResourceNotFoundException;
import pl.bartekbak.lawyer.repository.NoteRepository;
import pl.bartekbak.lawyer.service.NoteService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteServiceJooq implements NoteService {
    NoteRepository noteRepository;

    @Autowired
    public NoteServiceJooq(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<NoteDTO> findAllNotes() {
        return noteRepository.list()
                .stream()
                .map(Note::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public NoteDTO findNoteById(int id) {
        Optional<Note> result = noteRepository.noteById(id);
        NoteDTO note;
        if (result.isPresent()) {
            note = result.get().toDto();
        } else {
            throw new ResourceNotFoundException("Note id not found");
        }
        return note;

    }

    @Override
    public void saveNote(NoteDTO note) {
        noteRepository.add(Note.fromDto(note));
    }

    @Override
    public void deleteNoteById(int id) {
        noteRepository.deleteById(id);
    }
}