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
import pl.bartekbak.lawyer.dto.AddressDTO;
import pl.bartekbak.lawyer.entity.Address;
import pl.bartekbak.lawyer.service.spring.data.AddressServiceSpringData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AddressRestControllerTest {

    private final AddressDTO firstAddress = AddressDTO.builder()
            .addressId(100)
            .city("Warsaw")
            .country("Poland")
            .build();
    private final AddressDTO secondAddress = AddressDTO.builder()
            .addressId(101)
            .city("Berlin")
            .country("Germany")
            .build();
    private final AddressDTO thirdAddress = AddressDTO.builder()
            .addressId(102)
            .city("Madrid")
            .country("Spain")
            .build();

    private final List<AddressDTO> addresses = List.of(firstAddress, secondAddress, thirdAddress);

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private AddressRestController addressRestController;

    @Mock
    private AddressServiceSpringData addressService;

    @BeforeEach
    void setUp() {
        addressRestController = new AddressRestController(addressService);
        final StandaloneMockMvcBuilder mvcBuilder = MockMvcBuilders.standaloneSetup(addressRestController);
        mockMvc = mvcBuilder.build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllAddresses_shouldReturnAddresses() throws Exception {
        //given
        when(addressService.findAllAddresses()).thenReturn(addresses);

        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/address/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final List<AddressDTO> result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
                });
        assertEquals(addresses, result);
    }

    @Test
    void getAddress_shouldReturnFirstAddress() throws Exception {
        //given
        when(addressService.findAddressById(100)).thenReturn(firstAddress);

        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/address/id/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final AddressDTO result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
                });
        assertEquals(firstAddress, result);
    }

    @Test
    void addAddress_shouldInvokePostSaveAddressOnce() throws Exception {
        //given
        doNothing().when(addressService).saveAddress(any(AddressDTO.class));
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/address/register")
                        .content(objectMapper.writeValueAsString(firstAddress))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(addressService, times(1)).saveAddress(any(AddressDTO.class));
    }

    @Test
    void updateAddress_shouldInvokePutSaveAddressOnce() throws Exception{
        //given
        doNothing().when(addressService).saveAddress(any(AddressDTO.class));
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/address/register")
                        .content(objectMapper.writeValueAsString(firstAddress))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(addressService, times(1)).saveAddress(any(AddressDTO.class));
    }

    @Test
    void deleteAddress_shouldInvokeDeleteAddressByIdOnce() throws Exception {
        //given
        doNothing().when(addressService).deleteAddressById(anyInt());
        when(addressService.findAddressById(anyInt())).thenReturn(firstAddress);

        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/address/remove/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(addressService, times(1)).deleteAddressById(anyInt());
    }
}
