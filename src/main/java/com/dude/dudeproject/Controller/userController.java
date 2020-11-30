package com.dude.dudeproject.Controller;

import com.dude.dudeproject.Domain.user;
import com.dude.dudeproject.Service.userDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class userController {

    @Autowired
    private userDaoService service;

    @GetMapping("/")
    public String startPage() {

        return "index";
    }

    @GetMapping("/user/login")
    public String loginPage(){

        return "signup/login";
    }

    @GetMapping("/user/preRegistration")
    public String preRegPage(){

        return "signup/preRegistration";
    }

    @GetMapping("/user/registration")
    public String regPage(Model model){
        model.addAttribute("user", new user());

        return "signup/registration";
    }

    @PostMapping("/user/add")
    public String createUser(@ModelAttribute user user) {
        System.out.println(user.toString());
        service.save(user);

        return "/signup/login";
    }


}
