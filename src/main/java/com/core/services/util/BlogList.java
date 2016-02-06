package com.core.services.util;

import com.core.models.entries.Blog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fcj on 16/1/12.
 */
public class BlogList {

    private List<Blog> blogs = new ArrayList<Blog>();

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}
