/*package ecobridge.EcologyMap.service;

import ecobridge.EcologyMap.config.jwt.TokenProvider;
import ecobridge.EcologyMap.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final TokenProvider tokenProvider;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken) {
        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("unexpected token");
        }

        Long id = userService.findByRefreshToken(refreshToken).getId();
        User user = userService.findById(id);

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
*/