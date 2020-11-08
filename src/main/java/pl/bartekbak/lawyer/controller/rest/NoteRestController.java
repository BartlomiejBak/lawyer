package pl.bartekbak.lawyer.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bartekbak.lawyer.entity.Note;
import pl.bartekbak.lawyer.service.spring.data.NoteServiceSpringData;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteRestController {
    NoteServiceSpringData noteService;

    @Autowired
    public NoteRestController(NoteServiceSpringData noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        return noteService.findAllNotes();
    }

    @GetMapping("/notes/{noteId}")
    public Note getNote(@PathVariable int noteId) {
        Note note = noteService.findNoteById(noteId);
        if (note == null) {
            throw new RuntimeException("No such Id in database");
        }
        return note;
    }

    @PostMapping("/notes")
    public Note addNote(@RequestBody Note note) {
        note.setId(0);
        noteService.saveNote(note);
        return note;
    }

    @PutMapping("/notes")
    public Note updateNote(@RequestBody Note note) {
        noteService.saveNote(note);
        return note;
    }

    @DeleteMapping("notes/{noteId}")
    public String deleteNote(@PathVariable int noteId) {
        Note note = noteService.findNoteById(noteId);
        if (note == null) {
            throw new RuntimeException("No such Id in database");
        }
        noteService.deleteNoteById(noteId);
        return "Note successfully deleted";
    }
}
