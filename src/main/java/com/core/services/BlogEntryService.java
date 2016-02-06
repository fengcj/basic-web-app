package com.core.services;

import com.core.models.entries.BlogEntry;

/**
 * Created by fengc on 1/12/2016.
 */
public interface BlogEntryService {
     BlogEntry findBlogEntry(Long id);

     BlogEntry updateBlogEntry(Long id,BlogEntry blogEntry);

     BlogEntry deleteBlogEntry(Long id);
}
