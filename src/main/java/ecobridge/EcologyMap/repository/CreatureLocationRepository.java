package ecobridge.EcologyMap.repository;

import ecobridge.EcologyMap.domain.Creature_location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatureLocationRepository extends JpaRepository<Creature_location, Long> {
    //'Creature_location' 엔티티와 상호작용하기 위한 메서드 포함
}

