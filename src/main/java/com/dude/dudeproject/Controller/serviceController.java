package com.dude.dudeproject.Controller;


import com.dude.dudeproject.Domain.response;
import com.dude.dudeproject.Service.responseDaoService;
import com.dude.dudeproject.Service.serviceDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;


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


    @GetMapping(value="/getImage", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody void byte2Image(@RequestParam(value="qr_image") String qr_image) throws FileNotFoundException, IOException {
    System.out.println("come?");
    System.out.println(qr_image);
        File file = new File("./../QRimages/"+qr_image+".png");
        if(file.exists()) {
            System.out.println("exist");
            BufferedImage image = ImageIO.read(file);
            OutputStream out = new FileOutputStream("./../QR/" + qr_image + ".png"); //파일로 출력하기위해 파일출력스트림 생성
            ImageIO.write(image, "png", out); //이미지 출력! , 이미지를 파일출력스트림을 통해 JPG타입으로 출력
            out.close();  //출력스트림 닫기
        }

    System.out.println("out?");
    }

    @GetMapping(path = "qr", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody void setImageFileById(@RequestParam(value="qr_image") String qr_image, HttpServletResponse response)
            throws IOException {
//        FileInfo fileInfo = fileServicegetFileInfo(qr);
        // 파일 정보를 찾고
//        StringBuilder sb = new StringBuilder("file://./../QRimages/");
        // 파일이 실제로 저장되어 있는 경로에
//        String fileName = qr_image;
//        sb.append(fileName);
//        sb.append(".png");
        // 파일 이름을 더해

//        URL fileUrl = new URL(sb.toString());
        // file URL을 생성하고

//        IOUtils.copy(fileUrl.openStream(), response.getOutputStream());
        // IOUtils.copy는 input에서 output으로 encoding 맞춰서 복사하는 메소드다
        // openStream으로 fileUrl의 통로( 입력 스트림 )를 열고 respons의 outputStream에 복사하면 끝

        //===========================================================================


//        BufferedImage image; //로컬 파일을 사용하는 경우
//        File imageFile = new File("./../QRimages/"+qr_image+".png");
//        image = ImageIO.read(imageFile);

        OutputStream out = response.getOutputStream();
        FileInputStream fis = null;

        try {
            fis = new FileInputStream("./../QRimages/"+qr_image+".png");
            System.out.println("fis 는 : " + fis);
            FileCopyUtils.copy(fis, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            out.flush();
        }
    }



//    @GetMapping(value="/getImage111/{qr_image}")
//    public void byteImage(@PathVariable(value = "qr_image") byte[] qr_image, HttpServletResponse response,
//                          HttpServletRequest request, Model model) throws FileNotFoundException, IOException {
//        System.out.println("come?");
//        System.out.println(qr_image);
//
//        InputStream is = null;
//        byte[] bytes;
//
//        /* 저는 jpg 파일로 고정이라 이렇게 했지만 여러분은 타입을 얻어와야 한다. */
//        String content_type = "image/png";
//        response.setContentType(content_type);  // Content Type Set
//        System.out.println("1?");
//        /* DB의 BLOB 타입의 내용을 가져와서 bytes 변수에 담아보자. */
//
//        bytes = qr_image;
//        System.out.println("2?");
//        /* String --> InputStream 타입으로 변환 */
//        is = new ByteArrayInputStream(bytes);
//
//        System.out.println("3?");
//        /* 이제 OutputStream 으로 출력해보자*/
//        ServletOutputStream os = response.getOutputStream();
//        System.out.println("4?");
//        int binaryRead;
//
//        while ((binaryRead = is.read()) != -1) {
//            System.out.println("5?");
//            os.write(binaryRead);
//        }
//
//        outputStream.close();
//
//        model.addAttribute("qr_image", qr_image);
//        model.addAttribute(" qr_id",user_id);

//        System.out.println("qr 삽입 할 id : " + service.getUser_id());
//        System.out.println("qr 삽입 할 target_no 값 : " + service.getTarget_qr_no());
//        System.out.println("qr 삽입 할 이미지 값 : " + service.getQr_image());
//
//    }

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
