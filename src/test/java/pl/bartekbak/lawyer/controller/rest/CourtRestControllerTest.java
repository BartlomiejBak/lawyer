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
import pl.bartekbak.lawyer.dto.CourtDTO;
import pl.bartekbak.lawyer.service.jooq.CourtServiceJooq;

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
class CourtRestControllerTest {

    ModelProvider provider = new ModelProvider();

    private final CourtDTO firstCourt = provider.getFirstCourt();

    private final List<CourtDTO> courts = provider.getCourts();

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private CourtRestController courtRestController;

    @Mock
    private CourtServiceJooq courtService;

    @BeforeEach
    void setUp() {
        courtRestController = new CourtRestController(courtService);
        final StandaloneMockMvcBuilder mvcBuilder = MockMvcBuilders.standaloneSetup(courtRestController);
        mockMvc = mvcBuilder.build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllCourts_shouldReturnCourts() throws Exception {
        //given
        when(courtService.findAllCourts()).thenReturn(courts);
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/courts")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final List<CourtDTO> result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
                });
        assertEquals(courts, result);
    }

    @Test
    void getCourt_shouldReturnFirstCourt() throws Exception {
        //given
        when(courtService.findCourtById(100)).thenReturn(firstCourt);
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/courts/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final CourtDTO result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
                });
        assertEquals(firstCourt, result);
    }

    @Test
    void addCourt_shouldInvokePostSaveCourtOnce() throws Exception {
        //given
        doNothing().when(courtService).saveCourt(any(CourtDTO.class));
        //when
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/courts")
                        .content(objectMapper.writeValueAsString(firstCourt))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(courtService, times(1)).saveCourt(any(CourtDTO.class));
    }

    @Test
    void updateCourt_shouldInvokePutSaveCourtOnce() throws Exception {
        //given
        doNothing().when(courtService).saveCourt(any(CourtDTO.class));
        //when
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/courts")
                        .content(objectMapper.writeValueAsString(firstCourt))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(courtService, times(1)).saveCourt(any(CourtDTO.class));
    }

    @Test
    void deleteCourt_shouldInvokeDeleteCourtByIdOnce() throws Exception {
        //given
        doNothing().when(courtService).deleteCourtById(anyInt());
        when(courtService.findCourtById(anyInt())).thenReturn(firstCourt);
        //when
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/courts/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(courtService, times(1)).deleteCourtById(anyInt());
    }
}
