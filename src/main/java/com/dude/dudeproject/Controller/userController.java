package com.dude.dudeproject.Controller;


import com.dude.dudeproject.Domain.user;
import com.dude.dudeproject.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class userController {

    @Autowired
    private userRepository urepo;

    @GetMapping("signup/login.html")
    public String loginPage(){

        return "signup/login";
    }

    @GetMapping("/signup/preRegistration.html")
    public String preRegPage(){



        return "signup/registration";
    }


    @GetMapping("/regi")
    public String regiPage(Model model){
        model.addAttribute("user", new user());


        return "signup/regi";
    }

    @PostMapping("/regi")
    public String regiSubmit(@ModelAttribute user user){

        System.out.println("들어왔다" + user.toString());

        urepo.save(user);

        return "/signup/login";
        }

}
