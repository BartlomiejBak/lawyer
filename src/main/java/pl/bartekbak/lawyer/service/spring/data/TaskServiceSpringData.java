package pl.bartekbak.lawyer.service.spring.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dao.TaskRepository;
import pl.bartekbak.lawyer.entity.Task;
import pl.bartekbak.lawyer.service.TaskService;

import java.util.List;

@Service
public class TaskServiceSpringData implements TaskService {
    TaskRepository taskRepository;

    @Autowired
    public TaskServiceSpringData(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findAllAddresses() {
        return null;
    }
}
