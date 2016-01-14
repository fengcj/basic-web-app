package com.rest.mvc;

import com.core.services.BlogService;
import com.core.services.exceptions.BlogNotFoundException;
import com.core.services.util.BlogEntryList;
import com.core.services.util.BlogList;
import com.rest.exceptions.NotFoundException;
import com.rest.resources.BlogEntryListResource;
import com.rest.resources.BlogListResource;
import com.rest.resources.asm.BlogEntryListResourceAsm;
import com.rest.resources.asm.BlogListResourceAsm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by fengc on 1/13/2016.
 */
@Controller
@RequestMapping("/rest/blogs")
public class BlogController {

    private BlogService blogService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<BlogListResource> findAllBlogs(){

        try{
            BlogList blogList = blogService.findAllBlogs(); // not throw exception?
            BlogListResource blogListResource = new BlogListResourceAsm().toResource(blogList);
            return new ResponseEntity<BlogListResource>(blogListResource,HttpStatus.OK);
        }catch (Exception exception){
            throw exception;
        }


    }


    @RequestMapping(value="/{blogId}/blog-entries",method = RequestMethod.GET)
    public ResponseEntity<BlogEntryListResource> findAllBlogEntries(@PathVariable Long blogId){

        try{
            BlogEntryList blogEntryList = blogService.findAllBlogEntries(blogId);
            BlogEntryListResource blogEntryListResource = new BlogEntryListResourceAsm().toResource(blogEntryList);
            return new ResponseEntity<BlogEntryListResource>(blogEntryListResource, HttpStatus.OK);
        }catch (BlogNotFoundException exception){
            throw new NotFoundException(exception);
        }

    }


}
