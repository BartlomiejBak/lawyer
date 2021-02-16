package pl.bartekbak.lawyer.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.bartekbak.lawyer.entity.Tag;
import pl.bartekbak.lawyer.service.TagService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;
    private static final String TAG_ADD_FORM = "tags/add-tag-form";

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/list")
    public String listAllTags(Model model) {
        List<Tag> tagList = tagService.findAllTags();
        model.addAttribute("tags", tagList);
        return "tags/list-tags";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Tag tag = new Tag();
        model.addAttribute("tag", tag);
        return TAG_ADD_FORM;
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("tagId") int id, Model model) {
        Tag tag = tagService.findTagById(id);
        model.addAttribute(tag);
        return TAG_ADD_FORM;
    }

    @PostMapping("/save")
    public String saveTag(@Valid @ModelAttribute("tag") Tag tag, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError ->
                log.debug(objectError.toString()));
            return TAG_ADD_FORM;
        }
        tagService.saveTag(tag);
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("tagId") int id) {
        tagService.deleteTagById(id);
        return "redirect:list";
    }

}
