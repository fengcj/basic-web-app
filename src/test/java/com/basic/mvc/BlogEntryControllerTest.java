package com.basic.mvc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by fengc on 1/11/2016.
 */
public class BlogEntryControllerTest {

    @InjectMocks
    private BlogEntryController controller;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void test() throws Exception {
        mockMvc.perform(get("/test")).andDo(print());
    }

    @Test
    public void test2() throws Exception {
        mockMvc.perform(get("/test2")).andDo(print());
    }

    @Test
    public void test3() throws Exception {
        mockMvc.perform(get("/test3")).andDo(print());
    }

    @Test
    public void test4() throws Exception {
        mockMvc.perform(post("/test4").
                        content("{\"title\":\"Test Blog Entry\"}").
                        contentType(MediaType.APPLICATION_JSON)
                        ).andExpect(jsonPath("$.title",is("Test Blog Entry Back to client")))
                        .andDo(print());
    }


}
