package ecobridge.EcologyMap.controller;

//회원가입 및 로그인

import ecobridge.EcologyMap.config.jwt.TokenProvider;
import ecobridge.EcologyMap.domain.User;
import ecobridge.EcologyMap.dto.UserDTO;
import ecobridge.EcologyMap.repository.UserRepository;
import ecobridge.EcologyMap.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenProvider tokenProvider;

    // 토큰 발급해서 db에 저장, 저장된 토큰으로 계속 검증..........
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> user) {
        User member = userRepository.findByUsername(user.get("username"))
                .orElseThrow(() -> new IllegalArgumentException("가입하지 않은 사용자입니다."));

        if(!bCryptPasswordEncoder.matches(user.get("password"), member.getPassword())) {
            return ResponseEntity.badRequest().body("잘못된 비밀번호 입니다.");
        }

         return ResponseEntity.ok(tokenProvider.generateToken(member, Duration.ofHours(2)));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody UserDTO request) {
        try{
            userService.save(request);
            return ResponseEntity.ok("success");
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }
}