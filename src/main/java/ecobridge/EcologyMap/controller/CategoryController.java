package ecobridge.EcologyMap.controller;

import ecobridge.EcologyMap.domain.Creature;
import ecobridge.EcologyMap.domain.Creature_location;
import ecobridge.EcologyMap.domain.Main_Category;
import ecobridge.EcologyMap.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/creatures")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/locations")
    public ResponseEntity<List<Creature_location>> getCreatureLocationsByMainCategory(@RequestParam("main_category_name") String main_category_name) {
        List<Creature_location> creatureLocations = categoryService.findLocationsByMainCategory(main_category_name);
        return ResponseEntity.ok(creatureLocations);
    }
}



