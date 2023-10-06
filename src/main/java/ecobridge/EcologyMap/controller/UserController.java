package ecobridge.EcologyMap.controller;

//회원가입 및 로그인

import ecobridge.EcologyMap.config.jwt.TokenProvider;
import ecobridge.EcologyMap.domain.User;
import ecobridge.EcologyMap.dto.CreateAccessTokenRequest;
import ecobridge.EcologyMap.dto.CreateAccessTokenResponse;
import ecobridge.EcologyMap.dto.UserDTO;
import ecobridge.EcologyMap.repository.UserRepository;
import ecobridge.EcologyMap.service.RefreshTokenService;
import ecobridge.EcologyMap.service.TokenService;
import ecobridge.EcologyMap.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final TokenService tokenService;
    private final TokenProvider tokenProvider;

    // 로그인 한 정보를 검증, 토큰 발급해서 db에 저장?, 저장된 토큰으로 계속 검증?
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> user) {
        User member = userRepository.findByUsername(user.get("username"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않음"));

        return tokenProvider.generateToken(member, Duration.ofHours(2));
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