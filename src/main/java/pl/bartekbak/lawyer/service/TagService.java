package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.dto.TagDTO;

import java.util.List;

public interface TagService {
    List<TagDTO> findAllTags();

    TagDTO findTagById(int id);

    void saveTag(TagDTO tag);

    void deleteTagById(int id);
}
