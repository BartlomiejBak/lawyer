package pl.bartekbak.lawyer.controller.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import pl.bartekbak.lawyer.commons.LocalDateMapper;
import pl.bartekbak.lawyer.commons.ModelProvider;
import pl.bartekbak.lawyer.dto.TaskDTO;
import pl.bartekbak.lawyer.service.spring.data.TaskServiceSpringData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TaskRestControllerTest {

    ModelProvider provider = new ModelProvider();

    private final TaskDTO firstTask = provider.getFirstTask();
    private final List<TaskDTO> tasks = provider.getTasks();

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private TaskServiceSpringData taskService;

    @BeforeEach
    void setUp() {
        objectMapper = LocalDateMapper.builder().build().getMapper();
        TaskRestController taskRestController = new TaskRestController(taskService);
        final StandaloneMockMvcBuilder mvcBuilder = MockMvcBuilders.standaloneSetup(taskRestController);
        mockMvc = mvcBuilder.build();
    }

    @Test
    void getAllTasks_shouldReturnTasks() throws Exception {
        //given
        when(taskService.findAllTasks()).thenReturn(tasks);
        //when
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/tasks")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final List<TaskDTO> result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
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
                        .get("/api/tasks/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final TaskDTO result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
                });
        assertEquals(firstTask, result);
    }

    @Test
    void addTask_shouldInvokePostSaveTaskOnce() throws Exception {
        //given
        doNothing().when(taskService).saveTask(any(TaskDTO.class));
        //when
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/tasks")
                        .content(objectMapper.writeValueAsString(firstTask))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(taskService, times(1)).saveTask(any(TaskDTO.class));
    }

    @Test
    void updateTask_shouldInvokePutSaveTaskOnce() throws Exception {
        //given
        doNothing().when(taskService).saveTask(any(TaskDTO.class));
        //when
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/tasks")
                        .content(objectMapper.writeValueAsString(firstTask))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(taskService, times(1)).saveTask(any(TaskDTO.class));
    }

    @Test
    void deleteTask_shouldInvokeDeleteOrderByIdOnce() throws Exception {
        //given
        doNothing().when(taskService).deleteTaskById(anyInt());
        when(taskService.findTaskById(anyInt())).thenReturn(firstTask);
        //when
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/tasks/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(taskService, times(1)).deleteTaskById(anyInt());
    }
}
