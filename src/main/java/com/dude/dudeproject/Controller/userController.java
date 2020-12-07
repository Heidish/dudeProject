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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public String login(@ModelAttribute user user, HttpServletRequest request) throws Exception {
        String password = service.loginPwdChk(user);
        System.out.println("로그인 페이지에서 사용자가 입력한 비밀번호 : " + user.getUser_pw());
        System.out.println("db에 저장된 암호화된 비밀번호 : " + password);

        boolean pwdMatch = passwordEncoder.matches(user.getUser_pw(), password);

        if (!service.login(user) && pwdMatch == false) {
            throw new Exception();
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("id", user.getUser_id());

        return "afterLogin/mainPage";  // success
    }

    @PostMapping(value = "/findID")
    public @ResponseBody user findID(@RequestParam("user_mobile") String user_mobile) {
        user user = new user();
        System.out.println(service.findId(user_mobile));
        user.setUser_id(service.findId(user_mobile));
        return user;

    }

    @GetMapping(value = "/findID")
    public String findIDForm() {

        return "/signup/findID";
    }

    @GetMapping(value = "/findIDResult")
    public String findIDResult(@RequestParam String user_id,Model model) {
        model.addAttribute("user_id",user_id);
        return "/signup/findIDResult";
    }


    @GetMapping(value = "/findPwd")
    public String findPwd() {

        return "/signup/findPwd";
    }

    @GetMapping(value = "/findPwdResult")
    public String findPwdResult(@RequestParam("user_id") String user_id, Model model ) {
        model.addAttribute("user_id",user_id);
        return "/signup/findPwdResult";
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

    @GetMapping(value = "/mobileCheck")
    public @ResponseBody user mobileCheckGetName(@RequestParam("user_mobile") String user_mobile){
        user user = new user();
        user.setUser_name(service.mobileCheck(user_mobile));
        return user;
    }

    @GetMapping(value = "/mobileCheck2")
    public @ResponseBody user mobileCheckGetId(@RequestParam("user_mobile") String user_mobile){
        user user = new user();
        user.setUser_id(service.mobileCheck2(user_mobile));
        return user;
    }

    @PostMapping(value="/pass") // 비밀번호 찾기
    public String setUpdatePass(@ModelAttribute user user){
        System.out.println("암호화 전 비밀번호 = " + user.getUser_pw());

        String password = passwordEncoder.encode(user.getUser_pw()); // 비밀번호를 암호화
        user.setUser_pw(password);

        System.out.println("암호화 후 비밀번호 = " + password);

       service.setNewPass(user);
       
       return "/signup/login";
    }

    @PostMapping(value="/newpass") // 비밀번호 재설정
    public String setNewUpdatePass(HttpServletRequest request,@ModelAttribute user user){
        HttpSession session = request.getSession(false);

        System.out.println("암호화 전 비밀번호 = " + user.getUser_pw());

        String password = passwordEncoder.encode(user.getUser_pw()); // 비밀번호를 암호화
        user.setUser_pw(password);

        System.out.println("암호화 후 비밀번호 = " + password);

        service.setNewPass(user);
        session.invalidate();

        return "/signup/login";
    }

    @GetMapping(value ="/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        session.invalidate();
        return "/signup/login";
    }

    @GetMapping(value = "/account")
    public String myAccount(){
        // qr 

        return "/afterLogin/myAccount";
    }

    @GetMapping(value = "/main")
    public String goMain(){

        return "/afterLogin/mainPage";
    }

    @GetMapping(value = "/chkPass")
    public String goChkPass(){

        return "/afterLogin/checkPw";
    }

    @PostMapping(value = "/chkPass")
    public String chkPass(HttpServletRequest request, @RequestParam String user_pw) throws Exception {
        HttpSession session = request.getSession(false);
        String user_id = (String) session.getAttribute("id");

        user user= new user();
        user.setUser_id(user_id);
        user.setUser_pw(user_pw);

        String password = service.loginPwdChk(user);
        System.out.println("로그인 페이지에서 사용자가 입력한 비밀번호 : " + user.getUser_pw());
        System.out.println("db에 저장된 암호화된 비밀번호 : " + password);

        boolean pwdMatch = passwordEncoder.matches(user_pw, password);

        if (!service.login(user) && pwdMatch == false) {
            throw new Exception();
        }
        return "/afterLogin/updateUserPw";
    }

    @GetMapping(value = "/updateMobile")
    public String updateMobileFrom(){
    //setNewMobile


        return "/afterLogin/updateUserMobile";
    }

    @PostMapping(value = "/updateMobile")
    public String updateMobile(HttpServletRequest request, @ModelAttribute user user){
        System.out.println("mobile : "+user.getUser_mobile());
        HttpSession session = request.getSession(false);
        String user_id = (String) session.getAttribute("id");
        System.out.println("id : "+user_id);
        user.setUser_id(user_id);

        service.setNewMobile(user);

        return "/afterLogin/mainPage";
    }

    @GetMapping(value = "/updatepw")
    public String updatePw(){

        return "/afterLogin/updateUserPw";
    }
}
