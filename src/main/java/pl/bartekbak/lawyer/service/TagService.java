package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.entity.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findAllTags();

    Tag findTagById(int id);

    void saveTag(Tag tag);

    void deleteTagById(int id);
}
