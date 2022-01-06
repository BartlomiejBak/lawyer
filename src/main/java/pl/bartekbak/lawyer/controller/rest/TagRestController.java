package pl.bartekbak.lawyer.controller.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bartekbak.lawyer.dto.TagDTO;
import pl.bartekbak.lawyer.exceptions.ResourceNotFoundException;
import pl.bartekbak.lawyer.service.jooq.TagServiceJooq;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tags")
public class TagRestController {

    private final TagServiceJooq service;

    public TagRestController(TagServiceJooq service) {
        this.service = service;
    }

    @GetMapping
    public List<TagDTO> getAllTags() {
        return service.findAllTags();
    }

    @GetMapping("/{tagId}")
    public TagDTO getTag(@PathVariable UUID tagId) {
        TagDTO tag = service.findTagById(tagId);
        if (tag == null) {
            throw new ResourceNotFoundException("No such Id in database");
        }
        return tag;
    }

    @PostMapping
    public TagDTO addTag(@RequestBody TagDTO tag) {
        tag.setTagId(UUID.randomUUID());
        service.saveTag(tag);
        return tag;
    }

    @PutMapping
    public TagDTO updateTag(@RequestBody TagDTO tag) {
        service.saveTag(tag);
        return tag;
    }

    @DeleteMapping("/{tagId}")
    public String deleteTag(@PathVariable UUID tagId) {
        TagDTO tag = service.findTagById(tagId);
        if (tag == null) {
            throw new ResourceNotFoundException("No such Id in database");
        }
        service.deleteTagById(tagId);
        return "Tag successfully deleted";
    }
}
