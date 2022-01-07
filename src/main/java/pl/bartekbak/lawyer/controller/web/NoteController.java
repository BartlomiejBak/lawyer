package pl.bartekbak.lawyer.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.bartekbak.lawyer.dto.NoteDTO;
import pl.bartekbak.lawyer.service.NoteService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

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
        List<NoteDTO> noteList = noteService.findAllNotes();
        model.addAttribute("notes", noteList);
        return "notes/list-notes";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        NoteDTO note = new NoteDTO();
        model.addAttribute("note", note);
        return NOTE_ADD_FORM;
    }

    @GetMapping("/{noteId}/edit")
    public String showFormForUpdate(@PathVariable UUID noteId, Model model) {
        NoteDTO note = noteService.findNoteById(noteId);
        model.addAttribute("note", note);
        return NOTE_ADD_FORM;
    }

    @PostMapping("/save")
    public String saveNote(@Valid @ModelAttribute("note") NoteDTO note, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError ->
                log.debug(objectError.toString()));
            return NOTE_ADD_FORM;
        }
        noteService.saveNote(note);
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("noteId") UUID id) {
        noteService.deleteNoteById(id);
        return "redirect:list";
    }

    @GetMapping("/{noteId}")
    public ModelAndView showNote(@PathVariable UUID noteId) {
        ModelAndView mav = new ModelAndView("notes/note-details");
        mav.addObject(noteService.findNoteById(noteId));
        return mav;
    }
}
