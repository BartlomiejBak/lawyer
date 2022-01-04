package pl.bartekbak.lawyer.repository;

import pl.bartekbak.lawyer.entity.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository {

    List<Task> list();
    Optional<Task> taskById(UUID id);
    void add(Task task);
    void update(Task task);
    void deleteById(UUID id);
}
