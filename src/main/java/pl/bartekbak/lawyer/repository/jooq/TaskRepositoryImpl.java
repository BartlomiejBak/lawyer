package pl.bartekbak.lawyer.repository.jooq;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.bartekbak.lawyer.common.DatabaseContext;
import pl.bartekbak.lawyer.entity.Task;
import pl.bartekbak.lawyer.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

import static org.jooq.impl.DSL.*;
import static pl.bartekbak.lawyer.generate.jooq.Tables.DB_TASK_CONTACT;
import static pl.bartekbak.lawyer.generate.jooq.tables.DbContact.DB_CONTACT;
import static pl.bartekbak.lawyer.generate.jooq.tables.DbTask.DB_TASK;

@Repository
public class TaskRepositoryImpl extends DatabaseContext implements TaskRepository {

    public TaskRepositoryImpl(DSLContext dsl) {
        super(dsl);
    }

    @Override
    public List<Task> list() {
        return dslContext().select(
                        DB_TASK.asterisk(),
                        field(
                                select(jsonArrayAgg(jsonObject(DB_CONTACT.fields())))
                                        .from(DB_CONTACT)
                                        .join(DB_TASK_CONTACT)
                                        .on(DB_CONTACT.CONTACT_ID.eq(DB_TASK_CONTACT.CONTACT))
                                        .where(DB_TASK_CONTACT.TASK.eq(DB_TASK.TASK_ID))
                        )
                                .as("contactList")
                )
                .from(DB_TASK)
                .fetchInto(Task.class);
    }

    @Override
    public Optional<Task> taskById(int id) {
        return Optional.ofNullable(
                dslContext().select(
                                DB_TASK.asterisk(),
                                field(
                                        select(jsonArrayAgg(jsonObject(DB_CONTACT.fields())))
                                                .from(DB_CONTACT)
                                                .join(DB_TASK_CONTACT)
                                                .on(DB_CONTACT.CONTACT_ID.eq(DB_TASK_CONTACT.CONTACT))
                                                .where(DB_TASK_CONTACT.TASK.eq(DB_TASK.TASK_ID))
                                )
                                        .as("contactList")
                        )
                        .from(DB_TASK)
                        .where(DB_TASK.TASK_ID.eq(id))
                        .fetchOneInto(Task.class)
        );
    }

    // todo add/remove contact

    @Override
    @Transactional
    public void add(Task task) {
        dslContext().insertInto(DB_TASK)
                .set(DB_TASK.PRIORITY, task.isPriority())
                .set(DB_TASK.DEADLINE, task.getDeadline())
                .set(DB_TASK.DESCRIPTION, task.getDescription())
                .onDuplicateKeyIgnore()
                .execute();
    }

    @Override
    @Transactional
    public void update(Task task) {
        dslContext().update(DB_TASK)
                .set(DB_TASK.PRIORITY, task.isPriority())
                .set(DB_TASK.DEADLINE, task.getDeadline())
                .set(DB_TASK.DESCRIPTION, task.getDescription())
                .where(DB_TASK.TASK_ID.eq(task.getTaskId()))
                .execute();
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        dslContext().deleteFrom(DB_TASK)
                .where(DB_TASK.TASK_ID.eq(id))
                .execute();
    }
}
