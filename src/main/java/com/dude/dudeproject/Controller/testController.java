package com.dude.dudeproject.Controller;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class testController {

    @GetMapping("/")
    public String testPage(){

        return "index";
    }

}
