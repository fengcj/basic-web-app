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
public class BlogEntryController {

    private BlogEntryService service;

    public BlogEntryController(BlogEntryService service){
        this.service = service;
    }


    @RequestMapping(value="/rest/blog-entries/{blogEntryId}",method = RequestMethod.GET)
    public ResponseEntity<BlogEntryResource> getBlogEntry(@PathVariable Long blogEntryId){
        BlogEntry blogEntry = service.find(blogEntryId);
        if(blogEntry == null){
            return new ResponseEntity<BlogEntryResource>(HttpStatus.NOT_FOUND);
        }
        BlogEntryResource blogEntryResource = new BlogEntryResourceAsm().toResource(blogEntry);
        return  new ResponseEntity<BlogEntryResource>(blogEntryResource,HttpStatus.OK);

    }




}