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
import pl.bartekbak.lawyer.dto.NoteDTO;
import pl.bartekbak.lawyer.service.spring.data.NoteServiceSpringData;

import java.util.List;

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

    private final NoteDTO firstNote = new NoteDTO(100,"1 note", "1000000");
    private final NoteDTO secondNote = new NoteDTO(101,"2 note", "1000001");
    private final NoteDTO thirdNote = new NoteDTO(102,"3 note", "1000002");
    private final List<NoteDTO> notes = List.of(firstNote, secondNote, thirdNote);

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private NoteRestController noteRestController;

    @Mock
    private NoteServiceSpringData noteService;

    @BeforeEach
    void setUp() {
        noteRestController = new NoteRestController(noteService);
        final StandaloneMockMvcBuilder mvcBuilder = MockMvcBuilders.standaloneSetup(noteRestController);
        mockMvc = mvcBuilder.build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllNotes_shouldReturnNotes() throws Exception {
        //given
        when(noteService.findAllNotes()).thenReturn(notes);
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/note/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final List<NoteDTO> result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
                });
        assertEquals(notes, result);
    }

    @Test
    void getNote_shouldReturnFirstNote() throws Exception {
        //given
        when(noteService.findNoteById(100)).thenReturn(firstNote);
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/note/id/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final NoteDTO result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
                });
        assertEquals(firstNote, result);
    }

    @Test
    void addNote_shouldInvokePostSaveNoteOnce() throws Exception {
        //given
        doNothing().when(noteService).saveNote(any(NoteDTO.class));
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/note/register")
                        .content(objectMapper.writeValueAsString(firstNote))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(noteService, times(1)).saveNote(any(NoteDTO.class));
    }

    @Test
    void updateNote_shouldInvokePutSaveNoteOnce() throws Exception {
        //given
        doNothing().when(noteService).saveNote(any(NoteDTO.class));
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/note/register")
                        .content(objectMapper.writeValueAsString(firstNote))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(noteService, times(1)).saveNote(any(NoteDTO.class));
    }

    @Test
    void deleteNote_shouldInvokeDeleteNoteByIdOnce() throws Exception {
        //given
        doNothing().when(noteService).deleteNoteById(anyInt());
        when(noteService.findNoteById(anyInt())).thenReturn(firstNote);
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/note/remove/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(noteService, times(1)).deleteNoteById(anyInt());
    }
}
