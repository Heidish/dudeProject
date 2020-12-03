package com.dude.dudeproject.Controller;

import com.dude.dudeproject.Domain.user;
import com.dude.dudeproject.Repository.userRepository;
import com.dude.dudeproject.Service.userDaoService;
import com.dude.dudeproject.System.SmsClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@Slf4j
public class userController {

//    @Autowired
//    private userDaoService service;
//
//    private BCryptPasswordEncoder pwdEncoder;

    userDaoService service;
    PasswordEncoder passwordEncoder;

    @Autowired
    public userController(userDaoService service, PasswordEncoder passwordEncoder) {

        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }


    private static final Logger logger = LogManager.getLogger(userController.class);

//    @GetMapping("/")
//    public String startPage() {
//        logger.info("LogManager's logger : logging test");
//        System.out.println(logger.equals(log));
//
//        return "index";
//    }

    @GetMapping(value = "/login")
    public String loginPage(@ModelAttribute user user, Model model) {
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
        System.out.println("암호화 전 비밀번호 = " + user.getUser_pw());

        String password = passwordEncoder.encode(user.getUser_pw()); // 비밀번호를 암호화
        user.setUser_pw(password);

        System.out.println("암호화 후 비밀번호 = " + password);

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
        String password = service.loginPwdChk(user);
        System.out.println("로그인 페이지에서 사용자가 입력한 비밀번호 : " + user.getUser_pw());
        System.out.println("db에 저장된 암호화된 비밀번호 : " + password);

        boolean pwdMatch = passwordEncoder.matches(user.getUser_pw(), password);

        if (!service.login(user) && pwdMatch == false) {
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
