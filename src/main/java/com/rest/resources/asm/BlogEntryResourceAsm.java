package com.rest.resources.asm;

import com.core.entities.BlogEntry;
import com.rest.mvc.BlogController;
import com.rest.mvc.BlogEntryController;
import com.rest.resources.BlogEntryResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * Created by fengc on 1/12/2016.
 */
public class BlogEntryResourceAsm extends ResourceAssemblerSupport<BlogEntry,BlogEntryResource>{
    public BlogEntryResourceAsm(){
        super(BlogEntryController.class, BlogEntryResource.class);
    }

    @Override
    public BlogEntryResource toResource(BlogEntry blogEntry) {
        BlogEntryResource resource = new BlogEntryResource();
        resource.setTitle(blogEntry.getTitle());
        Link self = linkTo(BlogEntryController.class).slash(blogEntry.getId()).withSelfRel();
        resource.add(self);
        if(blogEntry.getBlog() != null){
            resource.add(linkTo(BlogController.class).slash(blogEntry.getBlog().getId()).withRel("blog"));
        }
        return resource;
    }
}
