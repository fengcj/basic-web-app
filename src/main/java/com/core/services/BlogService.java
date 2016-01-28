package com.core.services;

import com.core.entities.Blog;
import com.core.entities.BlogEntry;
import com.core.services.util.BlogEntryList;
import com.core.services.util.BlogList;

/**
 * Created by fcj on 16/1/12.
 */
public interface BlogService {

    BlogEntry createBlogEntry(Long id,BlogEntry data);

    BlogList findAllBlogs();

    Blog findBlog(Long id);

    BlogEntryList findAllBlogEntries(Long BlogId);

}
