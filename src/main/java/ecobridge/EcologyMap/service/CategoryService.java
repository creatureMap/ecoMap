package ecobridge.EcologyMap.service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import ecobridge.EcologyMap.domain.Creature;
import ecobridge.EcologyMap.domain.Creature_location;
import ecobridge.EcologyMap.domain.Main_Category;
import ecobridge.EcologyMap.repository.CategoryRepository;
import ecobridge.EcologyMap.repository.MainCategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Creature_location> findLocationsByMainCategory(String main_category) {

        List<Creature> creatures = categoryRepository.findByMainCategory_MainCategory(main_category);

        return creatures.stream()
                .flatMap(creature -> creature.getCreatureLocations().stream())
                .collect(Collectors.toList());
    }
}
