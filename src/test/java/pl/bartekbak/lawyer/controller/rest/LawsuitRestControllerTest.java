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
import pl.bartekbak.lawyer.entity.Court;
import pl.bartekbak.lawyer.service.spring.data.CourtServiceSpringData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class LawsuitRestControllerTest {

    private final Court firstCourt = Court.builder()
            .courtId(100)
            .name("court")
            .build();
    private final Court secondCourt = Court.builder()
            .courtId(101)
            .name("court")
            .build();
    private final Court thirdCourt = Court.builder()
            .courtId(102)
            .name("court")
            .build();
    private final List<Court> courts = List.of(firstCourt, secondCourt, thirdCourt);

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private CourtRestController courtRestController;

    @Mock
    private CourtServiceSpringData courtService;

    @BeforeEach
    void setUp() {
        courtRestController = new CourtRestController(courtService);
        final StandaloneMockMvcBuilder mvcBuilder = MockMvcBuilders.standaloneSetup(courtRestController);
        mockMvc = mvcBuilder.build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllLawsuits() throws Exception {
        //given
        when(courtService.findAllCourts()).thenReturn(courts);
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/court/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final List<Court> result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<List<Court>>() {
                });
        assertEquals(courts, result);
    }

    @Test
    void getLawsuit() throws Exception {
        //given
        when(courtService.findCourtById(100)).thenReturn(firstCourt);
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/court/id/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final Court result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<Court>() {
                });
        assertEquals(firstCourt, result);
    }

    @Test
    void addLawsuit() throws Exception {
        //given
        doNothing().when(courtService).saveCourt(any(Court.class));
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/court/register")
                        .content(objectMapper.writeValueAsString(firstCourt))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(courtService, times(1)).saveCourt(any(Court.class));
    }

    @Test
    void updateLawsuit() throws Exception {
        //given
        doNothing().when(courtService).saveCourt(any(Court.class));
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/court/register")
                        .content(objectMapper.writeValueAsString(firstCourt))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(courtService, times(1)).saveCourt(any(Court.class));
    }

    @Test
    void deleteLawsuit() throws Exception {
        //given
        doNothing().when(courtService).deleteCourtById(anyInt());
        when(courtService.findCourtById(anyInt())).thenReturn(firstCourt);
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/court/remove/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(courtService, times(1)).deleteCourtById(anyInt());
    }
}
