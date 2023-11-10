package ecobridge.EcologyMap.service;

import ecobridge.EcologyMap.domain.RefreshToken;
import ecobridge.EcologyMap.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    //리프레시 토큰을 찾고, 해당 토큰이 없으면 예외 발생
    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("unexpected token"));
    }
}
