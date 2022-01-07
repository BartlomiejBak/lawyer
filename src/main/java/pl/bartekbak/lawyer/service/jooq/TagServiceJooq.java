package pl.bartekbak.lawyer.service.jooq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dto.TagDTO;
import pl.bartekbak.lawyer.entity.Tag;
import pl.bartekbak.lawyer.repository.TagRepository;
import pl.bartekbak.lawyer.service.TagService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TagServiceJooq implements TagService {
    TagRepository tagRepository;

    @Autowired
    public TagServiceJooq(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<TagDTO> findAllTags() {
        return tagRepository.list()
                .stream()
                .map(Tag::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TagDTO findTagById(UUID id) {
        Optional<Tag> result = tagRepository.tagById(id);
        TagDTO tag;
        tag = result.map(Tag::toDto).orElse(null);
        return tag;
    }

    @Override
    public void saveTag(TagDTO tag) {
        tagRepository.add(Tag.fromDto(tag));
    }

    @Override
    public void deleteTagById(UUID id) {
        tagRepository.deleteById(id);
    }
}
