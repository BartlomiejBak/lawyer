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
import pl.bartekbak.lawyer.commons.LocalDateMapper;
import pl.bartekbak.lawyer.commons.ModelProvider;
import pl.bartekbak.lawyer.dto.LawsuitDTO;
import pl.bartekbak.lawyer.service.jooq.LawsuitServiceJooq;

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

    ModelProvider provider = new ModelProvider();

    private final LawsuitDTO firstLawsuit = provider.getFirstLawsuit();
    private final List<LawsuitDTO> lawsuits = provider.getLawsuits();

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private LawsuitServiceJooq lawsuitService;

    @BeforeEach
    void setUp() {
        LawsuitRestController lawsuitRestController = new LawsuitRestController(lawsuitService);
        final StandaloneMockMvcBuilder mvcBuilder = MockMvcBuilders.standaloneSetup(lawsuitRestController);
        mockMvc = mvcBuilder.build();
        objectMapper = LocalDateMapper.builder().build().getMapper();
    }

    @Test
    void getAllLawsuits_shouldReturnLawsuits() throws Exception {
        //given
        when(lawsuitService.findAllLawsuits()).thenReturn(lawsuits);
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/lawsuits")
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
                        .get("/api/lawsuits/100")
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
                        .post("/api/lawsuits")
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
                        .put("/api/lawsuits")
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
                        .delete("/api/lawsuits/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(lawsuitService, times(1)).deleteLawsuitById(anyInt());
    }
}
