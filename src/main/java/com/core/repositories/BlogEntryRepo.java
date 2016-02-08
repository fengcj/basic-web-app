package com.core.repositories;

import com.core.models.entities.BlogEntry;

import java.util.List;

/**
 * Created by fcj on 16/2/7.
 */
public interface BlogEntryRepo {


    BlogEntry findBlogEntry(Long id);

    BlogEntry updateBlogEntry(Long id,BlogEntry blogEntry);

    BlogEntry deleteBlogEntry(Long id);

    BlogEntry createBlogEntry(BlogEntry data);

    List<BlogEntry> findBlogEntriesByBlogId(Long blogId);

}
