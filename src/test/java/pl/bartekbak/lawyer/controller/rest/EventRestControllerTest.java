package pl.bartekbak.lawyer.controller.rest;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.bartekbak.lawyer.commons.ModelProvider;
import pl.bartekbak.lawyer.dto.EventDTO;
import pl.bartekbak.lawyer.service.jooq.EventServiceJooq;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventRestController.class)
@ExtendWith(MockitoExtension.class)
class EventRestControllerTest {

    ModelProvider provider = new ModelProvider();

    @MockBean
    private EventServiceJooq eventService;

    @Autowired
    private MockMvc mockMvc;

    private final EventDTO firstEvent = provider.getFirstEvent();

    private final List<EventDTO> events = provider.getEvents();

    @Test
    void getAllEvents_shouldReturnEvents() throws Exception {
        when(eventService.findAllEvents()).thenReturn(events);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/events")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.is(3)))
                .andExpect(jsonPath("$[0].description").value("description"))
                .andReturn();
    }

    @Test
    void getEvent_shouldReturnFirstEvent() throws Exception {
        when(eventService.findEventById(any())).thenReturn(firstEvent);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/events/" + UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("description"))
                .andExpect(jsonPath("$.title").value("FirstEvent"))
                .andReturn();
    }

    @Test
    void addEvents_shouldInvokePostSaveEventOnce() throws Exception {
        doNothing().when(eventService).saveEvent(any(EventDTO.class));

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"FirstEvent\", \"description\": \"description\"}"))
                        .andExpect(status().isOk());
        verify(eventService).saveEvent(any(EventDTO.class));
    }

    @Test
    void updateEvent_shouldInvokePutSaveEventOnce() throws Exception{
        doNothing().when(eventService).saveEvent(any(EventDTO.class));

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"FirstEvent\", \"description\": \"description\"}"))
                .andExpect(status().isOk());
        verify(eventService).saveEvent(any(EventDTO.class));
    }

    @Test
    void deleteEvent_shouldInvokeDeleteEventByIdOnce() throws Exception {
        //given
        doNothing().when(eventService).deleteEventById(any());
        when(eventService.findEventById(any())).thenReturn(firstEvent);

        //when
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/events/" + UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(eventService, times(1)).deleteEventById(any());
    }
}
