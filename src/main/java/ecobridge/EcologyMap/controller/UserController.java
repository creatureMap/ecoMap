package ecobridge.EcologyMap.controller;

//회원가입 및 로그인


<<<<<<< HEAD
import ecobridge.EcologyMap.domain.BiologyEncyclopedia;
import ecobridge.EcologyMap.domain.User;
import ecobridge.EcologyMap.dto.BiologyEncyclopediaDTO;
import ecobridge.EcologyMap.dto.UserCreatureDTO;

import ecobridge.EcologyMap.config.jwt.TokenProvider;
import ecobridge.EcologyMap.domain.User;

=======
import ecobridge.EcologyMap.config.jwt.TokenProvider;
import ecobridge.EcologyMap.domain.BiologyEncyclopedia;
import ecobridge.EcologyMap.domain.User;
import ecobridge.EcologyMap.dto.BiologyEncyclopediaDTO;
>>>>>>> 5af3d2de74ebce386a1215c745b2aafb392e9b6f
import ecobridge.EcologyMap.dto.UserDTO;
import ecobridge.EcologyMap.repository.UserRepository;
import ecobridge.EcologyMap.service.BiologyEncyclopediaService;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;
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
    private final BiologyEncyclopediaService biologyEncyclopediaService;
    private final TokenService tokenService;

    // 토큰 발급해서 db에 저장, 저장된 토큰으로 계속 검증..........
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> user) {
        User member = userRepository.findByUsername(user.get("username"))
                .orElseThrow(() -> new IllegalArgumentException("가입하지 않은 사용자입니다."));

        if (!bCryptPasswordEncoder.matches(user.get("password"), member.getPassword())) {
            return ResponseEntity.badRequest().body("잘못된 비밀번호 입니다.");
        }

        Long userId = member.getId();
        String token = tokenProvider.generateToken(member, Duration.ofHours(2));

        // db에 저장
        tokenService.save(userId, token);

        //만든 token body로 보내주기
        return ResponseEntity.ok(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody UserDTO request) {
        try {
            userService.save(request);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }

    //사용자에 따라서 발견한 생물들의 정보를 확인하는 api
    @GetMapping("/{userId}/Encyclopedia")
    public List<BiologyEncyclopediaDTO> getUserCreatures(@PathVariable Long userId) {
        return biologyEncyclopediaService.getUserCreatures(userId);
    }

    //사용자에 따라서 발견한 생물들의 카테고리를 지정해서 정보를 확인하는 api
    @GetMapping("/{userId}/Encyclopedia/{detailCategoryName}")
    public List<BiologyEncyclopediaDTO> getUserCreaturesByDetailCategoryName(@PathVariable Long userId, @PathVariable String detailCategoryName) {
        return biologyEncyclopediaService.getUserCreaturesByDetailCategoryName(userId, detailCategoryName);
    }



    //사용자 도감에 생물을 추가하는 api
    @PostMapping("/addEncyclopedia")
    public ResponseEntity<Boolean> addCreatureToUser(@RequestBody UserCreatureDTO info) {
        if(info.getCorrectAnswers() >= 2) {


    //사용자 도감에 생물을 추가하는 api
    @PostMapping("/{userId}/Encyclopedia/{creatureId}/{correctAnswers}")
    public ResponseEntity<Boolean> addCreatureToUser(@PathVariable Long userId, @PathVariable Long creatureId, @PathVariable int correctAnswers) {
        if (correctAnswers >= 2) {


            try {
                BiologyEncyclopedia biology = biologyEncyclopediaService.addUserCreature(info.getUserId(), info.getCreatureId());
                return ResponseEntity.ok(biology != null);
            } catch (Exception e) {
                logger.error("Failed to add creature to user's encyclopedia", e);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
            }
        } else {
            return ResponseEntity.ok(false);
        }
    }
}
g