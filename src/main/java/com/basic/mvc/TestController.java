package com.basic.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by fcj on 16/1/10.
 */



@Controller
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "view";
    }


}
