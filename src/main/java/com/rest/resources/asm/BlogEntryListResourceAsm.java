package com.rest.resources.asm;

import com.core.services.util.BlogEntryList;
import com.rest.mvc.BlogController;
import com.rest.resources.BlogEntryListResource;
import com.rest.resources.BlogEntryResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * Created by fengc on 1/14/2016.
 */
public class BlogEntryListResourceAsm extends ResourceAssemblerSupport<BlogEntryList,BlogEntryListResource> {

    public BlogEntryListResourceAsm() {
        super(BlogController.class, BlogEntryListResource.class);
    }

    @Override
    public BlogEntryListResource toResource(BlogEntryList blogEntryList) {

        List<BlogEntryResource> resources = new BlogEntryResourceAsm().toResources(blogEntryList.getEntries());
        BlogEntryListResource blogEntryListResource = new BlogEntryListResource();

        blogEntryListResource.setEntries(resources);
        blogEntryListResource.add(linkTo(methodOn(BlogController.class).findAllBlogEntries(blogEntryList.getBlogId())).withSelfRel());
        return blogEntryListResource;
    }
}
