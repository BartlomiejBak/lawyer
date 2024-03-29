package pl.bartekbak.lawyer.controller.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.bartekbak.lawyer.commons.LocalDateMapper;
import pl.bartekbak.lawyer.commons.ModelProvider;
import pl.bartekbak.lawyer.dto.PoaDTO;
import pl.bartekbak.lawyer.service.jooq.PoaServiceJooq;

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

@WebMvcTest(PoaRestController.class)
@ExtendWith(MockitoExtension.class)
class PoaRestControllerTest {

    ModelProvider provider = new ModelProvider();

    @MockBean
    private PoaServiceJooq poaService;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = LocalDateMapper.builder().build().getMapper();
    
    private final PoaDTO poa = provider.getFirstPoa();
    
    List<PoaDTO> poaList = provider.getPoaList();

    @Test
    void getAllPoas_shouldReturnPoas() throws Exception {
        // given
        when(poaService.findAllPoa()).thenReturn(poaList);

        // when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/poas")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // then
        final List<PoaDTO> result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
                });
        assertEquals(poaList, result);
    }

    @Test
    void getPoa_shouldReturnPoa() throws Exception {
        // given
        when(poaService.findPoaById(any())).thenReturn(poa);

        // when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/poas/" + UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // then
        final PoaDTO result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
                });
        assertEquals(poa, result);
    }

    @Test
    void addPoa_shouldInvokePostSavePoaOnce() throws Exception {
        // given
        doNothing().when(poaService).savePoa(any(PoaDTO.class));

        // when
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/poas")
                        .content(objectMapper.writeValueAsString(poa))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // then
        verify(poaService, times(1)).savePoa(any(PoaDTO.class));
    }

    @Test
    void updatePoa_shouldInvokePutPoaOnce() throws Exception {
        // given
        doNothing().when(poaService).savePoa(any(PoaDTO.class));

        // when
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/poas")
                        .content(objectMapper.writeValueAsString(poa))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // then
        verify(poaService, times(1)).savePoa(any(PoaDTO.class));
    }

    @Test
    void deletePoa_shouldInvokeDeletePoaByIdOnce() throws Exception {
        // given
        doNothing().when(poaService).deletePoaById(any());
        when(poaService.findPoaById(any())).thenReturn(poa);

        // when
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/poas/" + UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // then
        verify(poaService, times(1)).deletePoaById(any());
    }


}
