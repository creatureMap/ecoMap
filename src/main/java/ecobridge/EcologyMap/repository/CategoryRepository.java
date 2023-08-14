package ecobridge.EcologyMap.repository;

import ecobridge.EcologyMap.domain.Creature;
import ecobridge.EcologyMap.domain.Main_Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Creature, Long> {

    List<Creature> findByMainCategory_MainCategory(String main_category);
}


