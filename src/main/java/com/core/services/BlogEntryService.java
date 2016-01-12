package com.core.services;

import com.core.entities.BlogEntry;

/**
 * Created by fengc on 1/12/2016.
 */
public interface BlogEntryService {
    public BlogEntry findBlogEntry(Long id);

    public BlogEntry updateBlogEntry(Long id,BlogEntry blogEntry);

    public BlogEntry deleteBlogEntry(Long id);
}
