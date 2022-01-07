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
import pl.bartekbak.lawyer.dto.PaymentDTO;
import pl.bartekbak.lawyer.service.PaymentService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class PaymentControllerTest {

    @Mock
    PaymentService service;
    @InjectMocks
    PaymentController controller;

    ModelProvider provider = new ModelProvider();
    ObjectMapper objectMapper;
    MockMvc mockMvc;
    private static final String PAYMENT_ADD_FORM = "payments/add-payment-form";
    PaymentDTO payment;

    @BeforeEach
    void setUp() {
        payment = provider.getFirstPayment();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void listPaymentsTest() throws Exception {
        mockMvc.perform(get("/payments/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("payments/list-payments"))
                .andExpect(model().attributeExists("payments"));
    }

    @Test
    void showFormForAddTest() throws Exception {
        mockMvc.perform(get("/payments/showFormForAdd"))
                .andExpect(status().isOk())
                .andExpect(view().name(PAYMENT_ADD_FORM))
                .andExpect(model().attributeExists("payment"));
    }

    @Test
    void showFormForUpdateTest() throws Exception {
        // given
        when(service.findPaymentById(any())).thenReturn(payment);

        // when
        mockMvc.perform(get("/payments/" + UUID.randomUUID() + "/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name(PAYMENT_ADD_FORM))
                .andExpect(model().attributeExists("payment"));
    }

    @Test
    void deleteTest() throws Exception {
        mockMvc.perform(get("/payments/delete")
                .param("paymentId", UUID.randomUUID().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:list"));
    }

    @Test
    void showPaymentTest() throws Exception {
        // given
        when(service.findPaymentById(any())).thenReturn(payment);

        // when
        mockMvc.perform(get("/payments/" + UUID.randomUUID()))
                .andExpect(status().isOk());
        ModelAndView result = controller.showPayment(payment.getPaymentId());

        // then
        assertFalse(result.isEmpty());
    }
}
