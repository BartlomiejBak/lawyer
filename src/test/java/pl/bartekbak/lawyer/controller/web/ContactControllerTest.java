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
import pl.bartekbak.lawyer.entity.Contact;
import pl.bartekbak.lawyer.service.ContactService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class ContactControllerTest {
    
    @Mock
    ContactService service;
    @InjectMocks
    ContactController controller;
    
    ObjectMapper objectMapper;
    MockMvc mockMvc;
    private static final String CONTACT_ADD_FORM = "contacts/add-contact-form";
    Contact contact;

    @BeforeEach
    void setUp() {
        contact = Contact.builder().contactId(1).build();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void listAllContactsTest() throws Exception {
        mockMvc.perform(get("/contacts/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("contacts/list-contacts"))
                .andExpect(model().attributeExists("contacts"));
    }

    @Test
    void showFormForAddTest() throws Exception {
        mockMvc.perform(get("/contacts/showFormForAdd"))
                .andExpect(status().isOk())
                .andExpect(view().name(CONTACT_ADD_FORM))
                .andExpect(model().attributeExists("contact"));
    }

    @Test
    void showFormForUpdateTest() throws Exception {
        //given
        when(service.findContactById(anyInt())).thenReturn(contact);
        //when
        mockMvc.perform(get("/contacts/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name(CONTACT_ADD_FORM))
                .andExpect(model().attributeExists("contact"));
    }

    @Test
    void saveContactTest() throws Exception {
        mockMvc.perform(post("/contacts/save")
                .content(objectMapper.writeValueAsString(contact)))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:list"));
    }

    @Test
    void deleteTest() throws Exception {
        mockMvc.perform(get("/contacts/delete")
                .param("contactId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:list"));
    }

    @Test
    void showContactTest() throws Exception {
        //given
        when(service.findContactById(anyInt())).thenReturn(contact);
        //when
        mockMvc.perform(get("/contacts/1"))
                .andExpect(status().isOk());
        ModelAndView result = controller.showContact(1);
        //then
        assertFalse(result.isEmpty());
    }
}
