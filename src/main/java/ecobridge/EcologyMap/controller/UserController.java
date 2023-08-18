//package ecobridge.EcologyMap.controller;
//
////회원가입 및 로그인
//
//import ecobridge.EcologyMap.dto.AddUserRequest;
//import ecobridge.EcologyMap.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/user")
//public class UserController {
//    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
//    private final UserService userService;
//
//    @GetMapping("/join")
//    public @ResponseBody String test(){
//        return "Join";
//    }
//
//    @PostMapping("/user")
//    public String signup(AddUserRequest request) {
//        userService.save(request);
//        return "redirect:/login";
//    }
//}
