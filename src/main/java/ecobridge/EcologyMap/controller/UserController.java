package ecobridge.EcologyMap.controller;

//회원가입 및 로그인

import ecobridge.EcologyMap.domain.BiologyEncyclopedia;
import ecobridge.EcologyMap.domain.User;
import ecobridge.EcologyMap.dto.BiologyEncyclopediaDTO;
import ecobridge.EcologyMap.dto.UserDTO;
import ecobridge.EcologyMap.service.BiologyEncyclopediaService;
import ecobridge.EcologyMap.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    BiologyEncyclopediaService biologyEncyclopediaService;

    @GetMapping("/login")
    public String login() {
        return "login";
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
    public List<BiologyEncyclopediaDTO> getUserCreaturesByDetailCategoryName(@PathVariable Long userId, @PathVariable String detailCategoryName){
        return biologyEncyclopediaService.getUserCreaturesByDetailCategoryName(userId,detailCategoryName);
    }

    //사용자 도감에 생물을 추가하는 api
    @PostMapping("/{userId}/Encyclopedia/{creatureId}/{correctAnswers}")
    public ResponseEntity<Boolean> addCreatureToUser(@PathVariable Long userId, @PathVariable Long creatureId, @PathVariable int correctAnswers) {
        if(correctAnswers >= 2) {

            try {
                BiologyEncyclopedia biology = biologyEncyclopediaService.addUserCreature(userId, creatureId);
                return ResponseEntity.ok(biology != null);
            } catch (Exception e) {
                logger.error("Failed to add creature to user's encyclopedia", e);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
            }
        }
        else {
            return ResponseEntity.ok(false);
        }
    }


}