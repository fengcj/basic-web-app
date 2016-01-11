package com.basic.mvc;

import com.basic.entities.BlogEntry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * Created by fcj on 16/1/10.
 */



@Controller
public class BlogEntryController {

    @RequestMapping("/test")
    public String test(){
        return "view";
    }

    @RequestMapping("/test2")
    public ResponseEntity<Object> test2(){
        BlogEntry blogEntry = new BlogEntry();
        blogEntry.setTitle("Test Blog Entry");
        return new ResponseEntity<Object>(blogEntry, HttpStatus.OK);
    }

    @RequestMapping("/test3")
    public @ResponseBody BlogEntry test3(){
        BlogEntry blogEntry = new BlogEntry();
        blogEntry.setTitle("Test Blog Entry");
        return blogEntry;
    }

    @RequestMapping(value = "/test4",method = RequestMethod.POST)
    public @ResponseBody BlogEntry test4(@RequestBody BlogEntry blogEntry){
        // BlogEntry blogEntry = new BlogEntry();
        blogEntry.setTitle("Test Blog Entry Back to client");
        return blogEntry;
    }


}
