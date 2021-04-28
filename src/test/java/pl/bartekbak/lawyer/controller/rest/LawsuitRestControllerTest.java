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
import pl.bartekbak.lawyer.dto.LawsuitDTO;
import pl.bartekbak.lawyer.service.spring.data.LawsuitServiceSpringData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class LawsuitRestControllerTest {

    private final LawsuitDTO firstLawsuit = LawsuitDTO.builder()
            .lawsuitId(100)
            .name("1 lawsuit")
            .build();
    private final LawsuitDTO secondLawsuit = LawsuitDTO.builder()
            .lawsuitId(101)
            .name("2 lawsuit")
            .build();
    private final LawsuitDTO thirdLawsuit = LawsuitDTO.builder()
            .lawsuitId(102)
            .name("3 lawsuit")
            .build();
    private final List<LawsuitDTO> lawsuits = List.of(firstLawsuit, secondLawsuit, thirdLawsuit);

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private LawsuitServiceSpringData lawsuitService;

    @BeforeEach
    void setUp() {
        LawsuitRestController lawsuitRestController = new LawsuitRestController(lawsuitService);
        final StandaloneMockMvcBuilder mvcBuilder = MockMvcBuilders.standaloneSetup(lawsuitRestController);
        mockMvc = mvcBuilder.build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllLawsuits_shouldReturnLawsuits() throws Exception {
        //given
        when(lawsuitService.findAllLawsuits()).thenReturn(lawsuits);
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/lawsuit/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final List<LawsuitDTO> result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
                });
        assertEquals(lawsuits, result);
    }

    @Test
    void getLawsuit_shouldReturnFirstLawsuit() throws Exception {
        //given
        when(lawsuitService.findLawsuitById(100)).thenReturn(firstLawsuit);
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/lawsuit/id/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final LawsuitDTO result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
                });
        assertEquals(firstLawsuit, result);
    }

    @Test
    void addLawsuit_shouldInvokePostSaveLawsuitOnce() throws Exception {
        //given
        doNothing().when(lawsuitService).saveLawsuit(any(LawsuitDTO.class));
        //when
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/lawsuit/register")
                        .content(objectMapper.writeValueAsString(firstLawsuit))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(lawsuitService, times(1)).saveLawsuit(any(LawsuitDTO.class));
    }

    @Test
    void updateLawsuit_shouldInvokePutSaveLawsuitOnce() throws Exception {
        //given
        doNothing().when(lawsuitService).saveLawsuit(any(LawsuitDTO.class));
        //when
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/lawsuit/register")
                        .content(objectMapper.writeValueAsString(firstLawsuit))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(lawsuitService, times(1)).saveLawsuit(any(LawsuitDTO.class));
    }

    @Test
    void deleteLawsuit_shouldInvokeDeleteLawsuitByIdOnce() throws Exception {
        //given
        doNothing().when(lawsuitService).deleteLawsuitById(anyInt());
        when(lawsuitService.findLawsuitById(anyInt())).thenReturn(firstLawsuit);
        //when
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/lawsuit/remove/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(lawsuitService, times(1)).deleteLawsuitById(anyInt());
    }
}
