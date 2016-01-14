package com.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Created by fengc on 1/13/2016.
 */
public class BlogEntryListResource extends ResourceSupport {

    private String title;
    private List<BlogEntryResource> entries;

    public List<BlogEntryResource> getEntries() {
        return entries;
    }

    public void setEntries(List<BlogEntryResource> entries) {
        this.entries = entries;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



}
