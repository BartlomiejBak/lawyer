package pl.bartekbak.lawyer.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
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
import pl.bartekbak.lawyer.service.spring.data.NoteServiceSpringData;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteRestController {
    NoteServiceSpringData noteService;

    @Autowired
    public NoteRestController(NoteServiceSpringData noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<NoteDTO> getAllNotes() {
        return noteService.findAllNotes();
    }

    @GetMapping("/{noteId}")
    public NoteDTO getNote(@PathVariable int noteId) {
        NoteDTO note = noteService.findNoteById(noteId);
        if (note == null) {
            throw new ResourceNotFoundException("No such Id in database");
        }
        return note;
    }

    @PostMapping
    public NoteDTO addNote(@RequestBody NoteDTO note) {
        note.setNoteId(0);
        noteService.saveNote(note);
        return note;
    }

    @PutMapping
    public NoteDTO updateNote(@RequestBody NoteDTO note) {
        noteService.saveNote(note);
        return note;
    }

    @DeleteMapping("/{noteId}")
    public String deleteNote(@PathVariable int noteId) {
        NoteDTO note = noteService.findNoteById(noteId);
        if (note == null) {
            throw new ResourceNotFoundException("No such Id in database");
        }
        noteService.deleteNoteById(noteId);
        return "Note successfully deleted";
    }
}
