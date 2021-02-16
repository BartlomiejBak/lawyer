package pl.bartekbak.lawyer.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.bartekbak.lawyer.entity.Note;
import pl.bartekbak.lawyer.service.NoteService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;
    private static final String NOTE_ADD_FORM = "notes/add-note-form";

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
        return NOTE_ADD_FORM;
    }

    @GetMapping("/{noteId}/edit")
    public String showFormForUpdate(@PathVariable int noteId, Model model) {
        Note note = noteService.findNoteById(noteId);
        model.addAttribute(note);
        return NOTE_ADD_FORM;
    }

    @PostMapping("/save")
    public String saveNote(@Valid @ModelAttribute("note") Note note, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError ->
                log.debug(objectError.toString()));
            return NOTE_ADD_FORM;
        }
        noteService.saveNote(note);
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("noteId") int id) {
        noteService.deleteNoteById(id);
        return "redirect:list";
    }

    @GetMapping("/{noteId}")
    public ModelAndView showNote(@PathVariable int noteId) {
        ModelAndView mav = new ModelAndView("notes/note-details");
        mav.addObject(noteService.findNoteById(noteId));
        return mav;
    }
}
