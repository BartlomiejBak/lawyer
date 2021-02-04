package pl.bartekbak.lawyer.service.spring.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bartekbak.lawyer.dao.TaskRepository;
import pl.bartekbak.lawyer.entity.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceSpringDataTest {

    @InjectMocks
    TaskServiceSpringData service;

    @Mock
    TaskRepository repository;

    Task taskOne = Task.builder().id(1).description("description 1").build();

    @Test
    void findAllTasks() {
        //given
        List<Task> list = new ArrayList<>();
        Task taskTwo = Task.builder().id(2).description("description 2").build();
        Task taskThree = Task.builder().id(3).description("description 3").build();

        list.add(taskOne);
        list.add(taskTwo);
        list.add(taskThree);

        //when
        when(repository.findAllByOrderByDeadlineAsc()).thenReturn(list);
        List<Task> result = service.findAllTasks();

        //then
        assertEquals(3, result.size());
        verify(repository, times(1)).findAllByOrderByDeadlineAsc();
    }

    @Test
    void findTaskById() {
        //given
        //when
        when(repository.findById(1))
                .thenReturn(Optional.of(taskOne));
        Task result = service.findTaskById(1);
        //then
        assertEquals("description 1", result.getDescription());
    }

    @Test
    void saveTask() {
        //given
        //when
        service.saveTask(taskOne);
        service.saveTask(new Task());
        //then
        verify(repository, times(1)).save(taskOne);
        verify(repository, times(2)).save(any());
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
