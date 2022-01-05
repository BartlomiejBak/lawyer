package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.dto.TagDTO;

import java.util.List;
import java.util.UUID;

public interface TagService {
    List<TagDTO> findAllTags();

    TagDTO findTagById(UUID id);

    void saveTag(TagDTO tag);

    void deleteTagById(UUID id);
}
