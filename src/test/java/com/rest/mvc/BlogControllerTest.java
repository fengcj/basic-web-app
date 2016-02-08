package com.rest.mvc;

import com.core.models.entities.Account;
import com.core.models.entities.Blog;
import com.core.models.entities.BlogEntry;
import com.core.services.BlogService;
import com.core.services.exceptions.BlogNotFoundException;
import com.core.services.util.BlogEntryList;
import com.core.services.util.BlogList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by fengc on 1/28/2016.
 */
public class BlogControllerTest {

    @InjectMocks
    private BlogController blogController;

    @Mock
    private BlogService blogService;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(blogController).build();
    }

    @Test
    public void findAllBlogs() throws Exception {

        BlogList foundBlogList = new BlogList();
        List<Blog> blogs = new ArrayList<Blog>();
        Blog blogA = new Blog();
        blogA.setId(1L);
        blogA.setTitle("Title A");
        Blog blogB = new Blog();
        blogB.setId(1L);
        blogB.setTitle("Title B");
        blogs.add(blogA);
        blogs.add(blogB);
        foundBlogList.setBlogs(blogs);

        when(blogService.findAllBlogs()).thenReturn(foundBlogList);
        mockMvc.perform(get("/rest/blogs"))
                .andExpect(jsonPath("$.blogs[*].title", hasItems(endsWith("Title A"), endsWith("Title B"))))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void getBlog() throws Exception {
        Blog blog = new Blog();
        blog.setId(1L);
        blog.setTitle("Test Title");

        Account account = new Account();
        account.setId(1L);
        blog.setOwner(account);

        when(blogService.findBlog(1L)).thenReturn(blog);
        mockMvc.perform(get("/rest/blogs/1"))
                .andExpect(jsonPath("$.title",is(blog.getTitle())))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/rest/blogs/1"))))
                .andExpect(jsonPath("$.links[*].href",hasItem(endsWith("/rest/blogs/1/entries"))))
                .andExpect(jsonPath("$.links[*].href",hasItem(endsWith(("/rest/accounts/1")))))
                .andExpect(jsonPath("$.links[*].rel",hasItems(is("self"),is("entries"),is("owner"))))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    public void createBlogEntryExistingBlog() throws Exception{
        BlogEntry createdBlogEntry = new BlogEntry();
        createdBlogEntry.setId(1L);
        createdBlogEntry.setTitle("Test Title");
        Blog blog = new Blog();
        blog.setId(1L);
        createdBlogEntry.setBlog(blog);

        when(blogService.createBlogEntry(eq(1L), any(BlogEntry.class))).thenReturn(createdBlogEntry);
        mockMvc.perform(post("/rest/blogs/1/blog-entries").contentType(MediaType.APPLICATION_JSON).content("{\"title\":\"Generic Title\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title",is(createdBlogEntry.getTitle())))
                .andExpect(jsonPath("$.links[*].href",hasItem(endsWith("/rest/blog-entries/1"))))
                .andExpect(jsonPath("$.links[*].href",hasItem(endsWith("/rest/blogs/1"))))
                .andDo(print());
    }


    @Test
    public void createBlogEntryNonExistingBlog() throws Exception{
        when(blogService.createBlogEntry(eq(1L),any(BlogEntry.class))).thenThrow(new BlogNotFoundException());
        mockMvc.perform(post("/rest/blogs/1/blog-entries").contentType(MediaType.APPLICATION_JSON).content("{\"title\":\"Generic Title\"}"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void findAllBlogEntries() throws Exception {

        BlogEntryList foundBlogEntryList = new BlogEntryList();
        List<BlogEntry> lists = new ArrayList<BlogEntry>();
        BlogEntry blogEntry1 = new BlogEntry();
        blogEntry1.setId(1L);
        blogEntry1.setTitle("Test title");
        BlogEntry blogEntry2 = new BlogEntry();
        blogEntry2.setId(2L);
        blogEntry2.setTitle("Test title");
        lists.add(blogEntry1);
        lists.add(blogEntry2);
        foundBlogEntryList.setEntries(lists);
        foundBlogEntryList.setBlogId(1L);
        when(blogService.findAllBlogEntries(1L)).thenReturn(foundBlogEntryList);



        mockMvc.perform(get("/rest/blogs/1/blog-entries"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.links[*].href",hasItem(endsWith("/blogs/1/blog-entries"))))
                .andExpect(jsonPath("$.entries[*].title",hasItem(is("Test title"))));

    }

    @Test
    public void findNonExistingAllBlogEntries() throws Exception {

        when(blogService.findAllBlogEntries(1L)).thenThrow(new BlogNotFoundException());

        mockMvc.perform(get("/rest/blogs/1/blog-entries")).andDo(print()).andExpect(status().isNotFound());

    }


}
