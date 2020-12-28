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
import pl.bartekbak.lawyer.entity.Tag;
import pl.bartekbak.lawyer.service.spring.data.TagServiceSpringData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TagRestControllerTest {

    private final Tag firstTag = new Tag(100, "1 tag");
    private final Tag secondTag = new Tag(101, "2 tag");
    private final Tag thirdTag = new Tag(102, "3 tag");

    private final List<Tag> tags = List.of(firstTag, secondTag, thirdTag);

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
                        .get("/api/tag/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final List<Tag> result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<List<Tag>>() {
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
                        .get("/api/tag/id/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final Tag result = objectMapper
                .readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<Tag>() {
                });
        assertEquals(firstTag, result);
    }

    @Test
    void addTag_shouldInvokePostSaveTagOnce() throws Exception {
        //given
        doNothing().when(tagService).saveTag(any(Tag.class));
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/tag/register")
                        .content(objectMapper.writeValueAsString(firstTag))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(tagService, times(1)).saveTag(any(Tag.class));
    }

    @Test
    void updateTag_shouldInvokePutSaveTagOnce() throws Exception {
        //given
        doNothing().when(tagService).saveTag(any(Tag.class));
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/tag/register")
                        .content(objectMapper.writeValueAsString(firstTag))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(tagService, times(1)).saveTag(any(Tag.class));
    }

    @Test
    void deleteTag_shouldInvokeDeleteTagByIdOnce() throws Exception {
        //given
        doNothing().when(tagService).deleteTagById(anyInt());
        when(tagService.findTagById(anyInt())).thenReturn(firstTag);
        //when
        final MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/tag/remove/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        verify(tagService, times(1)).deleteTagById(anyInt());
    }
}
