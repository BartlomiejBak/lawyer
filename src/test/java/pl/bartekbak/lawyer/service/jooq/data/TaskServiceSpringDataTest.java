package pl.bartekbak.lawyer.service.jooq.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bartekbak.lawyer.dto.TaskDTO;
import pl.bartekbak.lawyer.entity.Task;
import pl.bartekbak.lawyer.repository.TaskRepository;
import pl.bartekbak.lawyer.service.jooq.TaskServiceJooq;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceSpringDataTest {

    @InjectMocks
    TaskServiceJooq service;

    @Mock
    TaskRepository repository;

    private final UUID first = UUID.randomUUID();
    private final UUID second = UUID.randomUUID();
    private final UUID third = UUID.randomUUID();

    Task taskOne = Task.builder().taskId(first).description("description 1").build();

    @Test
    void findAllTasks() {
        // given
        List<Task> list = new ArrayList<>();
        Task taskTwo = Task.builder().taskId(second).description("description 2").build();
        Task taskThree = Task.builder().taskId(third).description("description 3").build();

        list.add(taskOne);
        list.add(taskTwo);
        list.add(taskThree);

        // when
        when(repository.list()).thenReturn(list);
        List<TaskDTO> result = service.findAllTasks();

        // then
        assertEquals(3, result.size());
        verify(repository, times(1)).list();
    }

    @Test
    void findTaskById() {
        // given
        // when
        when(repository.taskById(first))
                .thenReturn(Optional.of(taskOne));
        TaskDTO result = service.findTaskById(first);

        // then
        assertEquals("description 1", result.getDescription());
    }

    @Test
    void saveTask() {
        // given
        // when
        service.saveTask(taskOne.toDto());
        service.saveTask(new TaskDTO());

        // then
        verify(repository, times(1)).add(taskOne);
        verify(repository, times(2)).add(any());
    }

    @Test
    void deleteTaskById() {
        // given
        // when
        service.deleteTaskById(first);
        service.deleteTaskById(second);

        // then
        verify(repository, times(1)).deleteById(first);
        verify(repository, times(2)).deleteById(any());
    }
}
