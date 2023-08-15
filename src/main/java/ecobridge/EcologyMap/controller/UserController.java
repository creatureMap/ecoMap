package ecobridge.EcologyMap.controller;

//회원가입 및 로그인

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/join")
    public @ResponseBody String test(){
        return "Join";
    }
}
