package pl.bartekbak.lawyer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bartekbak.lawyer.entity.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    public List<Task> findAllByOrderByDeadlineAsc();
}
