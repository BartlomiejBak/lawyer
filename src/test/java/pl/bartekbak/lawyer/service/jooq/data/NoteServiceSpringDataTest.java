package pl.bartekbak.lawyer.service.jooq.data;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bartekbak.lawyer.repository.NoteRepository;
import pl.bartekbak.lawyer.dto.NoteDTO;
import pl.bartekbak.lawyer.entity.Note;
import pl.bartekbak.lawyer.service.jooq.NoteServiceJooq;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Disabled
@ExtendWith({MockitoExtension.class})
class NoteServiceSpringDataTest {

    @InjectMocks
    NoteServiceJooq service;

    @Mock
    NoteRepository repository;

    @Test
    void findAllNotesTest() {
        //given
        List<Note> list = new ArrayList<>();
        Note noteOne = new Note(1, "title1", "text1");
        Note noteTwo = new Note(2, "title2", "text2");
        Note noteThree = new Note(3, "title3", "text3");

        list.add(noteOne);
        list.add(noteTwo);
        list.add(noteThree);

        //when
        when(repository.list()).thenReturn(list);
        List<NoteDTO> result = service.findAllNotes();

        //then
        assertEquals(3, result.size());
        verify(repository, times(1)).list();
    }

    @Test
    void findNoteByIdTest() {
        //given
        //when
        when(repository.noteById(1))
                .thenReturn(Optional.of(new Note(1, "title1", "text1")));
        NoteDTO result = service.findNoteById(1);
        //then
        assertEquals("title1", result.getTitle());
        assertEquals("text1", result.getText());
    }

    @Test
    void saveNoteTest() {
        //given
        Note note = new Note(1, "title1", "text1");
        //when
        service.saveNote(note.toDto());
        service.saveNote(new NoteDTO());
        //then
        verify(repository, times(2)).add(any());
    }

    @Test
    void deleteNoteByIdTest() {
        //given
        //when
        service.deleteNoteById(1);
        service.deleteNoteById(2);
        //then
        verify(repository, times(1)).deleteById(1);
        verify(repository, times(2)).deleteById(any());
    }
}
