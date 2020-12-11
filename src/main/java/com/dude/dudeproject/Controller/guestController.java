package com.dude.dudeproject.Controller;


import com.dude.dudeproject.Domain.response;
import com.dude.dudeproject.Domain.user;
import com.dude.dudeproject.Repository.userRepository;
import com.dude.dudeproject.Service.responseDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/guest")
public class guestController {

    @Autowired
    userRepository userRepo;

    //추가
    @Autowired
    private responseDaoService service;

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

    //QR찍은 후 첫 화면
    @GetMapping(value = "/guestMain")
    public String guestMain(){
        return "guest/guestView";
    }

    //사용자가 응답 했을 때
    @GetMapping(value = "/guestResponse")
    public String guestResponseTrue(Model model){
        //qr에서 얻어온 사용자 아이디를 받아오는거로 바꿔야함
        String id = "tjddms2565";
        String status = service.getStatus(id);
        System.out.println("사용자가 선택한 응답 : " + status);
        if(status.equals("1")){  //가능
            return "guest/guestResponseTrue";
        }else if(status.equals("2")){    //불가능
            return "guest/guestResponseFalse";
        }else{     //전화번호 전송
            String phone_num = service.getPhonenum(id);
            System.out.println("사용자 번호 : " + phone_num);
            model.addAttribute("phone_num", phone_num);

            return "guest/guestResponse";
        }
    }
}