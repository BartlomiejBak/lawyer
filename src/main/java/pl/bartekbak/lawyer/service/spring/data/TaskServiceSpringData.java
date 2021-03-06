package pl.bartekbak.lawyer.service.spring.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dao.TaskRepository;
import pl.bartekbak.lawyer.dto.TaskDTO;
import pl.bartekbak.lawyer.entity.Task;
import pl.bartekbak.lawyer.service.TaskService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceSpringData implements TaskService {
    TaskRepository taskRepository;

    @Autowired
    public TaskServiceSpringData(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskDTO> findAllTasks() {
        return taskRepository.findAllByOrderByDeadlineAsc()
                .stream()
                .map(Task::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO findTaskById(int id) {
        Optional<Task> result = taskRepository.findById(id);
        TaskDTO task;
        if (result.isPresent()) {
            task = result.get().toDto();
        } else {
            throw new RuntimeException("Id not found");
        }
        return task;
    }

    @Override
    public void saveTask(TaskDTO task) {
        taskRepository.save(Task.fromDto(task));
    }

    @Override
    public void deleteTaskById(int id) {
        taskRepository.deleteById(id);
    }
}
