package ecobridge.EcologyMap.controller;

import ecobridge.EcologyMap.domain.Creature;
import ecobridge.EcologyMap.dto.CreatureLocationDTO;
import ecobridge.EcologyMap.repository.CategoryLocationRepository;
import ecobridge.EcologyMap.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryLocationRepository categoryLocationRepository;

    @GetMapping("/creatures/{category}")
    public ResponseEntity<List<CreatureLocationDTO>> getCreatureLocationsByCategory(@PathVariable String category) {
        List<Creature> creatures = categoryRepository.findByCategory(category);

        List<CreatureLocationDTO> creatureLocationDTOs = new ArrayList<>();
        for (Creature creature : creatures) {

            CreatureLocation creatureLocation = creature.getCreatureLocation();

            creatureLocationDTOs.add(new CreatureLocationDTO(

                    creatureLocation.getCreatureLatitude(),
                    creatureLocation.getCreatureLongitude()


            ));
        }

        return ResponseEntity.ok(creatureLocationDTOs);
    }
}



