package pl.bartekbak.lawyer.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.bartekbak.lawyer.entity.Tag;
import pl.bartekbak.lawyer.service.TagService;

import java.util.List;

@Controller
@RequestMapping("/tags")
public class TagController {

    private TagService service;

    @Autowired
    public TagController(TagService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String listAllTags(Model model) {
        List<Tag> tagList = service.findAllTags();
        model.addAttribute("tags", tagList);
        return "tags/list-tags";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Tag tag = new Tag();
        model.addAttribute("tags", tag);
        return "tags/add-tag-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("tagId") int id, Model model) {
        Tag tag = service.findTagById(id);
        model.addAttribute(tag);
        return "tags/add-tag-form";
    }

    @PostMapping("/save")
    public String saveTag(@ModelAttribute("tag") Tag tag) {
        service.saveTag(tag);

        //redirect prevents multiple saving
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("tagId") int id) {
        service.deleteTagById(id);
        return "redirect:list";
    }

}
