package pl.bartekbak.lawyer.service.jooq.data;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bartekbak.lawyer.repository.TaskRepository;
import pl.bartekbak.lawyer.dto.TaskDTO;
import pl.bartekbak.lawyer.entity.Task;
import pl.bartekbak.lawyer.service.jooq.TaskServiceJooq;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Disabled
@ExtendWith(MockitoExtension.class)
class TaskServiceSpringDataTest {

    @InjectMocks
    TaskServiceJooq service;

    @Mock
    TaskRepository repository;

    Task taskOne = Task.builder().taskId(1).description("description 1").build();

    @Test
    void findAllTasks() {
        //given
        List<Task> list = new ArrayList<>();
        Task taskTwo = Task.builder().taskId(2).description("description 2").build();
        Task taskThree = Task.builder().taskId(3).description("description 3").build();

        list.add(taskOne);
        list.add(taskTwo);
        list.add(taskThree);

        //when
        when(repository.list()).thenReturn(list);
        List<TaskDTO> result = service.findAllTasks();

        //then
        assertEquals(3, result.size());
        verify(repository, times(1)).list();
    }

    @Test
    void findTaskById() {
        //given
        //when
        when(repository.taskById(1))
                .thenReturn(Optional.of(taskOne));
        TaskDTO result = service.findTaskById(1);
        //then
        assertEquals("description 1", result.getDescription());
    }

    @Test
    void saveTask() {
        //given
        //when
        service.saveTask(taskOne.toDto());
        service.saveTask(new TaskDTO());
        //then
        verify(repository, times(1)).add(taskOne);
        verify(repository, times(2)).add(any());
    }

    @Test
    void deleteTaskById() {
        //given
        //when
        service.deleteTaskById(1);
        service.deleteTaskById(2);
        //then
        verify(repository, times(1)).deleteById(1);
        verify(repository, times(2)).deleteById(any());
    }
}
