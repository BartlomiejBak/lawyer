package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.dto.TaskDTO;

import java.util.List;

public interface TaskService {
    List<TaskDTO> findAllTasks();

    TaskDTO findTaskById(int id);

    void saveTask(TaskDTO task);

    void deleteTaskById(int id);
}
