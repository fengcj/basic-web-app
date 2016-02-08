package com.rest.resources;

import com.core.models.entities.Blog;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by fengc on 1/13/2016.
 */
public class BlogResource extends ResourceSupport {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Blog toBlog(){

        Blog blog = new Blog();
        blog.setTitle(title);
        return blog;

    }

}
