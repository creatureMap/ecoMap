package ecobridge.EcologyMap.repository;

import ecobridge.EcologyMap.domain.Creature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatureRepository extends JpaRepository<Creature, Long> {
    //'Creature' 엔티티와 상호작용하기 위한 메서드 포함
    // 상호작용?
    // - 데이터베이스 와 애플리케이션 내의 엔티티(데이터 객체) 간의 데이터 처리 작업
    // - CRUD
    // - save(), findById(), findAll(). deleteById(). delete() ...
}
