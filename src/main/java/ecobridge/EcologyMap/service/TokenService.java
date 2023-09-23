//package ecobridge.EcologyMap.service;
//
//import ecobridge.EcologyMap.config.jwt.TokenProvider;
//import ecobridge.EcologyMap.domain.User;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.Duration;
//
//@RequiredArgsConstructor
//@Service
//public class TokenService {
//    private final TokenProvider tokenProvider;
//    private final UserService userService;
//    private final RefreshTokenService refreshTokenService;
//
//    public String createNewAccessToken(String refreshToken) {
//        if(!tokenProvider.validToken(refreshToken)) {
//            throw new IllegalArgumentException("unexpected token");
//        }
//
//        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
//        User user = userService.findById(userId);
//
//        return tokenProvider.generateToken(user, Duration.ofHours(2));
//    }
//}