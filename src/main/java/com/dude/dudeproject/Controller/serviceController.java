package com.dude.dudeproject.Controller;


import com.dude.dudeproject.Domain.response;
import com.dude.dudeproject.Service.responseDaoService;
import com.dude.dudeproject.Service.serviceDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


@Controller
@RequestMapping("/service")
public class serviceController {

    @Autowired
    private responseDaoService service;

    @Autowired
    private serviceDaoService serviceDaoService;

    //target_qr_no 할당
//    @GetMapping(value = "/target_no", produces = MediaType.IMAGE_PNG_VALUE)
//
//    public String getTarget_qr_no(String target_qr_no) {
//        // qr 코드에 있는 난수 가져와서~
//        // TARGET_QR_NO 에 저장시켜주자.
//        ImageService imageService = new ImageService(); // System의 ImageService를 가져와서 사용.
//
//        //2. 난수를 service_tbl 의 target_qr_no 에 저장
//
//        target_qr_no = imageService.generateRandom();
//        serviceDaoService.saveQR(target_qr_no);
//
//        System.out.println("서비스 컨트롤러에서 난수 : " + target_qr_no);
//
//        return "signup/login";
//
//    }


    //QR 이미지 삽입
//    @GetMapping(value = "/qrcode/{qr_image}/{user_id}")
//    public void qrcode(@PathVariable("qr_image") String qr_image, @PathVariable("user_id") String user_id, HttpServletResponse response) throws  Exception {
//
//        serviceDaoService.saveImage(qr_image,user_id);
//        response.setContentType("image/png");
//        OutputStream outputStream = response.getOutputStream();
//        byte[] bit=ImageService.getQRCodeImage(qr_image, 350, 350);
//
//        System.out.println("byte 값 : " + bit);
//
//        System.out.println(bit);
//        outputStream.write(ImageService.getQRCodeImage(qr_image,350, 350));
//        outputStream.flush();
//        outputStream.close();
//    }

    /**
     * byte array to image
     */
    @GetMapping(value="/getImage/{qr_image}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody void byte2Image(@PathVariable(value = "qr_image") byte[] qr_image, HttpServletResponse response,
                                  HttpServletRequest request, Model model) throws FileNotFoundException, IOException {
        System.out.println("come?");
        //   response.setContentType("image/png");
//        OutputStream outputStream = response.getOutputStream();
//
//        outputStream.flush();
//
//        outputStream.write(qr_image);
//
//        outputStream.close();
        //   InputStream is = request.getInputStream();
        //     return IOUtils.read(is,qr_image);

        InputStream is = null;
        byte[] bytes;

        /* 저는 jpg 파일로 고정이라 이렇게 했지만 여러분은 타입을 얻어와야 한다. */
        String content_type = "image/png";
        response.setContentType(content_type);  // Content Type Set

        /* DB의 BLOB 타입의 내용을 가져와서 bytes 변수에 담아보자. */

        bytes = qr_image;

        /* String --> InputStream 타입으로 변환 */
        is = new ByteArrayInputStream(bytes);


        /* 이제 OutputStream 으로 출력해보자*/
        ServletOutputStream os = response.getOutputStream();

        int binaryRead;

        while ((binaryRead = is.read()) != -1) {
            os.write(binaryRead);
        }

    }


    @GetMapping(value="/getImage111/{qr_image}")
    public void byteImage(@PathVariable(value = "qr_image") String qr_image, HttpServletResponse response,
                          HttpServletRequest request, Model model) throws FileNotFoundException, IOException {
        System.out.println("come?");
        System.out.println(qr_image);

        InputStream is = null;
        byte[] bytes;

        /* 저는 jpg 파일로 고정이라 이렇게 했지만 여러분은 타입을 얻어와야 한다. */
        String content_type = "image/png";
        response.setContentType(content_type);  // Content Type Set
        System.out.println("1?");
        /* DB의 BLOB 타입의 내용을 가져와서 bytes 변수에 담아보자. */

    //    bytes = qr_image;
        System.out.println("2?");
        /* String --> InputStream 타입으로 변환 */
     //   is = new ByteArrayInputStream(bytes);

        System.out.println("3?");
        /* 이제 OutputStream 으로 출력해보자*/
        ServletOutputStream os = response.getOutputStream();
        System.out.println("4?");
        int binaryRead;

//        while ((binaryRead = is.read()) != -1) {
//            System.out.println("5?");
//            os.write(binaryRead);
//        }

//        outputStream.close();

        model.addAttribute("qr_image", qr_image);
//        model.addAttribute(" qr_id",user_id);

//        System.out.println("qr 삽입 할 id : " + service.getUser_id());
//        System.out.println("qr 삽입 할 target_no 값 : " + service.getTarget_qr_no());
//        System.out.println("qr 삽입 할 이미지 값 : " + service.getQr_image());


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
