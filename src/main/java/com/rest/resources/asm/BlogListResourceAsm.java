package com.rest.resources.asm;

import com.core.services.util.BlogList;
import com.rest.mvc.BlogController;
import com.rest.resources.BlogListResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by fengc on 1/13/2016.
 */
public class BlogListResourceAsm extends ResourceAssemblerSupport<BlogList,BlogListResource> {

    public BlogListResourceAsm() {
        super(BlogController.class, BlogListResource.class);
    }

    @Override
    public BlogListResource toResource(BlogList blogList) {
        BlogListResource blogListResource = new BlogListResource();
        blogListResource.setBlogs(new BlogResourceAsm().toResources(blogList.getBlogs()));
        return blogListResource;
    }
}
