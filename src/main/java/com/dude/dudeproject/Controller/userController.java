package com.dude.dudeproject.Controller;


import com.dude.dudeproject.Domain.user;
import com.dude.dudeproject.Repository.userRepository;
import com.dude.dudeproject.System.SmsClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController(value="login.html")
public class userController {

    @Autowired
    private userRepository urepo;

    @GetMapping("/idx/login")
    public String loginPage(){

        return "login.html";
    }

    @GetMapping("/signup/preRegistration.html")
    public String preRegPage(){



        return "signup/registration";
    }


    @GetMapping("/regi")
    public String regiPage(Model model){
        model.addAttribute("user", new user());


        return "/regi";
    }

    @PostMapping("/regi")
    public String regiSubmit(@ModelAttribute user user){

        System.out.println("들어왔다" + user.toString());

        urepo.save(user);

        return "signup/login";
        }


        @GetMapping("/textauth")
    public void text(){
            SmsClass sms=new SmsClass();
            String phoneNumber="01099744914";
            String numStr="1234";

            sms.smsText(phoneNumber, numStr);
        }

}
