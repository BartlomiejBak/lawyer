package pl.bartekbak.lawyer.service.spring.data;

import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dao.EventRepository;
import pl.bartekbak.lawyer.dto.EventDTO;
import pl.bartekbak.lawyer.entity.Event;
import pl.bartekbak.lawyer.service.EventService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceSpringData implements EventService {

    EventRepository eventRepository;

    public EventServiceSpringData(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<EventDTO> findAllEvents() {
        return eventRepository.findAllByOrderByDateTimeAsc()
                .stream()
                .map(Event::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventDTO findEventById(int id) {
        Optional<Event> result = eventRepository.findById(id);
        EventDTO event;
        if (result.isPresent()) {
            event = result.get().toDto();
        } else {
            throw new RuntimeException("Event id not found");
        }
        return event;
    }

    @Override
    public void saveEvent(EventDTO event) {
        eventRepository.save(Event.fromDto(event));
    }

    @Override
    public void deleteEventById(int id) {
        eventRepository.deleteById(id);
    }
}
