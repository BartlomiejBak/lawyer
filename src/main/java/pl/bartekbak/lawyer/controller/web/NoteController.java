package pl.bartekbak.lawyer.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.bartekbak.lawyer.entity.Note;
import pl.bartekbak.lawyer.service.NoteService;

import java.util.List;

@Controller
@RequestMapping("/notes")
public class NoteController {

    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/list")
    public String listAllNotes(Model model) {
        List<Note> noteList = noteService.findAllNotes();
        model.addAttribute("notes", noteList);
        return "notes/list-notes";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Note note = new Note();
        model.addAttribute("note", note);
        return "notes/add-note-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("noteId") int id, Model model) {
        Note note = noteService.findNoteById(id);
        model.addAttribute(note);
        return "notes/add-note-form";
    }

    @PostMapping("/save")
    public String saveNote(@ModelAttribute("note") Note note) {
        noteService.saveNote(note);

        //redirect prevents multiple saving
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("noteId") int id) {
        noteService.deleteNoteById(id);
        return "redirect:list";
    }
}
