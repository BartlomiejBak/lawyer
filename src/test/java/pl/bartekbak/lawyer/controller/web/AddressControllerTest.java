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
import pl.bartekbak.lawyer.entity.Address;
import pl.bartekbak.lawyer.service.AddressService;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyInt;
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

    ObjectMapper objectMapper;
    MockMvc mockMvc;
    private static final String ADDRESS_ADD_FORM = "addresses/add-address-form";
    Address address;

    @BeforeEach
    void setUp() {
        address = Address.builder().id(1).build();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void listAddresses() throws Exception {
        mockMvc.perform(get("/addresses/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("addresses/list-addresses"))
                .andExpect(model().attributeExists("addresses"));

    }

    @Test
    void showFormForAdd() throws Exception {
        //when
        mockMvc.perform(get("/addresses/showFormForAdd"))
                .andExpect(status().isOk())
                .andExpect(view().name(ADDRESS_ADD_FORM))
                .andExpect(model().attributeExists("address"));
    }

    @Test
    void showFormForUpdate() throws Exception {
        //given
        when(service.findAddressById(anyInt())).thenReturn(address);
        //when
        mockMvc.perform(get("/addresses/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name(ADDRESS_ADD_FORM))
                .andExpect(model().attributeExists("address"));
    }

    @Test
    void saveAddress_validObject() throws Exception {
        mockMvc.perform(post("/addresses/save")
                    .content(objectMapper.writeValueAsString(address)))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:list"));
    }

    @Test
    void delete() throws Exception {
        //when
        mockMvc.perform(get("/addresses/delete")
                    .param("addressId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:list"));
    }

    @Test
    void showAddress() throws Exception {
        //given
        when(service.findAddressById(anyInt())).thenReturn(address);
        //when
        mockMvc.perform(get("/addresses/1"))
                .andExpect(status().isOk());
        ModelAndView result = controller.showAddress(1);
        //then
        assertFalse(result.isEmpty());
    }
}
