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
import pl.bartekbak.lawyer.entity.Poa;
import pl.bartekbak.lawyer.service.PoaService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class PoaControllerTest {

    @Mock
    PoaService service;
    @InjectMocks
    PoaController controller;

    ObjectMapper objectMapper;
    MockMvc mockMvc;
    private static final String POA_ADD_FORM = "poas/add-poa-form";
    Poa poa;

    @BeforeEach
    void setUp() {
        poa = Poa.builder().poaId(1).build();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void listPoasTest() throws Exception {
        mockMvc.perform(get("/poas/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("poas/list-poas"))
                .andExpect(model().attributeExists("poas"));
    }

    @Test
    void showFormForAddTest() throws Exception {
        mockMvc.perform(get("/poas/showFormForAdd"))
                .andExpect(status().isOk())
                .andExpect(view().name(POA_ADD_FORM))
                .andExpect(model().attributeExists("poa"));
    }

    @Test
    void showFormForUpdateTest() throws Exception {
        //given
        when(service.findPoaById(anyInt())).thenReturn(poa);
        //when
        mockMvc.perform(get("/poas/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name(POA_ADD_FORM))
                .andExpect(model().attributeExists("poa"));
    }

    @Test
    void savePoa_validObjectTest() throws Exception {
        mockMvc.perform(post("/poas/save")
                .content(objectMapper.writeValueAsString(poa)))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:list"));
    }

    @Test
    void deleteTest() throws Exception {
        mockMvc.perform(get("/poas/delete")
                .param("poaId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:list"));
    }

    @Test
    void showPoaTest() throws Exception {
        //given
        when(service.findPoaById(anyInt())).thenReturn(poa);
        //when
        mockMvc.perform(get("/poas/1"))
                .andExpect(status().isOk());
        ModelAndView result = controller.showPoa(1);
        //then
        assertFalse(result.isEmpty());
    }
}
