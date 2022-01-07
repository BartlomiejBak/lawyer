package pl.bartekbak.lawyer.controller.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import pl.bartekbak.lawyer.commons.ModelProvider;
import pl.bartekbak.lawyer.dto.NoteDTO;
import pl.bartekbak.lawyer.service.jooq.NoteServiceJooq;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class NoteRestControllerTest {

    ModelProvider provider = new ModelProvider();

    private final NoteDTO firstNote = provider.getFirstNote();
    private final List<NoteDTO> notes = provider.getNotes();

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private NoteServiceJooq noteService;

    @BeforeEach
    void setUp() {
        NoteRestController noteRestController = new NoteRestController(noteService);
        final StandaloneMockMvcBuilder mvcBuilder = MockMvcBuilders.standaloneSetup(noteRestController);
        mockMvc = mvcBuilder.build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllNotes_shouldReturnNotes() throws Exception {
        // given
        when(noteService.findAllNotes()).thenReturn(notes);

        // when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/notes")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // then
        final List<NoteDTO> result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
                });
        assertEquals(notes, result);
    }

    @Test
    void getNote_shouldReturnFirstNote() throws Exception {
        // given
        when(noteService.findNoteById(any())).thenReturn(firstNote);

        // when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/notes/" + UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // then
        final NoteDTO result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
                });
        assertEquals(firstNote, result);
    }

    @Test
    void addNote_shouldInvokePostSaveNoteOnce() throws Exception {
        // given
        doNothing().when(noteService).saveNote(any(NoteDTO.class));

        // when
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/notes")
                        .content(objectMapper.writeValueAsString(firstNote))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // then
        verify(noteService, times(1)).saveNote(any(NoteDTO.class));
    }

    @Test
    void updateNote_shouldInvokePutSaveNoteOnce() throws Exception {
        // given
        doNothing().when(noteService).saveNote(any(NoteDTO.class));

        // when
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/notes")
                        .content(objectMapper.writeValueAsString(firstNote))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // then
        verify(noteService, times(1)).saveNote(any(NoteDTO.class));
    }

    @Test
    void deleteNote_shouldInvokeDeleteNoteByIdOnce() throws Exception {
        // given
        doNothing().when(noteService).deleteNoteById(any());
        when(noteService.findNoteById(any())).thenReturn(firstNote);

        // when
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/notes/" + UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // then
        verify(noteService, times(1)).deleteNoteById(any());
    }
}
