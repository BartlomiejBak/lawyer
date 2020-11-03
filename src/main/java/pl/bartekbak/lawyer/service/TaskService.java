package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.entity.Task;

import java.util.List;

public interface TaskService {
    public List<Task> findAllTasks();

    public Task findTaskById(int id);

    public void saveTask(Task task);

    public void deleteTaskById(int id);
}
