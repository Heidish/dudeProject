package com.dude.dudeproject.Controller;

import com.dude.dudeproject.Domain.user;
import com.dude.dudeproject.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("target")
public class guestController {

    @Autowired
    userRepository userRepo;

    @GetMapping("one")
    public ModelAndView guesttest(){

        ModelAndView mav=new ModelAndView();
        mav.setViewName("guest/guestResponseTrue");
        mav.addObject("context ok i'm coming");

        return mav;
    }

    @GetMapping("two")
    public ModelAndView twotwo(){
        ModelAndView mav=new ModelAndView();
        user user= new user();
        String id = "";


        user.setUser_id(userRepo.findByUserId("test1"));


        mav.setViewName("signup/login");

        mav.addObject("id", user.getUser_id());

        return mav;
    }

    public void defRandom(){

    }




}
