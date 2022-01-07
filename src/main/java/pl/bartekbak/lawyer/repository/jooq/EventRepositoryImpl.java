package pl.bartekbak.lawyer.repository.jooq;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.bartekbak.lawyer.common.DatabaseContext;
import pl.bartekbak.lawyer.entity.Event;
import pl.bartekbak.lawyer.generate.jooq.tables.DbEvent;
import pl.bartekbak.lawyer.repository.EventRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static pl.bartekbak.lawyer.generate.jooq.tables.DbEvent.DB_EVENT;

@Repository
public class EventRepositoryImpl extends DatabaseContext implements EventRepository {

    public EventRepositoryImpl(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public List<Event> list() {
        return dslContext().selectFrom(DB_EVENT)
                .fetch()
                .map(Event::fromDbRecord);
    }

    @Override
    public Optional<Event> eventById(UUID id) {
        return dslContext().selectFrom(DB_EVENT)
                .where(DB_EVENT.EVENT_ID.eq(id))
                .fetchOptional()
                .map(Event::fromDbRecord);
    }

    @Override
    @Transactional
    public void add(Event event) {
        dslContext().insertInto(DB_EVENT)
                .set(DB_EVENT.EVENT_ID, event.getEventId())
                .set(DB_EVENT.DATE_TIME, event.getDateTime())
                .set(DB_EVENT.TITLE, event.getTitle())
                .set(DB_EVENT.DESCRIPTION, event.getDescription())
                .onDuplicateKeyIgnore()
                .execute();
    }

    @Override
    @Transactional
    public void update(Event event) {
        dslContext().update(DB_EVENT)
                .set(DB_EVENT.DATE_TIME, event.getDateTime())
                .set(DB_EVENT.TITLE, event.getTitle())
                .set(DB_EVENT.DESCRIPTION, event.getDescription())
                .where(DB_EVENT.EVENT_ID.eq(event.getEventId()))
                .execute();
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        dslContext().deleteFrom(DB_EVENT)
                .where(DB_EVENT.EVENT_ID.eq(id))
                .execute();

    }
}
