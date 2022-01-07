package pl.bartekbak.lawyer.service.jooq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dto.NoteDTO;
import pl.bartekbak.lawyer.entity.Note;
import pl.bartekbak.lawyer.repository.NoteRepository;
import pl.bartekbak.lawyer.service.NoteService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
    public NoteDTO findNoteById(UUID id) {
        Optional<Note> result = noteRepository.noteById(id);
        NoteDTO note;
        note = result.map(Note::toDto).orElse(null);
        return note;

    }

    @Override
    public void saveNote(NoteDTO note) {
        noteRepository.add(Note.fromDto(note));
    }

    @Override
    public void deleteNoteById(UUID id) {
        noteRepository.deleteById(id);
    }
}
