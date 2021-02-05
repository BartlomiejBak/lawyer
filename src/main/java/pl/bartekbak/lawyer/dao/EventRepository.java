package pl.bartekbak.lawyer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bartekbak.lawyer.entity.Event;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    public List<Event> findAllByOrderByDateTimeAsc();
}
