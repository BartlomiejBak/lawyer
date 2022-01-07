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
import pl.bartekbak.lawyer.commons.ModelProvider;
import pl.bartekbak.lawyer.dto.AddressDTO;
import pl.bartekbak.lawyer.service.jooq.AddressServiceJooq;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AddressRestControllerTest {

    ModelProvider provider = new ModelProvider();

    private final AddressDTO firstAddress = provider.getFirstAddress();

    private final List<AddressDTO> addresses = provider.getAddresses();

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private AddressServiceJooq addressService;

    @BeforeEach
    void setUp() {
        AddressRestController addressRestController = new AddressRestController(addressService);
        final StandaloneMockMvcBuilder mvcBuilder = MockMvcBuilders.standaloneSetup(addressRestController);
        mockMvc = mvcBuilder.build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllAddresses_shouldReturnAddresses() throws Exception {
        // given
        when(addressService.findAllAddresses()).thenReturn(addresses);

        // when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/addresses")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // then
        final List<AddressDTO> result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
                });
        assertEquals(addresses, result);
    }

    @Test
    void getAddress_shouldReturnFirstAddress() throws Exception {
        // given
        when(addressService.findAddressById(any())).thenReturn(firstAddress);

        // when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/addresses/" + UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // then
        final AddressDTO result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
                });
        assertEquals(firstAddress, result);
    }

    @Test
    void addAddress_shouldInvokePostSaveAddressOnce() throws Exception {
        // given
        doNothing().when(addressService).saveAddress(any(AddressDTO.class));

        // when
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/addresses")
                        .content(objectMapper.writeValueAsString(firstAddress))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // then
        verify(addressService, times(1)).saveAddress(any(AddressDTO.class));
    }

    @Test
    void updateAddress_shouldInvokePutSaveAddressOnce() throws Exception{
        // given
        doNothing().when(addressService).saveAddress(any(AddressDTO.class));

        // when
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/addresses")
                        .content(objectMapper.writeValueAsString(firstAddress))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // then
        verify(addressService, times(1)).saveAddress(any(AddressDTO.class));
    }

    @Test
    void deleteAddress_shouldInvokeDeleteAddressByIdOnce() throws Exception {
        // given
        doNothing().when(addressService).deleteAddressById(any());
        when(addressService.findAddressById(any())).thenReturn(firstAddress);

        // when
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/addresses/" + UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // then
        verify(addressService, times(1)).deleteAddressById(any());
    }
}
