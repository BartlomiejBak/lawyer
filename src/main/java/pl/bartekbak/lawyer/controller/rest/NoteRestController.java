package pl.bartekbak.lawyer.controller.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bartekbak.lawyer.dto.NoteDTO;
import pl.bartekbak.lawyer.exceptions.ResourceNotFoundException;
import pl.bartekbak.lawyer.service.jooq.NoteServiceJooq;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notes")
public class NoteRestController {

    private final NoteServiceJooq noteService;

    public NoteRestController(NoteServiceJooq noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<NoteDTO> getAllNotes() {
        return noteService.findAllNotes();
    }

    @GetMapping("/{noteId}")
    public NoteDTO getNote(@PathVariable UUID noteId) {
        NoteDTO note = noteService.findNoteById(noteId);
        if (note == null) {
            throw new ResourceNotFoundException("No such Id in database");
        }
        return note;
    }

    @PostMapping
    public NoteDTO addNote(@RequestBody NoteDTO note) {
        note.setNoteId(UUID.randomUUID());
        noteService.saveNote(note);
        return note;
    }

    @PutMapping
    public NoteDTO updateNote(@RequestBody NoteDTO note) {
        noteService.saveNote(note);
        return note;
    }

    @DeleteMapping("/{noteId}")
    public String deleteNote(@PathVariable UUID noteId) {
        NoteDTO note = noteService.findNoteById(noteId);
        if (note == null) {
            throw new ResourceNotFoundException("No such Id in database");
        }
        noteService.deleteNoteById(noteId);
        return "Note successfully deleted";
    }
}
