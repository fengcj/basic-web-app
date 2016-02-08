package com.core.services.impl;

import com.core.models.entities.BlogEntry;
import com.core.repositories.BlogEntryRepo;
import com.core.repositories.BlogRepo;
import com.core.services.BlogEntryService;
import com.rest.resources.asm.BlogEntryListResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by fcj on 16/2/6.
 */
@Service
@Transactional
public class BlogEntryServiceImpl implements BlogEntryService {


    @Autowired
    private BlogEntryRepo blogEntryRepo;


    @Override
    public BlogEntry findBlogEntry(Long id) {
        return blogEntryRepo.findBlogEntry(id);
    }

    @Override
    public BlogEntry updateBlogEntry(Long id, BlogEntry blogEntry) {
        return blogEntryRepo.updateBlogEntry(id,blogEntry);
    }

    @Override
    public BlogEntry deleteBlogEntry(Long id) {
        return blogEntryRepo.deleteBlogEntry(id);
    }
}
