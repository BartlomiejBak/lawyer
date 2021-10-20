package pl.bartekbak.lawyer.repository.jooq;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.bartekbak.lawyer.common.DatabaseContext;
import pl.bartekbak.lawyer.entity.Lawsuit;
import pl.bartekbak.lawyer.repository.LawsuitRepository;

import java.util.List;
import java.util.Optional;

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
    public Optional<Lawsuit> lawsuitById(int id) {

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

    }

    @Override
    @Transactional
    public void update(Lawsuit lawsuit) {

    }

    @Override
    @Transactional
    public void deleteById(int id) {

    }
}
