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
import pl.bartekbak.lawyer.service.AddressService;

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
class AddressControllerTest {

    @Mock
    AddressService service;
    @InjectMocks
    AddressController controller;

    ModelProvider provider = new ModelProvider();
    ObjectMapper objectMapper;
    MockMvc mockMvc;
    private static final String ADDRESS_ADD_FORM = "addresses/add-address-form";
    AddressDTO address;

    @BeforeEach
    void setUp() {
        address = provider.getFirstAddress();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void listAddressesTest() throws Exception {
        mockMvc.perform(get("/addresses/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("addresses/list-addresses"))
                .andExpect(model().attributeExists("addresses"));
    }

    @Test
    void showFormForAddTest() throws Exception {
        mockMvc.perform(get("/addresses/showFormForAdd"))
                .andExpect(status().isOk())
                .andExpect(view().name(ADDRESS_ADD_FORM))
                .andExpect(model().attributeExists("address"));
    }

    @Test
    void showFormForUpdateTest() throws Exception {
        // given
        when(service.findAddressById(any())).thenReturn(address);

        // when
        mockMvc.perform(get("/addresses/" + UUID.randomUUID() + "/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name(ADDRESS_ADD_FORM))
                .andExpect(model().attributeExists("address"));
    }

    @Test
    void saveAddress_validObjectTest() throws Exception {
        mockMvc.perform(post("/addresses/save")
                    .content(objectMapper.writeValueAsString(address)))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:list"));
    }

    @Test
    void deleteTest() throws Exception {
        mockMvc.perform(get("/addresses/delete")
                    .param("addressId", UUID.randomUUID().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:list"));
    }

    @Test
    void showAddressTest() throws Exception {
        // given
        when(service.findAddressById(any())).thenReturn(address);

        // when
        mockMvc.perform(get("/addresses/" + provider.getFirstAddress().getAddressId()))
                .andExpect(status().isOk());
        ModelAndView result = controller.showAddress(address.getAddressId());

        // then
        assertFalse(result.isEmpty());
    }
}
