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
import pl.bartekbak.lawyer.common.ExpandedObjectMapper;
import pl.bartekbak.lawyer.commons.ModelProvider;
import pl.bartekbak.lawyer.dto.TaskDTO;
import pl.bartekbak.lawyer.service.TaskService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
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

    ModelProvider provider = new ModelProvider();
    ObjectMapper objectMapper;
    MockMvc mockMvc;
    private static final String TASK_ADD_FORM = "tasks/add-task-form";
    TaskDTO task;

    @BeforeEach
    void setUp() {
        task = provider.getFirstTask();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ExpandedObjectMapper();
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
        when(service.findTaskById(any())).thenReturn(task);
        //when
        mockMvc.perform(get("/tasks/" + UUID.randomUUID() + "/edit"))
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
                .param("taskId", UUID.randomUUID().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:list"));
    }

    @Test
    void showTaskTest() throws Exception {
        //given
        when(service.findTaskById(any())).thenReturn(task);
        //when
        mockMvc.perform(get("/tasks/" + UUID.randomUUID()))
                .andExpect(status().isOk());
        ModelAndView result = controller.showTask(task.getTaskId());
        //then
        assertFalse(result.isEmpty());
    }
}
