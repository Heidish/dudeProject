package com.dude.dudeproject.Controller;

import com.dude.dudeproject.Service.scanDaoService;
import com.dude.dudeproject.Service.userDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class scanController {

    private String QR_NO_ACTOR;
    private String URL_ONE="qr";
    private String URL_TWO="qrcode";
    private String TARGET_QR_NO="abc123";

    scanDaoService scanService;
    userDaoService userService;

    @Autowired
    public scanController(scanDaoService scanService, userDaoService userService){
        this.scanService=scanService;
        this.userService=userService;
    }


    /**
     * QR타고 들어온 actor, receiver 구분 및 paging
     * **/
    // qr/qrcode/난수
    @GetMapping("/{URL_ONE}/{URL_TWO}/{TARGET_QR_NO}")
    public String userCheck(/*@RequestParam("user_id"),*/
            @PathVariable String URL_ONE,
            @PathVariable String URL_TWO,
            @PathVariable String TARGET_QR_NO, Model model){

        this.TARGET_QR_NO=TARGET_QR_NO;
        this.URL_ONE = URL_ONE;
        this.URL_TWO = URL_TWO;

        String user_id = "abc123"; //확인용

        int value = userService.idCheck(user_id);
        System.out.println(value);
        String id=scanService.findIdByQrNo(this.TARGET_QR_NO);
        System.out.println(id);
                                                //세션에 저장된 user_id값 불러와야함
            if(value==0){
                    model.addAttribute("id", id);
                return "guest/guestView";
            }else
                    model.addAttribute("id", id);
                return "user/userView";
    }



}
