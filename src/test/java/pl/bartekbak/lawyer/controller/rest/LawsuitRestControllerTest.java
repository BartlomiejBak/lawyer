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
import pl.bartekbak.lawyer.entity.Lawsuit;
import pl.bartekbak.lawyer.service.spring.data.LawsuitServiceSpringData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class LawsuitRestControllerTest {
    private final Lawsuit firstLawsuit = Lawsuit.builder()
            .id(100)
            .name("1 lawsuit")
            .build();
    private final Lawsuit secondLawsuit = Lawsuit.builder()
            .id(101)
            .name("2 lawsuit")
            .build();
    private final Lawsuit thirdLawsuit = Lawsuit.builder()
            .id(102)
            .name("3 lawsuit")
            .build();
    private final List<Lawsuit> lawsuits = List.of(firstLawsuit, secondLawsuit, thirdLawsuit);

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private LawsuitRestController lawsuitRestController;

    @Mock
    private LawsuitServiceSpringData lawsuitService;

    @BeforeEach
    void setUp() {
        lawsuitRestController = new LawsuitRestController(lawsuitService);
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
        final List<Lawsuit> result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<List<Lawsuit>>() {
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
        final Lawsuit result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<Lawsuit>() {
                });
        assertEquals(firstLawsuit, result);
    }

    @Test
    void addLawsuit_shouldInvokePostSaveLawsuitOnce() throws Exception {
        //given
        doNothing().when(lawsuitService).saveLawsuit(any(Lawsuit.class));
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/lawsuit/register")
                        .content(objectMapper.writeValueAsString(firstLawsuit))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(lawsuitService, times(1)).saveLawsuit(any(Lawsuit.class));
    }

    @Test
    void updateLawsuit_shouldInvokePutSaveLawsuitOnce() throws Exception {
        //given
        doNothing().when(lawsuitService).saveLawsuit(any(Lawsuit.class));
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/lawsuit/register")
                        .content(objectMapper.writeValueAsString(firstLawsuit))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(lawsuitService, times(1)).saveLawsuit(any(Lawsuit.class));
    }

    @Test
    void deleteLawsuit_shouldInvokeDeleteLawsuitByIdOnce() throws Exception {
        //given
        doNothing().when(lawsuitService).deleteLawsuitById(anyInt());
        when(lawsuitService.findLawsuitById(anyInt())).thenReturn(firstLawsuit);
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/lawsuit/remove/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(lawsuitService, times(1)).deleteLawsuitById(anyInt());
    }
}
