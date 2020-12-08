package com.dude.dudeproject.Controller;

import com.dude.dudeproject.Repository.userRepository;
import com.dude.dudeproject.Service.userDaoService;
import org.springframework.stereotype.Controller;



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
