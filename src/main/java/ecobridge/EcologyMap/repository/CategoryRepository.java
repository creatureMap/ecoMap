package ecobridge.EcologyMap.repository;

import ecobridge.EcologyMap.domain.Creature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Creature,Long> {
    List<Creature> findByCategory(String category);
}
