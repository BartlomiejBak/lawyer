package pl.bartekbak.lawyer.service.spring.data;

import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dao.EventRepository;
import pl.bartekbak.lawyer.entity.Event;
import pl.bartekbak.lawyer.service.EventService;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceSpringData implements EventService {

    EventRepository eventRepository;

    public EventServiceSpringData(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> findAllCourts() {
        return eventRepository.findAllByOrderByDateTimeAsc();
    }

    @Override
    public Event findEventById(int id) {
        Optional<Event> result = eventRepository.findById(id);
        Event event = null;
        if (result.isPresent()) {
            event = result.get();
        } else {
            throw new RuntimeException("Event id not found");
        }
        return event;
    }

    @Override
    public void saveEvent(Event event) {
        eventRepository.save(event);
    }

    @Override
    public void deleteEventById(int id) {
        eventRepository.deleteById(id);
    }
}
