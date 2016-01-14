package com.rest.mvc;

import com.core.entities.Blog;
import com.core.entities.BlogEntry;
import com.core.services.BlogService;
import com.core.services.exceptions.BlogNotFoundException;
import com.core.services.util.BlogEntryList;
import com.core.services.util.BlogList;
import com.rest.exceptions.NotFoundException;
import com.rest.resources.BlogEntryListResource;
import com.rest.resources.BlogEntryResource;
import com.rest.resources.BlogListResource;
import com.rest.resources.BlogResource;
import com.rest.resources.asm.BlogEntryListResourceAsm;
import com.rest.resources.asm.BlogEntryResourceAsm;
import com.rest.resources.asm.BlogListResourceAsm;
import com.rest.resources.asm.BlogResourceAsm;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URI;

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
        }catch (Exception e){
            //TODO: handler exception
            e.printStackTrace();
            throw e;
        }
    }

    @RequestMapping(value = "/{blogId}",method = RequestMethod.GET)
    public ResponseEntity<BlogResource> getBlog(@PathVariable Long blogId){
        try {
            Blog blog = blogService.findBlog(blogId);  //  not throw exception?
            BlogResource blogResource = new BlogResourceAsm().toResource(blog);
            return new ResponseEntity<BlogResource>(blogResource,HttpStatus.OK);
        } catch (Exception e) {
            //TODO: handler exception
            e.printStackTrace();
            throw e;
        }
    }

    @RequestMapping(value="/{blogId}/blog-entries",method = RequestMethod.POST)
    public ResponseEntity<BlogEntryResource> createBlogEntry(@PathVariable Long blogId,@RequestBody BlogEntryResource blogEntryResource){
        try {
            BlogEntry createdBlogEntry = blogService.createBlogEntry(blogId,blogEntryResource.toBlogEntry());
            BlogEntryResource createdblogEntryResource = new BlogEntryResourceAsm().toResource(createdBlogEntry);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(createdblogEntryResource.getLink("self").getHref()));
            return new ResponseEntity<BlogEntryResource>(createdblogEntryResource,headers,HttpStatus.CREATED);
        } catch (BlogNotFoundException e) {
            throw new NotFoundException(e);
        }
    }




    @RequestMapping(value="/{blogId}/blog-entries",method = RequestMethod.GET)
    public ResponseEntity<BlogEntryListResource> findAllBlogEntries(@PathVariable Long blogId){

        try{
            BlogEntryList blogEntryList = blogService.findAllBlogEntries(blogId);
            BlogEntryListResource blogEntryListResource = new BlogEntryListResourceAsm().toResource(blogEntryList);
            return new ResponseEntity<BlogEntryListResource>(blogEntryListResource, HttpStatus.OK);
        }catch (BlogNotFoundException e){
            throw new NotFoundException(e);
        }

    }


}
