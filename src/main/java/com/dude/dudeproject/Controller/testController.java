package com.dude.dudeproject.Controller;

import com.dude.dudeproject.Repository.userRepository;
import com.dude.dudeproject.Service.userDaoService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;


@Controller
public class testController {

    private userDaoService service;
    private userRepository repository;

    public testController(userDaoService service) {
        this.service = service;
    }

//    @GetMapping("/")
//    public String testPage() {
//
//        return "/signup/login";
//    }
//
//    @GetMapping("/user/preRegistration")
//    public String viewPreRegistration() {
//
//        return "/signup/preRegistration";
//    }
//
////    @GetMapping("/user/registration")
//    @RequestMapping(value = "/user/registration", method = RequestMethod.GET)
//    public String viewRegistration(Model model, userVO user) {
//        model.addAttribute("user", user);
//        return "/signup/registration";
//    }
//
////    @RequestMapping(value = "/user/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    @RequestMapping(value = "/add", method = RequestMethod.GET)
//    public String createUser(@Valid @RequestBody user user) {
//        System.out.println(user.toString());
//        repository.save(user);
//
//        return "/afterlogin/mainPage";
//    }


}
