package ecobridge.EcologyMap.repository;

import ecobridge.EcologyMap.domain.Creature_location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryLocationRepository extends JpaRepository<Creature_location, Long> {
    List<Creature_location> findByCreatureCategory(String category);
}
