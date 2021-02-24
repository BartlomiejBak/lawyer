package pl.bartekbak.lawyer.service.spring.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dao.TagRepository;
import pl.bartekbak.lawyer.entity.Tag;
import pl.bartekbak.lawyer.service.TagService;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceSpringData implements TagService {
    TagRepository tagRepository;

    @Autowired
    public TagServiceSpringData(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> findAllTags() {
        return tagRepository.findAllByOrderByNameAsc();
    }

    @Override
    public Tag findTagById(int id) {
        Optional<Tag> result = tagRepository.findById(id);
        Tag tag;
        if (result.isPresent()) {
            tag = result.get();
        } else {
            throw new RuntimeException("Tag id not found");
        }
        return tag;
    }

    @Override
    public void saveTag(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public void deleteTagById(int id) {
        tagRepository.deleteById(id);
    }
}
