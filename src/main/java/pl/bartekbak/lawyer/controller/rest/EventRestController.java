package pl.bartekbak.lawyer.controller.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bartekbak.lawyer.dto.EventDTO;
import pl.bartekbak.lawyer.exceptions.ResourceNotFoundException;
import pl.bartekbak.lawyer.service.jooq.EventServiceJooq;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/events")
public class EventRestController {

    private final EventServiceJooq service;

    public EventRestController(EventServiceJooq service) {
        this.service = service;
    }

    @GetMapping
    public List<EventDTO> getAllEvents() {
        return service.findAllEvents();
    }

    @GetMapping("/{eventId}")
    public EventDTO getEvent(@PathVariable UUID eventId) {
        EventDTO event = service.findEventById(eventId);
        if (event == null) {
            throw new ResourceNotFoundException("No such Id in database");
        }
        return event;
    }

    @PostMapping
    public EventDTO addEvent(@RequestBody EventDTO event) {
        event.setEventId(UUID.randomUUID());
        service.saveEvent(event);
        return event;
    }

    @PutMapping
    public EventDTO updateEvent(@RequestBody EventDTO event) {
        service.saveEvent(event);
        return event;
    }

    @DeleteMapping("/{eventId}")
    public String deleteEvent(@PathVariable UUID eventId) {
        EventDTO event = service.findEventById(eventId);
        if (event == null) {
            throw new ResourceNotFoundException("No such Id in database");
        }
        service.deleteEventById(eventId);
        return "Event successfully deleted";
    }

}
