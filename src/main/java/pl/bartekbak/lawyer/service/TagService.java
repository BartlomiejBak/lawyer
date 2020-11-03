package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.entity.Tag;

import java.util.List;

public interface TagService {
    public List<Tag> findAllTags();

    public Tag findTagById(int id);

    public void saveTag(Tag tag);

    public void deleteTagById(int id);
}
