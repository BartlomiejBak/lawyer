package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.entity.Event;

import java.util.List;

public interface EventService {

    List<Event> findAllEvents();

    Event findEventById(int id);

    void saveEvent(Event event);

    void deleteEventById(int id);
}
