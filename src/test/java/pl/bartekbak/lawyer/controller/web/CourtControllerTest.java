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
import pl.bartekbak.lawyer.dto.AddressDTO;
import pl.bartekbak.lawyer.dto.CourtDTO;
import pl.bartekbak.lawyer.service.CourtService;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class CourtControllerTest {

    @Mock
    CourtService service;
    @InjectMocks
    CourtController controller;

    ModelProvider provider = new ModelProvider();
    ObjectMapper objectMapper;
    MockMvc mockMvc;
    private static final String COURT_ADD_FORM = "courts/add-court-form";
    CourtDTO court;

    @BeforeEach
    void setUp() {
        court = provider.getFirstCourt();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void listAllContactsTest() throws Exception {
        mockMvc.perform(get("/courts/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("courts/list-courts"))
                .andExpect(model().attributeExists("courts"));
    }

    @Test
    void showFormForAddTest() throws Exception {
        mockMvc.perform(get("/courts/showFormForAdd"))
                .andExpect(status().isOk())
                .andExpect(view().name(COURT_ADD_FORM))
                .andExpect(model().attributeExists("court"));
    }

    @Test
    void showFormForUpdateTest() throws Exception {
        //given
        when(service.findCourtById(anyInt())).thenReturn(court);
        //when
        mockMvc.perform(get("/courts/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name(COURT_ADD_FORM))
                .andExpect(model().attributeExists("court"));
    }

    @Test
    void saveCourtTest() throws Exception {
        mockMvc.perform(post("/courts/save")
                .content(objectMapper.writeValueAsString(court)))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:list"));
    }

    @Test
    void deleteTest() throws Exception {
        mockMvc.perform(get("/courts/delete")
                .param("courtId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:list"));
    }

    @Test
    void showCourtTest() throws Exception {
        //given
        when(service.findCourtById(anyInt())).thenReturn(court);
        //when
        mockMvc.perform(get("/courts/1"))
                .andExpect(status().isOk());
        ModelAndView result = controller.showCourt(1);
        //then
        assertFalse(result.isEmpty());
    }
}
