package com.basic.mvc;

import com.core.entities.BlogEntry;
import com.core.services.BlogEntryService;
import com.rest.mvc.BlogEntryController;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by fengc on 1/11/2016.
 */
public class BlogEntryControllerTest {

    @InjectMocks
    private BlogEntryController controller;

    @Mock
    private BlogEntryService service;


    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getExistingBlogEntry() throws Exception {
        BlogEntry blogEntry = new BlogEntry();
        blogEntry.setId(1L);
        blogEntry.setTitle("Test title");

        when(service.find(1L)).thenReturn(blogEntry);

        mockMvc.perform(get("/rest/blog-entries/1"))
                .andExpect(jsonPath("$.title",is(blogEntry.getTitle())))
                .andExpect(jsonPath("$.links[*].href",hasItem(endsWith("/blog-entries/1"))))
                .andExpect(status().isOk()).andDo(print());

    }

    @Test
    public void getNonExistingBlogEntry() throws Exception {
        when(service.find(1L)).thenReturn(null);

        mockMvc.perform(get("/rest/blog-entries/1")).andExpect(status().isNotFound()).andDo(print());
    }




}
