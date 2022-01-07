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
import pl.bartekbak.lawyer.dto.LawsuitDTO;
import pl.bartekbak.lawyer.entity.Lawsuit;
import pl.bartekbak.lawyer.service.LawsuitService;

import java.time.LocalDate;
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
class LawsuitControllerTest {

    @Mock
    LawsuitService service;
    @InjectMocks
    LawsuitController controller;

    ModelProvider provider = new ModelProvider();
    ObjectMapper objectMapper;
    MockMvc mockMvc;
    private static final String LAWSUIT_ADD_FORM = "lawsuits/add-lawsuit-form";
    LawsuitDTO lawsuit;

    @BeforeEach
    void setUp() {
        lawsuit = provider.getFirstLawsuit();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void listLawsuitsTest() throws Exception {
        mockMvc.perform(get("/lawsuits/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("lawsuits/list-lawsuits"))
                .andExpect(model().attributeExists("lawsuits"));
    }

    @Test
    void showFormForAddTest() throws Exception {
        mockMvc.perform(get("/lawsuits/showFormForAdd"))
                .andExpect(status().isOk())
                .andExpect(view().name(LAWSUIT_ADD_FORM))
                .andExpect(model().attributeExists("lawsuit"));
    }

    @Test
    void showFormForUpdateTest() throws Exception {
        // given
        when(service.findLawsuitById(any())).thenReturn(lawsuit);

        // when
        mockMvc.perform(get("/lawsuits/" + UUID.randomUUID() + "/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name(LAWSUIT_ADD_FORM))
                .andExpect(model().attributeExists("lawsuit"));
    }

    @Test
    void saveLawsuit_validObjectTest() throws Exception {
        // given
        Lawsuit firstLawsuit = Lawsuit.builder()
                .name("lawsuit no. 1223")
                .caseSide("defendant")
                .claimAmount(25000.2)
                .signature("ABC 123456")
                .deadline(LocalDate.of(2021, 5, 10))
                .additionalInfo("no important info")
                .inputDate(LocalDate.now())
                .build();

        // when
        mockMvc.perform(post("/lawsuits/save")
                .content(objectMapper.writeValueAsString(firstLawsuit)))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:list"));
    }

    @Test
    void deleteTest() throws Exception {
        mockMvc.perform(get("/lawsuits/delete")
                .param("lawsuitId", UUID.randomUUID().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:list"));
    }

    @Test
    void showLawsuitTest() throws Exception {
        // given
        when(service.findLawsuitById(any())).thenReturn(lawsuit);

        // when
        mockMvc.perform(get("/lawsuits/" + UUID.randomUUID()))
                .andExpect(status().isOk());
        ModelAndView result = controller.showLawsuit(lawsuit.getLawsuitId());

        // then
        assertFalse(result.isEmpty());
    }
}
