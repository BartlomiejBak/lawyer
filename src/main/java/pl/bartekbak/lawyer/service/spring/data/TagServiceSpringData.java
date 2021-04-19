package pl.bartekbak.lawyer.service.spring.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dao.TagRepository;
import pl.bartekbak.lawyer.dto.TagDTO;
import pl.bartekbak.lawyer.entity.Tag;
import pl.bartekbak.lawyer.service.TagService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagServiceSpringData implements TagService {
    TagRepository tagRepository;

    @Autowired
    public TagServiceSpringData(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<TagDTO> findAllTags() {
        return tagRepository.findAllByOrderByNameAsc()
                .stream()
                .map(Tag::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TagDTO findTagById(int id) {
        Optional<Tag> result = tagRepository.findById(id);
        TagDTO tag;
        if (result.isPresent()) {
            tag = result.get().toDto();
        } else {
            throw new RuntimeException("Tag id not found");
        }
        return tag;
    }

    @Override
    public void saveTag(TagDTO tag) {
        tagRepository.save(Tag.fromDto(tag));
    }

    @Override
    public void deleteTagById(int id) {
        tagRepository.deleteById(id);
    }
}
