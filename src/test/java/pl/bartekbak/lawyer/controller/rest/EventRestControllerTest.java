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
import pl.bartekbak.lawyer.dto.EventDTO;
import pl.bartekbak.lawyer.service.spring.data.EventServiceSpringData;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventRestController.class)
@ExtendWith(MockitoExtension.class)
class EventRestControllerTest {

    @MockBean
    private EventServiceSpringData eventService;

    @Autowired
    private MockMvc mockMvc;

    private final EventDTO firstEvent = EventDTO.builder()
            .eventId(100)
            .title("FirstEvent")
            .dateTime(LocalDateTime.of(2021, 11, 11, 0 ,0))
            .description("description")
            .build();

    private final EventDTO secondEvent = EventDTO.builder()
            .eventId(101)
            .title("SecondEvent")
            .dateTime(LocalDateTime.of(2021, 11, 11, 0 ,0))
            .description("description")
            .build();

    private final EventDTO thirdEvent = EventDTO.builder()
            .eventId(102)
            .title("ThirdEvent")
            .dateTime(LocalDateTime.of(2021, 11, 11, 0 ,0))
            .description("description")
            .build();

    private final List<EventDTO> events = List.of(firstEvent, secondEvent, thirdEvent);

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
        when(eventService.findEventById(100)).thenReturn(firstEvent);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/events/100")
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
        doNothing().when(eventService).deleteEventById(anyInt());
        when(eventService.findEventById(anyInt())).thenReturn(firstEvent);

        //when
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/events/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(eventService, times(1)).deleteEventById(anyInt());
    }
}
