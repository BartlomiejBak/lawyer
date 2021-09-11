package pl.bartekbak.lawyer.repository.jooq;

import org.springframework.stereotype.Repository;
import pl.bartekbak.lawyer.entity.Tag;
import pl.bartekbak.lawyer.repository.TagRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class TagRepositoryImpl implements TagRepository {
    @Override
    public List<Tag> list() {
        return List.of();
    }

    @Override
    public Optional<Tag> tagById(int id) {
        return Optional.empty();
    }

    @Override
    public void add(Tag tag) {

    }

    @Override
    public void deleteById(int id) {

    }
}
