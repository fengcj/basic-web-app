package com.rest.resources.asm;

import com.core.models.entries.Blog;
import com.rest.mvc.AccountController;
import com.rest.mvc.BlogController;
import com.rest.resources.BlogResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

//import static org.springframework.hateoas.jaxrs.JaxRsLinkBuilder.linkTo;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * Created by fengc on 1/13/2016.
 */
public class BlogResourceAsm extends ResourceAssemblerSupport<Blog,BlogResource> {

    public BlogResourceAsm() {
        super(BlogController.class, BlogResource.class);
    }

    @Override
    public BlogResource toResource(Blog blog) {
        BlogResource blogResource = new BlogResource();
        blogResource.setTitle(blog.getTitle());
        blogResource.add(linkTo(BlogController.class).slash(blog.getId()).withSelfRel());
        blogResource.add(linkTo(BlogController.class).slash(blog.getId()).slash("entries").withRel("entries"));
        if(blog.getOwner() != null){
            blogResource.add(linkTo(AccountController.class).slash(blog.getOwner().getId()).withRel("owner"));
        }
        return blogResource;
    }


}
