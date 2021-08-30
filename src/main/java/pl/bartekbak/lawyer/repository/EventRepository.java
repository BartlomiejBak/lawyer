package pl.bartekbak.lawyer.repository;

import pl.bartekbak.lawyer.entity.Event;

import java.util.List;
import java.util.Optional;

public interface EventRepository {

    List<Event> list();
    Optional<Event> eventById(int id);
    void add(Event event);
    void deleteById(int id);
}
