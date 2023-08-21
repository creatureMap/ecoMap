package ecobridge.EcologyMap.repository;

import ecobridge.EcologyMap.domain.CreatureLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CreatureLocationRepository extends JpaRepository<CreatureLocation, Long> {
    //'Creature_location' 엔티티와 상호작용하기 위한 메서드 포함. JpaRepository에서 제공하는 여러 메서드를 활용할 수 있음.
}

