package ecobridge.EcologyMap.repository;

import ecobridge.EcologyMap.domain.Creature_location;
import ecobridge.EcologyMap.domain.Main_Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MainCategoryRepository extends JpaRepository<Main_Category, Long> {

    Optional<Main_Category> findByMainCategoryName(String main_category_name);
}