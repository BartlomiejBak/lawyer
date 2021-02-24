package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAllTasks();

    Task findTaskById(int id);

    void saveTask(Task task);

    void deleteTaskById(int id);
}
