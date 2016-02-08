package com.core.repositories;

import com.core.models.entities.Blog;

import java.util.List;

/**
 * Created by fcj on 16/2/7.
 */
public interface BlogRepo {


    Blog createBlog(Blog data);
    List<Blog> findAllBlogs();
    Blog findBlog(Long id);
    Blog findBlogByTitle(String title);
    List<Blog> findBlogsByAccount(Long accountId);


}
