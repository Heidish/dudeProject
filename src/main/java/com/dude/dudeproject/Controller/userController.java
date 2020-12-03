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
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@Slf4j
public class userController {

    @Autowired
    private userDaoService service;

    private static final Logger logger = LogManager.getLogger(userController.class);

//    @GetMapping("/")
//    public String startPage() {
//        logger.info("LogManager's logger : logging test");
//        System.out.println(logger.equals(log));
//
//        return "index";
//    }

    @GetMapping(value = "/login")
    public String loginPage(Model model) {
        model.addAttribute("user", new user());

        return "signup/login";
    }

    @GetMapping(value = "/preRegistration")
    public String preRegPage() {

        return "signup/preRegistration";
    }

    @GetMapping(value = "/registration")
    public String regPage(Model model) {
        model.addAttribute("user", new user());

        return "signup/registration";
    }

    @PostMapping(value = "/add")
    public String createUser(@ModelAttribute user user) {
        System.out.println(user.toString());
        service.save(user);

        return "signup/login";
    }

    @GetMapping(value="/textauth")
    public @ResponseBody String text(@RequestParam("user_mobile") String number){
        SmsClass sms=new SmsClass();
        System.out.println(number);
        String certNum = sms.smsText(number);
        return certNum;
    }

    @PostMapping(value = "/login")
    public String login(@ModelAttribute user user) throws Exception {
        service.login(user);
        System.out.println("id=" + user.getUser_id());
        System.out.println("pw=" + user.getUser_pw());

        if (!service.login(user)) {
            throw new Exception();
        }

        return "afterLogin/mainPage";  // success
    }

    @GetMapping(value = "/findID")
    public String findID() {

        return "/signup/findID";
    }

    @GetMapping(value = "/findPwd")
    public String findPwd() {

        return "/signup/findPwd";
    }

    @GetMapping(value = "/afterLogin")
    public String afterLogin() {

        return "/afterLogin/mainPage";
    }

    @GetMapping(value = "/idCheck")
    public @ResponseBody int idCheck(@RequestParam("user_id") String user_id){
        int check = service.idCheck(user_id);
        if(check==0) {
            return 0;  // unique
        }else{
            return 1; // 중복
        }

    }

}
