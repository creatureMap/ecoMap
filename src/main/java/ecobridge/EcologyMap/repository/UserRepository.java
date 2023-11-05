package ecobridge.EcologyMap.repository;

import ecobridge.EcologyMap.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //아이디로 사용자를 식별하여 정보를 가져옴
    Optional<User> findByUsername(String username);
}
