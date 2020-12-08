package com.dude.dudeproject.Controller;

import com.dude.dudeproject.Domain.timer;
import com.dude.dudeproject.Service.timerDaoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/timer")
@Slf4j
public class timerController {

    @Autowired
    timerDaoService service;
    PasswordEncoder passwordEncoder;

    @Autowired
    public timerController(timerDaoService service) {
        this.service = service;
    }

    private static final Logger logger = LogManager.getLogger(userController.class);

    @PostMapping(value = "/setTimer")
    public @ResponseBody String setTimer(@RequestParam(value = "time_set_home") String setTime,
                                         HttpServletRequest request,
                                         @ModelAttribute timer timer) throws Exception {

        HttpSession session = request.getSession(false);
        String user_id = (String) session.getAttribute("id");

        System.out.println("시간설정 id : " + user_id);
        System.out.println("시간설정 time : " + setTime);

        timer.setTime_set_home(setTime);
        service.save(timer, user_id);

        // set_timer_tbl 컬럼 중 당분간 time_set_home 만 사용함. (time_set_away 는 나중에 사용할 예정)
//        String hour = setTime.substring(0, 2);
//        System.out.println("시간 : " + hour);
//        String minute = setTime.substring(3, 5);
//        System.out.println("분 : " + minute);


        return "signup/login";
    }

}
