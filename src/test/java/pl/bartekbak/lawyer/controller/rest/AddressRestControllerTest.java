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
import pl.bartekbak.lawyer.entity.Address;
import pl.bartekbak.lawyer.service.spring.data.AddressServiceSpringData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AddressRestControllerTest {

    private final Address firstAddress = Address.builder()
            .id(100)
            .city("Warsaw")
            .country("Poland")
            .build();
    private final Address secondAddress = Address.builder()
            .id(101)
            .city("Berlin")
            .country("Germany")
            .build();
    private final Address thirdAddress = Address.builder()
            .id(102)
            .city("Madrid")
            .country("Spain")
            .build();

    private final List<Address> addresses = List.of(firstAddress, secondAddress, thirdAddress);

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
        final List<Address> result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<List<Address>>() {
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
        final Address result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<Address>() {
                });
        assertEquals(firstAddress, result);
    }

    @Test
    void addAddress_shouldInvokePostSaveAddressOnce() throws Exception {
        //given
        doNothing().when(addressService).saveAddress(any(Address.class));
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
        verify(addressService, times(1)).saveAddress(any(Address.class));
    }

    @Test
    void updateAddress_shouldInvokePutSaveAddressOnce() throws Exception{
        //given
        doNothing().when(addressService).saveAddress(any(Address.class));
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
        verify(addressService, times(1)).saveAddress(any(Address.class));
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
