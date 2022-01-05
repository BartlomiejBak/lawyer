package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.dto.TaskDTO;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<TaskDTO> findAllTasks();

    TaskDTO findTaskById(UUID id);

    void saveTask(TaskDTO task);

    void deleteTaskById(UUID id);
}
