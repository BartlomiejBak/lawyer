package pl.bartekbak.lawyer.service.jooq;

import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dto.EventDTO;
import pl.bartekbak.lawyer.entity.Event;
import pl.bartekbak.lawyer.repository.EventRepository;
import pl.bartekbak.lawyer.service.EventService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventServiceJooq implements EventService {

    EventRepository eventRepository;

    public EventServiceJooq(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<EventDTO> findAllEvents() {
        return eventRepository.list()
                .stream()
                .map(Event::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventDTO findEventById(UUID id) {
        Optional<Event> result = eventRepository.eventById(id);
        EventDTO event;
        event = result.map(Event::toDto).orElse(null);
        return event;
    }

    @Override
    public void saveEvent(EventDTO event) {
        eventRepository.add(Event.fromDto(event));
    }

    @Override
    public void deleteEventById(UUID id) {
        eventRepository.deleteById(id);
    }
}
