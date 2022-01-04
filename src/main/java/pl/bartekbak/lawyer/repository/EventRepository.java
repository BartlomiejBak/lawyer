package pl.bartekbak.lawyer.repository;

import pl.bartekbak.lawyer.entity.Event;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventRepository {

    List<Event> list();
    Optional<Event> eventById(UUID id);
    void add(Event event);
    void update(Event event);
    void deleteById(UUID id);
}
