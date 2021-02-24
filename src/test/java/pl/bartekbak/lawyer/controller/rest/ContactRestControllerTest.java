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
import pl.bartekbak.lawyer.entity.Contact;
import pl.bartekbak.lawyer.service.spring.data.ContactServiceSpringData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ContactRestControllerTest {

    private final Contact firstContact = Contact.builder()
            .contactId(100)
            .firstName("First")
            .lastName("Contact")
            .build();
    private final Contact secondContact = Contact.builder()
            .contactId(101)
            .firstName("Second")
            .lastName("Contact")
            .build();
    private final Contact thirdContact = Contact.builder()
            .contactId(102)
            .firstName("Third")
            .lastName("Contact")
            .build();

    private final List<Contact> contacts = List.of(firstContact, secondContact, thirdContact);

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private ContactServiceSpringData contactService;

    @BeforeEach
    void setUp() {
        ContactRestController contactRestController = new ContactRestController(contactService);
        final StandaloneMockMvcBuilder mvcBuilder = MockMvcBuilders.standaloneSetup(contactRestController);
        mockMvc = mvcBuilder.build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllContacts_shouldReturnContacts() throws Exception {
        //given
        when(contactService.findAllContacts()).thenReturn(contacts);
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/contact/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final List<Contact> result = objectMapper
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
                        .get("/api/contact/id/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final Contact result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
                });
        assertEquals(firstContact, result);
    }

    @Test
    void addContact_shouldInvokePostSaveContactOnce() throws Exception {
        //given
        doNothing().when(contactService).saveContact(any(Contact.class));
        //when
         mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/contact/register")
                        .content(objectMapper.writeValueAsString(firstContact))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(contactService, times(1)).saveContact(any(Contact.class));
    }

    @Test
    void updateContact_shouldInvokePutContactOnce() throws Exception {
        //given
        doNothing().when(contactService).saveContact(any(Contact.class));
        //when
         mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/contact/register")
                        .content(objectMapper.writeValueAsString(firstContact))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(contactService, times(1)).saveContact(any(Contact.class));
    }

    @Test
    void deleteContact_shouldInvokeDeleteContactByIdOnce() throws Exception {
        //given
        doNothing().when(contactService).deleteContactById(anyInt());
        when(contactService.findContactById(anyInt())).thenReturn(firstContact);
        //when

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/contact/remove/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(contactService, times(1)).deleteContactById(anyInt());
    }
}
