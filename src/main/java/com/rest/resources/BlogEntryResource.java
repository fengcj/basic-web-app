package com.rest.resources;

import com.core.models.entities.BlogEntry;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by fengc on 1/12/2016.
 */
public class BlogEntryResource extends ResourceSupport{

    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BlogEntry toBlogEntry() {
        BlogEntry entry = new BlogEntry();
        entry.setTitle(title);
        entry.setContent(content);
        return entry;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
