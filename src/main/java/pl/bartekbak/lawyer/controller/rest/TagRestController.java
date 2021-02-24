package pl.bartekbak.lawyer.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bartekbak.lawyer.entity.Tag;
import pl.bartekbak.lawyer.service.spring.data.TagServiceSpringData;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagRestController {
    TagServiceSpringData service;

    @Autowired
    public TagRestController(TagServiceSpringData service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Tag> getAllTags() {
        return service.findAllTags();
    }

    @GetMapping("/id/{tagId}")
    public Tag getTag(@PathVariable int tagId) {
        Tag tag = service.findTagById(tagId);
        if (tag == null) {
            throw new RuntimeException("No such Id in database");
        }
        return tag;
    }

    @PostMapping("/register")
    public Tag addTag(@RequestBody Tag tag) {
        tag.setTagId(0);
        service.saveTag(tag);
        return tag;
    }

    @PutMapping("/register")
    public Tag updateTag(@RequestBody Tag tag) {
        service.saveTag(tag);
        return tag;
    }

    @DeleteMapping("/remove/{tagId}")
    public String deleteTag(@PathVariable int tagId) {
        Tag tag = service.findTagById(tagId);
        if (tag == null) {
            throw new RuntimeException("No such Id in database");
        }
        service.deleteTagById(tagId);
        return "Tag successfully deleted";
    }
}
