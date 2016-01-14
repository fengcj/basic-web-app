package com.rest.mvc;


import com.core.entities.BlogEntry;
import com.core.services.BlogEntryService;
import com.rest.resources.BlogEntryResource;
import com.rest.resources.asm.BlogEntryResourceAsm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * Created by fcj on 16/1/10.
 */



@Controller
@RequestMapping("/rest/blog-entries")
public class BlogEntryController {

    private BlogEntryService service;

    public BlogEntryController(BlogEntryService service){
        this.service = service;
    }


    @RequestMapping(value="/{blogEntryId}",method = RequestMethod.GET)
    public ResponseEntity<BlogEntryResource> getBlogEntry(@PathVariable Long blogEntryId){
        BlogEntry blogEntry = service.findBlogEntry(blogEntryId);
        if(blogEntry == null){
            return new ResponseEntity<BlogEntryResource>(HttpStatus.NOT_FOUND);
        }
        BlogEntryResource blogEntryResource = new BlogEntryResourceAsm().toResource(blogEntry);
        return  new ResponseEntity<BlogEntryResource>(blogEntryResource,HttpStatus.OK);

    }

    @RequestMapping(value = "/{blogEntryId}",method = RequestMethod.DELETE)
    public ResponseEntity<BlogEntryResource> deleteBlogEntry(@PathVariable Long blogEntryId){

        BlogEntry blogEntry = service.deleteBlogEntry(blogEntryId);
        if(blogEntry == null){
            return new ResponseEntity<BlogEntryResource>(HttpStatus.NOT_FOUND);
        }
        BlogEntryResource blogEntryResource = new BlogEntryResourceAsm().toResource(blogEntry);
        return new ResponseEntity<BlogEntryResource>(blogEntryResource,HttpStatus.OK);
    }

    @RequestMapping(value = "/{blogEntryId}",method = RequestMethod.PUT)
    public ResponseEntity<BlogEntryResource> updateBlogEntry(@PathVariable Long blogEntryId,@RequestBody BlogEntryResource sentBlogEntry){

        BlogEntry blogEntry = service.updateBlogEntry(blogEntryId, sentBlogEntry.toBlogEntry());
        if(blogEntry == null){
            return new ResponseEntity<BlogEntryResource>(HttpStatus.NOT_FOUND);
        }
        BlogEntryResource blogEntryResource = new BlogEntryResourceAsm().toResource(blogEntry);

        return new ResponseEntity<BlogEntryResource>(blogEntryResource,HttpStatus.OK);
    }




}
