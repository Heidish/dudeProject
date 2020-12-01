package com.dude.dudeproject.Controller;

import com.dude.dudeproject.Domain.user;
import com.dude.dudeproject.Service.userDaoService;
import com.dude.dudeproject.System.SmsClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;


@Controller
public class userController {

    @Autowired
    private userDaoService service;

    @GetMapping("/")
    public String startPage() {

        return "index";
    }

    @GetMapping("/user/login")
    public String loginPage(Model model) {
        model.addAttribute("user", new user());

        return "signup/login";
    }

    @GetMapping("/user/preRegistration")
    public String preRegPage() {

        return "signup/preRegistration";
    }

    @GetMapping("/user/registration")
    public String regPage(Model model) {
        model.addAttribute("user", new user());

        return "signup/registration";
    }

    @PostMapping("/user/add")
    public String createUser(@ModelAttribute user user) {
        System.out.println(user.toString());
        service.save(user);

        return "signup/login";
    }

    @GetMapping("/textauth")
    public void text(){
        SmsClass sms=new SmsClass();
        String phoneNumber="01099744914";
        String numStr="1234";

        sms.smsText(phoneNumber, numStr);
    }

    @PostMapping("/user/login")
    public String login(@ModelAttribute user user) {
        service.login(user);
        System.out.println(user.getUser_id());
        System.out.println(user.getUser_pw());

        if (service.login(user) == null) {

            return "/signup/login";
        }

        return "afterLogin/mainPage";
    }

    @GetMapping("/user/findID")
    public String findID() {

        return "/signup/findID";
    }

    @GetMapping("/user/findPwd")
    public String findPwd() {

        return "/signup/findPwd";
    }

    // push해야함
}
