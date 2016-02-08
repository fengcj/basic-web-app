package com.rest.mvc;

import com.core.models.entities.BlogEntry;
import com.core.services.BlogEntryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by fengc on 1/11/2016.
 */
public class BlogEntryControllerTest {

    @InjectMocks
    private BlogEntryController blogEntryController;

    @Mock
    private BlogEntryService blogEntryService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(blogEntryController).build();
    }

    @Test
    public void getExistingBlogEntry() throws Exception {
        BlogEntry blogEntry = new BlogEntry();
        blogEntry.setId(1L);
        blogEntry.setTitle("Test title");

        when(blogEntryService.findBlogEntry(1L)).thenReturn(blogEntry);

        mockMvc.perform(get("/rest/blog-entries/1"))
                .andExpect(jsonPath("$.title", is(blogEntry.getTitle())))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/blog-entries/1"))))
                .andExpect(status().isOk()).andDo(print());

    }

    @Test
    public void getNonExistingBlogEntry() throws Exception {
        when(blogEntryService.findBlogEntry(1L)).thenReturn(null);
        mockMvc.perform(get("/rest/blog-entries/1"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }


    @Test
    public void deleteExistingBlogEntry() throws Exception {
        BlogEntry deletedBlogEntry = new BlogEntry();
        deletedBlogEntry.setTitle("Test Title");
        deletedBlogEntry.setId(1L);

        when(blogEntryService.deleteBlogEntry(1L)).thenReturn(deletedBlogEntry);

        mockMvc.perform(delete("/rest/blog-entries/1"))
                .andExpect(jsonPath("$.title", is(deletedBlogEntry.getTitle())))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/blog-entries/1"))))
                .andExpect(status().isOk()).andDo(print());

    }

    @Test
    public void deleteNotExistingBlogEntry() throws Exception{

        when(blogEntryService.deleteBlogEntry(1L)).thenReturn(null);
        mockMvc.perform(delete("/rest/blog-entries"))
                .andExpect(status().isNotFound()).andDo(print());


    }


    @Test
    public void updateExistingBlogEntry() throws Exception {
        BlogEntry updatedBlogEntry = new BlogEntry();
        updatedBlogEntry.setTitle("Updated Test Title");
        updatedBlogEntry.setId(1L);

        when(blogEntryService.updateBlogEntry(eq(1L), any(BlogEntry.class))).thenReturn(updatedBlogEntry);

        mockMvc.perform(put("/rest/blog-entries/1").contentType(MediaType.APPLICATION_JSON).content("{\"title\":\"Test Title\"}"))
                .andExpect(jsonPath("$.title", is(updatedBlogEntry.getTitle())))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/blog-entries/1"))))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void updateNotExistingBlogEntry() throws Exception{
        when(blogEntryService.updateBlogEntry(eq(1L),any(BlogEntry.class))).thenReturn(null);

        mockMvc.perform(put("/rest/blog-entries/1").contentType(MediaType.APPLICATION_JSON).content("{\"title\":\"Test Title\"}"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }



}
