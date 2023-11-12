package ecobridge.EcologyMap.repository;

import ecobridge.EcologyMap.domain.BiologyEncyclopedia;
import ecobridge.EcologyMap.domain.Creature;
import ecobridge.EcologyMap.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BiologyEncyclopediaRepository extends JpaRepository<BiologyEncyclopedia,Long> {
    List<BiologyEncyclopedia> findByUserId(Long userId);
    boolean existsByUserAndCreature(User user, Creature creature);
}
