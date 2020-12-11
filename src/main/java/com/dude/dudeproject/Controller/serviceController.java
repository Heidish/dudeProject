package com.dude.dudeproject.Controller;


import com.dude.dudeproject.Domain.response;
import com.dude.dudeproject.Domain.service;
import com.dude.dudeproject.Service.responseDaoService;
import com.dude.dudeproject.Service.serviceDaoService;
import com.dude.dudeproject.System.ImageService;
import com.dude.dudeproject.System.QRGenerator;
import com.dude.dudeproject.System.RandomClass;
import com.sun.imageio.plugins.png.PNGImageReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.awt.image.PNGImageDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Objects;


@Controller
@RequestMapping("/service")
public class serviceController {
    @Autowired
    private responseDaoService service;


    @Autowired
    private serviceDaoService serviceDaoService;


    //회원 가입 후, 내 정보 누르면 사용자마다 QR 이미지 넣어주기
    @GetMapping(value = "/setQRcode/{qr_image}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody
    void insertQR(@PathVariable(value = "qr_image") String qr_image,  HttpServletRequest request, HttpServletResponse response,
                  @ModelAttribute service service, Model model) throws Exception {


        HttpSession session = request.getSession(false);

        String user_id = (String) request.getAttribute("id");

        String target_no = service.getTarget_qr_no();

        target_no = RandomClass.numrandom(10);


        qr_image = "http://localhost:8090/" + RandomClass.numrandom(10);









        response.setContentType("image/png");
        OutputStream outputStream = response.getOutputStream();


        outputStream.flush();

        service.setUser_id(user_id);
        service.setTarget_qr_no(target_no);
        service.setQr_image(qr_image);
        outputStream.write((ImageService.getQRCodeImage(qr_image, 300, 300)));
        serviceDaoService.create(service);

        outputStream.close();





        model.addAttribute("qr_image", qr_image);
//        model.addAttribute(" qr_id",user_id);

        System.out.println("qr 삽입 할 id : " + service.getUser_id());
        System.out.println("qr 삽입 할 target_no 값 : " + service.getTarget_qr_no());
        System.out.println("qr 삽입 할 이미지 값 : " + service.getQr_image());


    }


    //첫 출차요청으로 사용자가 들어왔을 때
    @GetMapping(value = "/response")
    public String ResponsePage() {
        return "user/userResponse";
    }

    //사용자가 응답 후 디비에 저장
    @PostMapping(value = "/response")
    public String saveResponse(HttpServletRequest request, @ModelAttribute response response) {
        System.out.println("사용자가 응답한 num : " + response.getUser_response());
        System.out.println("사용자 ID : " + response.getUser_id());

        service.create(response);

        return "/user/userResponseTrue";     //어디로든 보내놓고 어차피 아래꺼로 내려옴
    }

    //사용자 전환 페이지
    @GetMapping(value = "/responseTrue")
    public String ResponseTruePage() {
        return "/user/userResponseTrue";
    }

}