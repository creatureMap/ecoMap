//package ecobridge.EcologyMap.controller;
//
//import ecobridge.EcologyMap.dto.CreateAccessTokenRequest;
//import ecobridge.EcologyMap.dto.CreateAccessTokenResponse;
//import ecobridge.EcologyMap.service.TokenService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequiredArgsConstructor
//@RestController
//public class TokenController {
//    private final TokenService tokenService;
//
//    @PostMapping("/api/token")
//    public ResponseEntity<CreateAccessTokenResponse> createNewAccessToken
//            (@RequestBody CreateAccessTokenRequest request) {
//        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());
//
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(new CreateAccessTokenResponse(newAccessToken));
//    }
//}
