package pl.bartekbak.lawyer.repository.jooq;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.bartekbak.lawyer.common.DatabaseContext;
import pl.bartekbak.lawyer.entity.ContactRole;
import pl.bartekbak.lawyer.entity.Event;
import pl.bartekbak.lawyer.entity.Lawsuit;
import pl.bartekbak.lawyer.entity.Task;
import pl.bartekbak.lawyer.repository.LawsuitRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.jsonObject;
import static org.jooq.impl.DSL.multiset;
import static org.jooq.impl.DSL.select;
import static pl.bartekbak.lawyer.generate.jooq.Tables.DB_CONTACT;
import static pl.bartekbak.lawyer.generate.jooq.Tables.DB_CONTACT_ROLE;
import static pl.bartekbak.lawyer.generate.jooq.Tables.DB_CONTACT_ROLE_LAWSUIT;
import static pl.bartekbak.lawyer.generate.jooq.Tables.DB_EVENT;
import static pl.bartekbak.lawyer.generate.jooq.Tables.DB_EVENT_LAWSUIT;
import static pl.bartekbak.lawyer.generate.jooq.Tables.DB_LAWSUIT_TASK;
import static pl.bartekbak.lawyer.generate.jooq.Tables.DB_TASK;
import static pl.bartekbak.lawyer.generate.jooq.tables.DbLawsuit.DB_LAWSUIT;

@Repository
public class LawsuitRepositoryImpl extends DatabaseContext implements LawsuitRepository {

    public LawsuitRepositoryImpl(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public List<Lawsuit> list() {

        return dslContext().select(
                        DB_LAWSUIT.asterisk(),
                        multiset(
                                select(
                                        DB_CONTACT_ROLE.ID,
                                        DB_CONTACT_ROLE.ROLE,
                                        field(
                                                select(jsonObject(DB_CONTACT.fields()))
                                                        .from(DB_CONTACT)
                                                        .where(DB_CONTACT.CONTACT_ID.eq(DB_CONTACT_ROLE.CONTACT))
                                        ).as("contact")
                                )
                                        .from(DB_CONTACT_ROLE)
                                        .join(DB_CONTACT_ROLE_LAWSUIT)
                                        .on(DB_CONTACT_ROLE.ID.eq(DB_CONTACT_ROLE_LAWSUIT.CONTACT_ROLE))
                                        .where(DB_CONTACT_ROLE_LAWSUIT.LAWSUIT.eq(DB_LAWSUIT.LAWSUIT_ID))
                        )
                                .as("contacts"),
                        multiset(
                                select(DB_TASK.fields())
                                        .from(DB_TASK)
                                        .join(DB_LAWSUIT_TASK)
                                        .on(DB_TASK.TASK_ID.eq(DB_LAWSUIT_TASK.TASK))
                                        .where(DB_LAWSUIT_TASK.LAWSUIT.eq(DB_LAWSUIT.LAWSUIT_ID))
                        ).as("tasklist"),
                        multiset(
                                select(DB_EVENT.fields())
                                        .from(DB_EVENT)
                                        .join(DB_EVENT_LAWSUIT)
                                        .on(DB_EVENT.EVENT_ID.eq(DB_EVENT_LAWSUIT.EVENT))
                                        .where(DB_EVENT_LAWSUIT.LAWSUIT.eq(DB_LAWSUIT.LAWSUIT_ID))
                        ).as("event_set")
                )
                .from(DB_LAWSUIT)
                .fetchInto(Lawsuit.class);
    }

    @Override
    public Optional<Lawsuit> lawsuitById(UUID id) {

        return Optional.ofNullable(dslContext().select(
                        DB_LAWSUIT.asterisk(),
                        multiset(
                                select(
                                        DB_CONTACT_ROLE.ID,
                                        DB_CONTACT_ROLE.ROLE,
                                        field(
                                                select(jsonObject(DB_CONTACT.fields()))
                                                        .from(DB_CONTACT)
                                                        .where(DB_CONTACT.CONTACT_ID.eq(DB_CONTACT_ROLE.CONTACT))
                                        ).as("contact")
                                )
                                        .from(DB_CONTACT_ROLE)
                                        .join(DB_CONTACT_ROLE_LAWSUIT)
                                        .on(DB_CONTACT_ROLE.ID.eq(DB_CONTACT_ROLE_LAWSUIT.CONTACT_ROLE))
                                        .where(DB_CONTACT_ROLE_LAWSUIT.LAWSUIT.eq(DB_LAWSUIT.LAWSUIT_ID))
                        )
                                .as("contacts"),
                        multiset(
                                select(DB_TASK.fields())
                                        .from(DB_TASK)
                                        .join(DB_LAWSUIT_TASK)
                                        .on(DB_TASK.TASK_ID.eq(DB_LAWSUIT_TASK.TASK))
                                        .where(DB_LAWSUIT_TASK.LAWSUIT.eq(DB_LAWSUIT.LAWSUIT_ID))
                        ).as("tasklist"),
                        multiset(
                                select(DB_EVENT.fields())
                                        .from(DB_EVENT)
                                        .join(DB_EVENT_LAWSUIT)
                                        .on(DB_EVENT.EVENT_ID.eq(DB_EVENT_LAWSUIT.EVENT))
                                        .where(DB_EVENT_LAWSUIT.LAWSUIT.eq(DB_LAWSUIT.LAWSUIT_ID))
                        ).as("event_set")
                )
                .from(DB_LAWSUIT)
                .where(DB_LAWSUIT.LAWSUIT_ID.eq(id))
                .fetchOneInto(Lawsuit.class));
    }

    @Override
    @Transactional
    public void add(Lawsuit lawsuit) {
        dslContext().insertInto(DB_LAWSUIT)
                .set(DB_LAWSUIT.LAWSUIT_ID, lawsuit.getLawsuitId())
                .set(DB_LAWSUIT.NAME, lawsuit.getName())
                .set(DB_LAWSUIT.CASE_SIDE, lawsuit.getCaseSide())
                .set(DB_LAWSUIT.INPUT_DATE, lawsuit.getInputDate())
                .set(DB_LAWSUIT.DEADLINE, lawsuit.getDeadline())
                .set(DB_LAWSUIT.SIGNATURE, lawsuit.getSignature())
                .set(DB_LAWSUIT.CLAIM_AMOUNT, lawsuit.getClaimAmount())
                .set(DB_LAWSUIT.ADDITIONAL_INFO, lawsuit.getAdditionalInfo())
                .onDuplicateKeyIgnore()
                .returningResult(DB_LAWSUIT.LAWSUIT_ID)
                .execute();
        lawsuit.getContacts().forEach(
                c -> dslContext().insertInto(DB_CONTACT_ROLE_LAWSUIT)
                        .set(DB_CONTACT_ROLE_LAWSUIT.LAWSUIT, lawsuit.getLawsuitId())
                        .set(DB_CONTACT_ROLE_LAWSUIT.CONTACT_ROLE, c.getId())
                        .onDuplicateKeyIgnore()
                        .execute()
        );
        lawsuit.getTasklist().forEach(
                t -> dslContext().insertInto(DB_LAWSUIT_TASK)
                        .set(DB_LAWSUIT_TASK.LAWSUIT, lawsuit.getLawsuitId())
                        .set(DB_LAWSUIT_TASK.TASK, t.getTaskId())
                        .onDuplicateKeyIgnore()
                        .execute()
        );
        lawsuit.getEventSet().forEach(
                e -> dslContext().insertInto(DB_EVENT_LAWSUIT)
                        .set(DB_EVENT_LAWSUIT.LAWSUIT, lawsuit.getLawsuitId())
                        .set(DB_EVENT_LAWSUIT.EVENT, e.getEventId())
                        .onDuplicateKeyIgnore()
                        .execute()
        );
    }

    @Override
    @Transactional
    public void update(Lawsuit lawsuit) {
        final var current = lawsuitById(lawsuit.getLawsuitId());
        if (!current.isPresent()) {
            add(lawsuit);
        } else {
            var currentLawsuit = current.get();
            dslContext().update(DB_LAWSUIT)
                    .set(DB_LAWSUIT.NAME, lawsuit.getName())
                    .set(DB_LAWSUIT.CASE_SIDE, lawsuit.getCaseSide())
                    .set(DB_LAWSUIT.INPUT_DATE, lawsuit.getInputDate())
                    .set(DB_LAWSUIT.DEADLINE, lawsuit.getDeadline())
                    .set(DB_LAWSUIT.SIGNATURE, lawsuit.getSignature())
                    .set(DB_LAWSUIT.CLAIM_AMOUNT, lawsuit.getClaimAmount())
                    .set(DB_LAWSUIT.ADDITIONAL_INFO, lawsuit.getAdditionalInfo())
                    .where(DB_LAWSUIT.LAWSUIT_ID.eq(lawsuit.getLawsuitId()))
                    .andNotExists(dslContext().selectFrom(DB_LAWSUIT)
                            .where(DB_LAWSUIT.SIGNATURE.eq(lawsuit.getSignature()))
                            .and(DB_LAWSUIT.LAWSUIT_ID.ne(lawsuit.getLawsuitId())))
                    .execute();
            updateContacts(toAdd(currentLawsuit.getContacts(), lawsuit.getContacts()),
                    toRemove(currentLawsuit.getContacts(), lawsuit.getContacts()),
                    lawsuit.getLawsuitId());
            updateTasks(toAdd(currentLawsuit.getTasklist(), lawsuit.getTasklist()),
                    toRemove(currentLawsuit.getTasklist(), lawsuit.getTasklist()),
                    lawsuit.getLawsuitId());
            updateEvents(toAdd(currentLawsuit.getEventSet(), lawsuit.getEventSet()),
                    toRemove(currentLawsuit.getEventSet(), lawsuit.getEventSet()),
                    lawsuit.getLawsuitId());
        }
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        dslContext().deleteFrom(DB_LAWSUIT)
                .where(DB_LAWSUIT.LAWSUIT_ID.eq(id))
                .execute();
    }

    @Transactional
    public void updateContacts(List<ContactRole> toAdd, List<ContactRole> toRemove, UUID lawsuitId) {
        toAdd.forEach(c ->
                dslContext().insertInto(DB_CONTACT_ROLE_LAWSUIT)
                        .set(DB_CONTACT_ROLE_LAWSUIT.LAWSUIT, lawsuitId)
                        .set(DB_CONTACT_ROLE_LAWSUIT.CONTACT_ROLE, c.getId())
                        .execute()
        );
        toRemove.forEach(c ->
                dslContext().deleteFrom(DB_CONTACT_ROLE_LAWSUIT)
                        .where(DB_CONTACT_ROLE_LAWSUIT.LAWSUIT.eq(lawsuitId))
                        .and(DB_CONTACT_ROLE_LAWSUIT.CONTACT_ROLE.eq(c.getId()))
                        .execute()
        );
    }

    @Transactional
    public void updateTasks(List<Task> toAdd, List<Task> toRemove, UUID lawsuitId) {
        toAdd.forEach(t ->
                dslContext().insertInto(DB_LAWSUIT_TASK)
                        .set(DB_LAWSUIT_TASK.LAWSUIT, lawsuitId)
                        .set(DB_LAWSUIT_TASK.TASK, t.getTaskId())
                        .execute()
        );
        toRemove.forEach(t ->
                dslContext().deleteFrom(DB_LAWSUIT_TASK)
                        .where(DB_LAWSUIT_TASK.LAWSUIT.eq(lawsuitId))
                        .and(DB_LAWSUIT_TASK.TASK.eq(t.getTaskId()))
                        .execute()
        );
    }

    @Transactional
    public void updateEvents(List<Event> toAdd, List<Event> toRemove, UUID lawsuitId) {
        toAdd.forEach(e ->
                dslContext().insertInto(DB_EVENT_LAWSUIT)
                        .set(DB_EVENT_LAWSUIT.LAWSUIT, lawsuitId)
                        .set(DB_EVENT_LAWSUIT.EVENT, e.getEventId())
                        .execute()
        );
        toRemove.forEach(e ->
                dslContext().deleteFrom(DB_EVENT_LAWSUIT)
                        .where(DB_EVENT_LAWSUIT.LAWSUIT.eq(lawsuitId))
                        .and(DB_EVENT_LAWSUIT.EVENT.eq(e.getEventId()))
                        .execute()
        );
    }

    public <T> List<T> toAdd(List<T> current, List<T> updated) {
        var toAdd = new ArrayList<>(updated);
        toAdd.removeAll(current);
        return toAdd;
    }

    public <T> List<T> toRemove(List<T> current, List<T> updated) {
        var toRemove = new ArrayList<>(current);
        toRemove.removeAll(updated);
        return toRemove;
    }
}
