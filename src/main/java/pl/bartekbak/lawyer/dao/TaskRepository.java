package pl.bartekbak.lawyer.dao;

import org.springframework.data.repository.CrudRepository;
import pl.bartekbak.lawyer.entity.Task;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Integer> {

    public List<Task> findAllByOrderByDeadlineAsc();
}
