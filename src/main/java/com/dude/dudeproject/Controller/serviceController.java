package com.dude.dudeproject.Controller;


import com.dude.dudeproject.Domain.response;
import com.dude.dudeproject.Service.responseDaoService;
import com.dude.dudeproject.Service.serviceDaoService;
import com.dude.dudeproject.System.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;


@Controller
@RequestMapping("/service")
public class serviceController {

    @Autowired
    private responseDaoService service;

    @Autowired
    private serviceDaoService serviceDaoService;

    //target_qr_no 할당
    @GetMapping(value = "/target_no", produces = MediaType.IMAGE_PNG_VALUE)
    public String getTarget_qr_no(String target_qr_no) {
        // qr 코드에 있는 난수 가져와서~
        // TARGET_QR_NO 에 저장시켜주자.
        ImageService imageService = new ImageService();

        //2. 난수를 service_tbl 의 target_qr_no 에 저장
        target_qr_no = imageService.generateRandom();

        serviceDaoService.saveQR(target_qr_no);

        System.out.println("서비스 컨트롤러에서 난수 : " + target_qr_no);

        return "signup/login";

    }




    //회원확인
    @GetMapping(value = "/qrCheck/{user_id}")
    public String idCheck(@PathVariable("user_id") String user_id) throws Exception {

        // 1. service_tbl의 user_no 로 target_qr_no 조회
        // 2. if-> user_no가 존재하지 않으면, user_info_tbl에 삽입되지 않은 값
        // 3. else -> user_info_tbl에 존재하므로, myaccount에  target_qr_no 띄워주기

        String check = serviceDaoService.idCheck(user_id);

        System.out.println("보내진 값 머야?: " + check);
        if (check == null) {
            System.out.println("아이디 뭐야? : " + user_id);
            throw new Exception();

        } else {
            return "afterLogin/myAccount";
        }

    }

    //QR 이미지 삽입
    @GetMapping(value = "/qrcode/{qr_image}/{user_id}")
    public void qrcode(@PathVariable("qr_image") String qr_image,
                       @PathVariable("user_id") String user_id,
                       HttpServletResponse response) throws Exception {

        serviceDaoService.saveImage(qr_image,user_id);
        response.setContentType("image/png");
        OutputStream outputStream = response.getOutputStream();
        byte[] bit=ImageService.getQRCodeImage(qr_image, 350, 350);

        System.out.println("byte 값 : " + bit);

        System.out.println(bit);
        outputStream.write(ImageService.getQRCodeImage(qr_image,350, 350));
        outputStream.flush();
        outputStream.close();
    }



    @GetMapping(value = "/response")
    public String ResponsePage(){
        return "user/userResponse";
    }

    @PostMapping(value = "/response")
    public String saveResponse(@ModelAttribute response response){
        System.out.println("사용자가 응답한 num : " + response.getUser_response());
        System.out.println("사용자 ID : " + response.getUser_id());

        service.create(response);

        return "/user/userResponseTrue";     //어디로든 보내놓고 어차피 아래꺼로 내려옴
    }

    @GetMapping(value = "/responseTrue")
    public String ResponseTruePage(){
        return "/user/userResponseTrue";
    }

}
