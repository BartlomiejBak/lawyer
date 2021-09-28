package pl.bartekbak.lawyer.repository;

import pl.bartekbak.lawyer.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface TagRepository {

    List<Tag> list();
    Optional<Tag> tagById(int id);
    void add(Tag tag);
    void update(Tag tag);
    void deleteById(int id);
}
