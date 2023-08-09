//package ecobridge.EcologyMap.repository;
//
//import ecobridge.EcologyMap.domain.Creature;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//
//import java.util.List;
//import java.util.Optional;
//
//public interface CreatureRepository extends JpaRepository<Creature, Long> {
//    static Optional<Creature> findById(Long creature_id);
//    Optional<Creature> findByName(String creature_name);
//
//    static List<Creature> findAll();
//
//}
