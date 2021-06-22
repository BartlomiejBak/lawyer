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
import pl.bartekbak.lawyer.dto.EventDTO;
import pl.bartekbak.lawyer.service.EventService;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class EventControllerTest {

    @Mock
    EventService service;
    @InjectMocks
    EventController controller;

    ModelProvider provider = new ModelProvider();
    ObjectMapper objectMapper;
    MockMvc mockMvc;
    private static final String EVENT_ADD_FORM = "events/add-event-form";
    EventDTO event;

    @BeforeEach
    void setUp() {
        event = provider.getFirstEvent();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void listEventsTest() throws Exception {
        mockMvc.perform(get("/events/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("events/list-events"))
                .andExpect(model().attributeExists("events"));
    }

    @Test
    void showFormForAddTest() throws Exception {
        mockMvc.perform(get("/events/showFormForAdd"))
                .andExpect(status().isOk())
                .andExpect(view().name(EVENT_ADD_FORM))
                .andExpect(model().attributeExists("event"));
    }

    @Test
    void showFormForUpdateTest() throws Exception {
        //given
        when(service.findEventById(anyInt())).thenReturn(event);
        //when
        mockMvc.perform(get("/events/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name(EVENT_ADD_FORM))
                .andExpect(model().attributeExists("event"));
    }

    @Test
    void saveEvent_validObjectTest() throws Exception {
        mockMvc.perform(post("/events/save")
                .content(objectMapper.writeValueAsString(event)))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:list"));
    }

    @Test
    void deleteTest() throws Exception {
        mockMvc.perform(get("/events/delete")
                .param("eventId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:list"));
    }

    @Test
    void showEventTest() throws Exception {
        //given
        when(service.findEventById(anyInt())).thenReturn(event);
        //when
        mockMvc.perform(get("/events/1"))
                .andExpect(status().isOk());
        ModelAndView result = controller.showEvent(1);
        //then
        assertFalse(result.isEmpty());
    }
}
