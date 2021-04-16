package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.dto.EventDTO;

import java.util.List;

public interface EventService {
    List<EventDTO> findAllEvents();

    EventDTO findEventById(int id);

    void saveEvent(EventDTO event);

    void deleteEventById(int id);
}
