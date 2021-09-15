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
import pl.bartekbak.lawyer.commons.LocalDateMapper;
import pl.bartekbak.lawyer.commons.ModelProvider;
import pl.bartekbak.lawyer.dto.ContactDTO;
import pl.bartekbak.lawyer.service.jooq.ContactServiceJooq;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ContactRestControllerTest {

    ModelProvider provider = new ModelProvider();

    private final ContactDTO firstContact = provider.getFirstContact();

    private final List<ContactDTO> contacts = provider.getContacts();

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private ContactServiceJooq contactService;

    @BeforeEach
    void setUp() {
        ContactRestController contactRestController = new ContactRestController(contactService);
        final StandaloneMockMvcBuilder mvcBuilder = MockMvcBuilders.standaloneSetup(contactRestController);
        mockMvc = mvcBuilder.build();
        objectMapper = LocalDateMapper.builder().build().getMapper();
    }

    @Test
    void getAllContacts_shouldReturnContacts() throws Exception {
        //given
        when(contactService.findAllContacts()).thenReturn(contacts);
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/contacts")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final List<ContactDTO> result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
                });
        assertEquals(contacts, result);
    }

    @Test
    void getContact_shouldReturnFirstContact() throws Exception {
        //given
        when(contactService.findContactById(100)).thenReturn(firstContact);
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/contacts/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final ContactDTO result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
                });
        assertEquals(firstContact, result);
    }

    @Test
    void addContact_shouldInvokePostSaveContactOnce() throws Exception {
        //given
        doNothing().when(contactService).saveContact(any(ContactDTO.class));
        //when
         mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/contacts")
                        .content(objectMapper.writeValueAsString(firstContact))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(contactService, times(1)).saveContact(any(ContactDTO.class));
    }

    @Test
    void updateContact_shouldInvokePutContactOnce() throws Exception {
        //given
        doNothing().when(contactService).saveContact(any(ContactDTO.class));
        //when
         mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/contacts")
                        .content(objectMapper.writeValueAsString(firstContact))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(contactService, times(1)).saveContact(any(ContactDTO.class));
    }

    @Test
    void deleteContact_shouldInvokeDeleteContactByIdOnce() throws Exception {
        //given
        doNothing().when(contactService).deleteContactById(anyInt());
        when(contactService.findContactById(anyInt())).thenReturn(firstContact);
        //when

        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/contacts/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(contactService, times(1)).deleteContactById(anyInt());
    }
}
