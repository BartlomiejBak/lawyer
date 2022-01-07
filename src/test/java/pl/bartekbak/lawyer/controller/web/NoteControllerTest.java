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
import pl.bartekbak.lawyer.commons.ModelProvider;
import pl.bartekbak.lawyer.dto.NoteDTO;
import pl.bartekbak.lawyer.service.NoteService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
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

    ModelProvider provider = new ModelProvider();
    ObjectMapper objectMapper;
    MockMvc mockMvc;
    private static final String NOTE_ADD_FORM = "notes/add-note-form";
    NoteDTO note;

    @BeforeEach
    void setUp() {
        note = provider.getFirstNote();
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
        // given
        when(service.findNoteById(any())).thenReturn(note);

        // when
        mockMvc.perform(get("/notes/" + UUID.randomUUID() + "/edit"))
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
                .param("noteId", UUID.randomUUID().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:list"));
    }

    @Test
    void showNoteTest() throws Exception {
        // given
        when(service.findNoteById(any())).thenReturn(note);

        // when
        mockMvc.perform(get("/notes/" + UUID.randomUUID()))
                .andExpect(status().isOk());
        ModelAndView result = controller.showNote(note.getNoteId());

        // then
        assertFalse(result.isEmpty());
    }
}
