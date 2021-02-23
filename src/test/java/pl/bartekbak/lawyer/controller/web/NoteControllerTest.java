package pl.bartekbak.lawyer.controller.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;
import pl.bartekbak.lawyer.entity.Note;
import pl.bartekbak.lawyer.service.NoteService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class NoteControllerTest {

    @Mock
    NoteService service;
    @InjectMocks
    NoteController controller;

    ObjectMapper objectMapper;
    MockMvc mockMvc;
    private static final String NOTE_ADD_FORM = "notes/add-note-form";
    Note note;

    @BeforeEach
    void setUp() {
        note = Note.builder().id(1).build();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void listNotesTest() throws Exception {
        mockMvc.perform(get("/notes/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("notes/list-notes"))
                .andExpect(model().attributeExists("notes"));
    }

    @Test
    void showFormForAddTest() throws Exception {
        mockMvc.perform(get("/notes/showFormForAdd"))
                .andExpect(status().isOk())
                .andExpect(view().name(NOTE_ADD_FORM))
                .andExpect(model().attributeExists("note"));
    }

    @Test
    void showFormForUpdateTest() throws Exception {
        //given
        when(service.findNoteById(anyInt())).thenReturn(note);
        //when
        mockMvc.perform(get("/notes/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name(NOTE_ADD_FORM))
                .andExpect(model().attributeExists("note"));
    }

    @Test
    void saveNote_validObjectTest() throws Exception {
        mockMvc.perform(post("/notes/save")
                .content(objectMapper.writeValueAsString(note)))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:list"));
    }

    @Test
    void deleteTest() throws Exception {
        mockMvc.perform(get("/notes/delete")
                .param("noteId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:list"));
    }

    @Test
    void showNoteTest() throws Exception {
        //given
        when(service.findNoteById(anyInt())).thenReturn(note);
        //when
        mockMvc.perform(get("/notes/1"))
                .andExpect(status().isOk());
        ModelAndView result = controller.showNote(1);
        //then
        assertFalse(result.isEmpty());
    }
}
