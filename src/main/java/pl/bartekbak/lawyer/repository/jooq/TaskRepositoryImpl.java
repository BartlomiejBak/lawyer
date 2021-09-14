package pl.bartekbak.lawyer.repository.jooq;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import pl.bartekbak.lawyer.common.DatabaseContext;
import pl.bartekbak.lawyer.entity.Task;
import pl.bartekbak.lawyer.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepositoryImpl extends DatabaseContext implements TaskRepository {

    public TaskRepositoryImpl(DSLContext dsl) {
        super(dsl);
    }

    @Override
    public List<Task> list() {
        return List.of();
    }

    @Override
    public Optional<Task> taskById(int id) {
        return Optional.empty();
    }

    @Override
    public void add(Task task) {

    }

    @Override
    public void deleteById(int id) {

    }
}
