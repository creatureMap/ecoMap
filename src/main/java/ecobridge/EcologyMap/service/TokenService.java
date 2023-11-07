package ecobridge.EcologyMap.service;

import ecobridge.EcologyMap.config.jwt.TokenProvider;
import ecobridge.EcologyMap.domain.RefreshToken;
import ecobridge.EcologyMap.domain.User;
import ecobridge.EcologyMap.dto.CreateAccessTokenResponse;
import ecobridge.EcologyMap.dto.UserDTO;
import ecobridge.EcologyMap.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final TokenProvider tokenProvider;
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;
    private final RefreshTokenRepository refreshTokenRepository;

    //리프레시 토큰 검증 및 유저 정보 조회, 새로운 엑세스 토큰 발급
    public String createNewAccessToken(String refreshToken) {
        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("unexpected token");
        }

        // 토큰에서 유저 정보 조회
        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();

        // 토큰의 유저 정보를 데이터베이스에서 조회
        User user = userService.findById(userId);

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }

    public void save(Long userId, String refreshToken) {
        RefreshToken newRefreshToken = new RefreshToken(userId, refreshToken);
        refreshTokenRepository.save(newRefreshToken);
    }
}
