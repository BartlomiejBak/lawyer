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
import pl.bartekbak.lawyer.commons.ModelProvider;
import pl.bartekbak.lawyer.dto.PaymentDTO;
import pl.bartekbak.lawyer.service.spring.data.PaymentServiceSpringData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PaymentRestController.class)
@ExtendWith(MockitoExtension.class)
class PaymentRestControllerTest {

    ModelProvider provider = new ModelProvider();

    @MockBean
    private PaymentServiceSpringData paymentService;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();
    
    private final PaymentDTO firstPayment = provider.getFirstPayment();

    List<PaymentDTO> payments = provider.getPayments();

    @Test
    void getAllPayments_shouldReturnPayments() throws Exception {
        //given
        when(paymentService.findAllPayments()).thenReturn(payments);
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/payments")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final List<PaymentDTO> result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
                });
        assertEquals(payments, result);
    }

    @Test
    void getPayment_shouldReturnFirstPayment() throws Exception {
        //given
        when(paymentService.findPaymentById(100)).thenReturn(firstPayment);
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/payments/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final PaymentDTO result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
                });
        assertEquals(firstPayment, result);
    }

    @Test
    void addPayment_shouldInvokePostSavePaymentOnce() throws Exception {
        //given
        doNothing().when(paymentService).savePayment(any(PaymentDTO.class));
        //when
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/payments")
                        .content(objectMapper.writeValueAsString(firstPayment))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(paymentService, times(1)).savePayment(any(PaymentDTO.class));
    }

    @Test
    void updatePayment_shouldInvokePutPaymentOnce() throws Exception {
        //given
        doNothing().when(paymentService).savePayment(any(PaymentDTO.class));
        //when
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/payments")
                        .content(objectMapper.writeValueAsString(firstPayment))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(paymentService, times(1)).savePayment(any(PaymentDTO.class));
    }

    @Test
    void deletePayment_shouldInvokeDeletePaymentByIdOnce() throws Exception {
        //given
        doNothing().when(paymentService).deletePaymentById(anyInt());
        when(paymentService.findPaymentById(anyInt())).thenReturn(firstPayment);
        //when

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/payments/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(paymentService, times(1)).deletePaymentById(anyInt());
    }
}
