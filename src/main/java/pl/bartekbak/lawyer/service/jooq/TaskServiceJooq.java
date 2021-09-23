package pl.bartekbak.lawyer.service.jooq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dto.TaskDTO;
import pl.bartekbak.lawyer.entity.Task;
import pl.bartekbak.lawyer.repository.TaskRepository;
import pl.bartekbak.lawyer.service.TaskService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceJooq implements TaskService {
    TaskRepository taskRepository;

    @Autowired
    public TaskServiceJooq(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskDTO> findAllTasks() {
        return taskRepository.list()
                .stream()
                .map(Task::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO findTaskById(int id) {
        Optional<Task> result = taskRepository.taskById(id);
        TaskDTO task;
        task = result.map(Task::toDto).orElse(null);
        return task;
    }

    @Override
    public void saveTask(TaskDTO task) {
        taskRepository.add(Task.fromDto(task));
    }

    @Override
    public void deleteTaskById(int id) {
        taskRepository.deleteById(id);
    }
}
