package pl.bartekbak.lawyer.controller.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import pl.bartekbak.lawyer.entity.Task;
import pl.bartekbak.lawyer.service.spring.data.TaskServiceSpringData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TaskRestControllerTest {

    private final Task firstTask = Task.builder()
            .id(100)
            .description("1 task to do")
            .build();
    private final Task secondTask = Task.builder()
            .id(101)
            .description("2 task to do")
            .build();
    private final Task thirdTask = Task.builder()
            .id(102)
            .description("3 task to do")
            .build();
    private final List<Task> tasks = List.of(firstTask, secondTask, thirdTask);

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private TaskRestController taskRestController;

    @Mock
    private TaskServiceSpringData taskService;

    @BeforeEach
    void setUp() {
        taskRestController = new TaskRestController(taskService);
        final StandaloneMockMvcBuilder mvcBuilder = MockMvcBuilders.standaloneSetup(taskRestController);
        mockMvc = mvcBuilder.build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllTasks_shouldReturnTasks() throws Exception {
        //given
        when(taskService.findAllTasks()).thenReturn(tasks);
        //when
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/task/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final List<Task> result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<List<Task>>() {
                });
        assertEquals(tasks, result);
    }

    @Test
    void getTask_shouldReturnFirstTask() throws Exception {
        //given
        when(taskService.findTaskById(100)).thenReturn(firstTask);
        //when
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/task/id/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final Task result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<Task>() {
                });
        assertEquals(firstTask, result);
    }

    @Test
    void addTask_shouldInvokePostSaveTaskOnce() throws Exception {
        //given
        doNothing().when(taskService).saveTask(any(Task.class));
        //when
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/task/register")
                        .content(objectMapper.writeValueAsString(firstTask))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(taskService, times(1)).saveTask(any(Task.class));
    }

    @Test
    void updateTask_shouldInvokePutSaveTaskOnce() throws Exception {
        //given
        doNothing().when(taskService).saveTask(any(Task.class));
        //when
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/task/register")
                        .content(objectMapper.writeValueAsString(firstTask))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(taskService, times(1)).saveTask(any(Task.class));
    }

    @Test
    void deleteTask_shouldInvokeDeleteOrderByIdOnce() throws Exception {
        //given
        doNothing().when(taskService).deleteTaskById(anyInt());
        when(taskService.findTaskById(anyInt())).thenReturn(firstTask);
        //when
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/task/remove/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(taskService, times(1)).deleteTaskById(anyInt());
    }
}
