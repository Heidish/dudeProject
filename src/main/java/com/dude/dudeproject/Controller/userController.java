package com.dude.dudeproject.Controller;

import com.dude.dudeproject.Domain.user;
import com.dude.dudeproject.Service.userDaoService;
import com.dude.dudeproject.System.SmsClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class userController {

    @Autowired
    private userDaoService service;

    private static final Logger logger = LogManager.getLogger(userController.class);

    @GetMapping("/")
    public String startPage() {
        logger.info("LogManager's logger : logging test");
        System.out.println(logger.equals(log));

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
    public void text(String number){
        SmsClass sms=new SmsClass();

        sms.smsText(number);
    }

    @PostMapping("/user/login")
    public String login(@ModelAttribute user user) throws Exception {
        service.login(user);
        System.out.println("id=" + user.getUser_id());
        System.out.println("pw=" + user.getUser_pw());

        if (!service.login(user)) {
            throw new Exception();
        }

        return "afterLogin/mainPage";  // success
    }

    @GetMapping("/user/findID")
    public String findID() {

        return "/signup/findID";
    }

    @GetMapping("/user/findPwd")
    public String findPwd() {

        return "/signup/findPwd";
    }

    @GetMapping("/user/afterLogin")
    public String afterLogin() {

        return "/afterLogin/mainPage";
    }
    // push해야함
}
