package pl.bartekbak.lawyer.controller.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;
import pl.bartekbak.lawyer.dto.TaskDTO;
import pl.bartekbak.lawyer.entity.Task;
import pl.bartekbak.lawyer.service.TaskService;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    TaskService service;
    @InjectMocks
    TaskController controller;

    ObjectMapper objectMapper;
    MockMvc mockMvc;
    private static final String TASK_ADD_FORM = "tasks/add-task-form";
    TaskDTO task;

    @BeforeEach
    void setUp() {
        task = TaskDTO.builder()
                .taskId(1)
                .description("do something 1")
                .deadline(LocalDateTime.of(2021, 5, 13, 12, 30))
                .priority(true)
                .build();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void listTasksTest() throws Exception {
        mockMvc.perform(get("/tasks/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("tasks/list-tasks"))
                .andExpect(model().attributeExists("tasks"));
    }

    @Test
    void showFormForAddTest() throws Exception {
        mockMvc.perform(get("/tasks/showFormForAdd"))
                .andExpect(status().isOk())
                .andExpect(view().name(TASK_ADD_FORM))
                .andExpect(model().attributeExists("task"));
    }

    @Test
    void showFormForUpdateTest() throws Exception {
        //given
        when(service.findTaskById(anyInt())).thenReturn(task);
        //when
        mockMvc.perform(get("/tasks/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name(TASK_ADD_FORM))
                .andExpect(model().attributeExists("task"));
    }

    @Test
    void saveTask_validObjectTest() throws Exception {
        mockMvc.perform(post("/tasks/save")
                .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:list"));
    }

    @Test
    void deleteTest() throws Exception {
        mockMvc.perform(get("/tasks/delete")
                .param("taskId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:list"));
    }

    @Test
    void showTaskTest() throws Exception {
        //given
        when(service.findTaskById(anyInt())).thenReturn(task);
        //when
        mockMvc.perform(get("/tasks/1"))
                .andExpect(status().isOk());
        ModelAndView result = controller.showTask(1);
        //then
        assertFalse(result.isEmpty());
    }
}
