package ecobridge.EcologyMap.repository;

import ecobridge.EcologyMap.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByuserId(Long userId);
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
//