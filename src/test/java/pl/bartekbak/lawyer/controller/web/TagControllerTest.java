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
import pl.bartekbak.lawyer.dto.TagDTO;
import pl.bartekbak.lawyer.entity.Tag;
import pl.bartekbak.lawyer.service.TagService;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class TagControllerTest {

    @Mock
    TagService service;
    @InjectMocks
    TagController controller;

    ObjectMapper objectMapper;
    MockMvc mockMvc;
    private static final String TAG_ADD_FORM = "tags/add-tag-form";
    TagDTO tag;

    @BeforeEach
    void setUp() {
        tag = TagDTO.builder()
                .tagId(1)
                .name("name")
                .build();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void listTagsTest() throws Exception {
        mockMvc.perform(get("/tags/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("tags/list-tags"))
                .andExpect(model().attributeExists("tags"));
    }

    @Test
    void showFormForAddTest() throws Exception {
        mockMvc.perform(get("/tags/showFormForAdd"))
                .andExpect(status().isOk())
                .andExpect(view().name(TAG_ADD_FORM))
                .andExpect(model().attributeExists("tag"));
    }

    @Test
    void showFormForUpdateTest() throws Exception {
        //given
        when(service.findTagById(anyInt())).thenReturn(tag);
        //when
        mockMvc.perform(get("/tags/showFormForUpdate")
                    .param("tagId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name(TAG_ADD_FORM))
                .andExpect(model().attributeExists("tag"));
    }

    @Test
    void deleteTest() throws Exception {
        mockMvc.perform(get("/tags/delete")
                .param("tagId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:list"));
    }
}
