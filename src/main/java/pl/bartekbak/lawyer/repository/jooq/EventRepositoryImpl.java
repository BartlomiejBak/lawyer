package pl.bartekbak.lawyer.repository.jooq;

import org.springframework.stereotype.Repository;
import pl.bartekbak.lawyer.entity.Event;
import pl.bartekbak.lawyer.repository.EventRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class EventRepositoryImpl implements EventRepository {
    @Override
    public List<Event> list() {
        return List.of();
    }

    @Override
    public Optional<Event> eventById(int id) {
        return Optional.empty();
    }

    @Override
    public void add(Event event) {

    }

    @Override
    public void deleteById(int id) {

    }
}
