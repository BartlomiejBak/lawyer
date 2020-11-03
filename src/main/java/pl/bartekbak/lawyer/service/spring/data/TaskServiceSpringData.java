package pl.bartekbak.lawyer.service.spring.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dao.TaskRepository;
import pl.bartekbak.lawyer.entity.Task;
import pl.bartekbak.lawyer.service.TaskService;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceSpringData implements TaskService {
    TaskRepository taskRepository;

    @Autowired
    public TaskServiceSpringData(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findAllAddresses() {
        return taskRepository.findAllByOrderByDeadlineAsc();
    }

    @Override
    public Task findTaskById(int id) {
        Optional<Task> result = taskRepository.findById(id);
        Task task = null;
        if (result.isPresent()) {
            task = result.get();
        } else {
            throw new RuntimeException("Id not found");
        }
        return task;
    }

    @Override
    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void deleteTaskById(int id) {
        taskRepository.deleteById(id);
    }
}
