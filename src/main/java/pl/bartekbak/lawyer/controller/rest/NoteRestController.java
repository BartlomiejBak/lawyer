package pl.bartekbak.lawyer.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bartekbak.lawyer.entity.Note;
import pl.bartekbak.lawyer.exceptions.ResourceNotFoundException;
import pl.bartekbak.lawyer.service.spring.data.NoteServiceSpringData;

import java.util.List;

@RestController
@RequestMapping("/api/note")
public class NoteRestController {
    NoteServiceSpringData noteService;

    @Autowired
    public NoteRestController(NoteServiceSpringData noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/all")
    public List<Note> getAllNotes() {
        return noteService.findAllNotes();
    }

    @GetMapping("/id/{noteId}")
    public Note getNote(@PathVariable int noteId) {
        Note note = noteService.findNoteById(noteId);
        if (note == null) {
            throw new ResourceNotFoundException("No such Id in database");
        }
        return note;
    }

    @PostMapping("/register")
    public Note addNote(@RequestBody Note note) {
        note.setNoteId(0);
        noteService.saveNote(note);
        return note;
    }

    @PutMapping("/register")
    public Note updateNote(@RequestBody Note note) {
        noteService.saveNote(note);
        return note;
    }

    @DeleteMapping("/remove/{noteId}")
    public String deleteNote(@PathVariable int noteId) {
        Note note = noteService.findNoteById(noteId);
        if (note == null) {
            throw new ResourceNotFoundException("No such Id in database");
        }
        noteService.deleteNoteById(noteId);
        return "Note successfully deleted";
    }
}
