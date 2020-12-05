package com.dude.dudeproject.Controller;

import com.dude.dudeproject.Domain.timer;
import com.dude.dudeproject.Domain.user;
import com.dude.dudeproject.Service.timerDaoService;
import com.dude.dudeproject.Service.userDaoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/timer")
@Slf4j
public class timerController {

    timerDaoService service;
    PasswordEncoder passwordEncoder;

    @Autowired
    public timerController(timerDaoService service) {
        this.service = service;
    }

    private static final Logger logger = LogManager.getLogger(userController.class);

    @GetMapping(value = "/setTimer")
    public String setTimer(@ModelAttribute timer timer, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String user_id = (String) session.getAttribute("id");


        return "signup/login";
    }

}
