package pl.bartekbak.lawyer.service.jooq.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bartekbak.lawyer.dto.TagDTO;
import pl.bartekbak.lawyer.entity.Tag;
import pl.bartekbak.lawyer.repository.TagRepository;
import pl.bartekbak.lawyer.service.jooq.TagServiceJooq;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TagServiceSpringDataTest {

    @InjectMocks
    TagServiceJooq service;

    @Mock
    TagRepository repository;

    private final UUID first = UUID.randomUUID();
    private final UUID second = UUID.randomUUID();
    private final UUID third = UUID.randomUUID();

    @Test
    void findAllTagsTest() {
        // given
        List<Tag> list = new ArrayList<>();
        Tag tagOne = new Tag(first, "Name 1");
        Tag tagTwo = new Tag(second, "Name 2");
        Tag tagThree = new Tag(third, "Name 3");

        list.add(tagOne);
        list.add(tagTwo);
        list.add(tagThree);

        // when
        when(repository.list()).thenReturn(list);
        List<TagDTO> result = service.findAllTags();

        // then
        assertEquals(3, result.size());
        verify(repository, times(1)).list();
    }

    @Test
    void findTagByIdTest() {
        // given
        // when
        when(repository.tagById(first)).thenReturn(Optional
                .of(new Tag(first, "name")));
        TagDTO result = service.findTagById(first);

        // then
        assertEquals("name", result.getName());
    }

    @Test
    void saveTagTest() {
        // given
        Tag tag = new Tag(first, "name");

        // when
        service.saveTag(tag.toDto());
        service.saveTag(new TagDTO());

        // then
        verify(repository, times(1)).add(tag);
        verify(repository, times(2)).add(any());
    }

    @Test
    void deleteTagByIdTest() {
        // given
        // when
        service.deleteTagById(first);
        service.deleteTagById(second);

        // then
        verify(repository, times(1)).deleteById(first);
        verify(repository, times(2)).deleteById(any());
    }
}
