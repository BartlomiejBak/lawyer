package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.dto.EventDTO;

import java.util.List;
import java.util.UUID;

public interface EventService {
    List<EventDTO> findAllEvents();

    EventDTO findEventById(UUID id);

    void saveEvent(EventDTO event);

    void deleteEventById(UUID id);
}
