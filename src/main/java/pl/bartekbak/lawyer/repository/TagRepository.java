package pl.bartekbak.lawyer.repository;

import pl.bartekbak.lawyer.entity.Tag;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TagRepository {

    List<Tag> list();
    Optional<Tag> tagById(UUID id);
    int add(Tag tag);
    void update(Tag tag);
    void deleteById(UUID id);
}
