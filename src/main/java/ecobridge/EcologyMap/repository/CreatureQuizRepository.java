package ecobridge.EcologyMap.repository;

import ecobridge.EcologyMap.domain.CreatureQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatureQuizRepository extends JpaRepository<CreatureQuiz, Long> {
    //'CreatureQuiz' 엔티티와 상호작용하기 위한 메서드 포함
    // - 데이터베이스 와 애플리케이션 내의 엔티티(데이터 객체) 간의 데이터 처리 작업
    // - CRUD
    // - save(), findById(), findAll(). deleteById(). delete() ...
    //List<Creature> findByCategory_MainCategoryId(Long main_category_id);
    CreatureQuiz findByCreature_CreatureId(Long creatureId);
}