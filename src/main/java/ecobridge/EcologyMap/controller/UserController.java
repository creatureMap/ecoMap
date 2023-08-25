package ecobridge.EcologyMap.controller;

//회원가입 및 로그인

import ecobridge.EcologyMap.dto.UserDTO;
import ecobridge.EcologyMap.service.TokenService;
import ecobridge.EcologyMap.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final TokenService tokenService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> createNewAccessToken(
            @RequestBody UserDTO request) {
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());
        System.out.println(newAccessToken);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new UserDTO("hi","hello","hihi",newAccessToken));
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }
}