package pl.bartekbak.lawyer.service.spring.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bartekbak.lawyer.dao.TagRepository;
import pl.bartekbak.lawyer.entity.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TagServiceSpringDataTest {

    @InjectMocks
    TagServiceSpringData service;

    @Mock
    TagRepository repository;

    @Test
    void findAllTagsTest() {
        //given
        List<Tag> list = new ArrayList<>();
        Tag tagOne = new Tag(1, "Name 1");
        Tag tagTwo = new Tag(2, "Name 2");
        Tag tagThree = new Tag(3, "Name 3");

        list.add(tagOne);
        list.add(tagTwo);
        list.add(tagThree);

        //when
        when(repository.findAllByOrderByNameAsc()).thenReturn(list);
        List<Tag> result = service.findAllTags();

        //then
        assertEquals(3, result.size());
        verify(repository, times(1)).findAllByOrderByNameAsc();
    }

    @Test
    void findTagByIdTest() {
        //given
        //when
        when(repository.findById(1)).thenReturn(Optional
                .of(new Tag(1, "name")));
        Tag result = service.findTagById(1);
        //then
        assertEquals("name", result.getName());
    }

    @Test
    void saveTagTest() {
        //given
        Tag tag = new Tag(1, "name");
        //when
        service.saveTag(tag);
        service.saveTag(new Tag());
        //then
        verify(repository, times(1)).save(tag);
        verify(repository, times(2)).save(any());
    }

    @Test
    void deleteTagByIdTest() {
        //given
        //when
        service.deleteTagById(1);
        service.deleteTagById(2);
        //then
        verify(repository, times(1)).deleteById(1);
        verify(repository, times(2)).deleteById(any());
    }
}