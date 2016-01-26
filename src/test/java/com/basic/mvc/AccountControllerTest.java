package com.basic.mvc;

import com.core.entities.Account;
import com.core.entities.Blog;
import com.core.services.AccountService;
import com.core.services.exceptions.AccountDoesNotExistException;
import com.jayway.jsonassert.impl.matcher.IsMapContainingKey;
import com.rest.mvc.AccountController;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by fengc on 1/14/2016.
 */
public class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    private MockMvc mockMvc;

    private ArgumentCaptor<Account> accountCapter;
    private ArgumentCaptor<Blog> blogCapter;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
        accountCapter = ArgumentCaptor.forClass(Account.class);
    }

    @Test
    public void createBlogExistingAccount() throws Exception{
        Blog createdBlog = new Blog();
        createdBlog.setId(1L);
        createdBlog.setTitle("Test title");

        when(accountService.createBlog(eq(1L),any(Blog.class))).thenReturn(createdBlog);

        mockMvc.perform(post("/rest/accounts/1/blogs")
                .content("{\"title\":\"Test title\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is(createdBlog.getTitle())))
                .andExpect(jsonPath("$.links[*].href",hasItem(endsWith("/blogs/1"))))
                .andExpect(header().string("Location", endsWith("/blogs/1")))
                .andExpect(status().isCreated())
                .andDo(print());

        // verify `createBlog` method has been called.
        verify(accountService).createBlog(eq(1L), any(Blog.class));

        // use `accountCapture` to capture params passed to `createAccount` method
        verify(accountService).createBlog(eq(1L),blogCapter.capture());
        String title = blogCapter.getValue().getTitle();
        assertEquals("Test title",title);








    }


    @Test
    public void createBlogNotExistingAccount() throws Exception{

        when(accountService.createBlog(eq(1L), any(Blog.class))).thenThrow(new AccountDoesNotExistException());

        mockMvc.perform(post("/rest/accounts/1/blogs").content("{\"title\":\"Test Title\"}").contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isBadRequest()).
                andDo(print());

    }

    @Test
    public void getExistingAccount() throws Exception{
        Account account = new Account();
        account.setId(1L);
        account.setName("test");
        account.setPassword("password");
        when(accountService.findAccount(eq(1L))).thenReturn(account);

        mockMvc.perform(get("/rest/accounts/1"))
                .andExpect(jsonPath("$.password", is(nullValue())))  //  is(nullValue())
                .andExpect(jsonPath("$.name", is(account.getName())))
                .andExpect(status().isOk())
                .andDo(print());

    }


}
