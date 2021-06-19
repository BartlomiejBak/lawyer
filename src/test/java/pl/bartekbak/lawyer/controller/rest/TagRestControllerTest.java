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
import pl.bartekbak.lawyer.commons.ModelProvider;
import pl.bartekbak.lawyer.dto.TagDTO;
import pl.bartekbak.lawyer.service.spring.data.TagServiceSpringData;

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
class TagRestControllerTest {

    ModelProvider provider = new ModelProvider();

    private final TagDTO firstTag = provider.getFirstTag();

    private final List<TagDTO> tags = provider.getTags();

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private TagRestController tagRestController;

    @Mock
    private TagServiceSpringData tagService;

    @BeforeEach
    void setUp() {
        tagRestController = new TagRestController(tagService);
        final StandaloneMockMvcBuilder mvcBuilder = MockMvcBuilders.standaloneSetup(tagRestController);
        mockMvc = mvcBuilder.build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllTags_shouldReturnTags() throws Exception {
        //given
        when(tagService.findAllTags()).thenReturn(tags);
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/tags")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final List<TagDTO> result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
                });
        assertEquals(tags, result);
    }

    @Test
    void getTag_shouldReturnFirstTag() throws Exception {
        //given
        when(tagService.findTagById(100)).thenReturn(firstTag);
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/tags/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final TagDTO result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
                });
        assertEquals(firstTag, result);
    }

    @Test
    void addTag_shouldInvokePostSaveTagOnce() throws Exception {
        //given
        doNothing().when(tagService).saveTag(any(TagDTO.class));
        //when
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/tags")
                        .content(objectMapper.writeValueAsString(firstTag))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(tagService, times(1)).saveTag(any(TagDTO.class));
    }

    @Test
    void updateTag_shouldInvokePutSaveTagOnce() throws Exception {
        //given
        doNothing().when(tagService).saveTag(any(TagDTO.class));
        //when
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/tags")
                        .content(objectMapper.writeValueAsString(firstTag))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(tagService, times(1)).saveTag(any(TagDTO.class));
    }

    @Test
    void deleteTag_shouldInvokeDeleteTagByIdOnce() throws Exception {
        //given
        doNothing().when(tagService).deleteTagById(anyInt());
        when(tagService.findTagById(anyInt())).thenReturn(firstTag);
        //when
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/tags/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(tagService, times(1)).deleteTagById(anyInt());
    }
}
