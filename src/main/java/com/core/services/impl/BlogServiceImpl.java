package com.core.services.impl;

import com.core.models.entities.Blog;
import com.core.models.entities.BlogEntry;
import com.core.repositories.BlogEntryRepo;
import com.core.repositories.BlogRepo;
import com.core.services.BlogService;
import com.core.services.exceptions.BlogNotFoundException;
import com.core.services.util.BlogEntryList;
import com.core.services.util.BlogList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by fcj on 16/2/6.
 */
@Service
@Transactional
public class BlogServiceImpl implements BlogService {


    @Autowired
    private BlogRepo blogRepo;
    @Autowired
    private BlogEntryRepo blogEntryRepo;


    @Override
    public BlogEntry createBlogEntry(Long id, BlogEntry data) {
        Blog blog = blogRepo.findBlog(id);
        if(blog == null){
            throw new BlogNotFoundException();
        }
        BlogEntry blogEntry = blogEntryRepo.createBlogEntry(data);
        blogEntry.setBlog(blog);
        return blogEntry;
    }

    @Override
    public BlogList findAllBlogs() {
        return new BlogList(blogRepo.findAllBlogs());
    }

    @Override
    public Blog findBlog(Long id) {
        return blogRepo.findBlog(id);
    }

    @Override
    public BlogEntryList findAllBlogEntries(Long blogId) {
        Blog blog = blogRepo.findBlog(blogId);
        if(blog == null) {
            throw new BlogNotFoundException();
        }
        return new BlogEntryList(blogId,blogEntryRepo.findBlogEntriesByBlogId(blogId));
    }
}
