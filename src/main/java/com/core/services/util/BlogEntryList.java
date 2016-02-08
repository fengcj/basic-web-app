package com.core.services.util;

import com.core.models.entities.BlogEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fcj on 16/1/12.
 */
public class BlogEntryList {

    public BlogEntryList(Long blogId,List<BlogEntry> entries){
        this.blogId = blogId;
        this.entries = entries;
    }


    public List<BlogEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<BlogEntry> entries) {
        this.entries = entries;
    }

    private List<BlogEntry> entries = new ArrayList<BlogEntry>();
    private Long blogId;

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }
}
