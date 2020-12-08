package com.dude.dudeproject.Controller;

import com.dude.dudeproject.Domain.response;
import com.dude.dudeproject.Service.responseDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/service")
public class serviceController {
    @Autowired
    private responseDaoService service;

    @GetMapping(value = "/response")
    public String ResponsePage() {
        return "user/userResponse";
    }

    @PostMapping(value = "/response")
    public String saveResponse(@ModelAttribute response response) {
        System.out.println("사용자가 응답한 num : " + response.getUser_response());
        System.out.println("사용자 ID : " + response.getUser_id());

        service.create(response);

        return "/user/userResponseTrue";     //어디로든 보내놓고 어차피 아래꺼로 내려옴
    }

    @GetMapping(value = "/responseTrue")
    public String ResponseTruePage() {

        return "/user/userResponseTrue";
    }

    @GetMapping(value = "/userTextView")
    public String userTextView() {

        return "/user/userTextView";
    }
}
