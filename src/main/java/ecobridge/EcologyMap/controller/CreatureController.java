package ecobridge.EcologyMap.controller;


import ecobridge.EcologyMap.domain.Creature;
import ecobridge.EcologyMap.domain.CreatureLocation;
import ecobridge.EcologyMap.dto.CreatureDTO;
import ecobridge.EcologyMap.dto.CreatureLocationDTO;
import ecobridge.EcologyMap.repository.CreatureRepository;
import ecobridge.EcologyMap.service.CreatureService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController //HTTP Response Body 에 객체 데이터를 JSON 형싟으로 변환하는 컨트롤러
@RequestMapping("/api")  // '/api' 로 시작하는 URL 을 처리한다는 의미
public class CreatureController {
    private final CreatureService creatureService;

    @Autowired //생성자는  CreatureService 객체를 주입받는다 - 조히 메서드 사용 가능
    public CreatureController(CreatureService creatureService) {
        this.creatureService = creatureService;
    }

    @Autowired
    CreatureRepository creatureRepository;

    @GetMapping("/creatures")
    public ResponseEntity<List<CreatureDTO>> getAllCreatureLocationsWithId() {
        List<CreatureDTO> creatureDTOs = creatureService.getAllCreatureLocationsWithId(); //CreatureService 클래스의 메서드를 호출하여 모든 생물의 위치 정보를 조회
        return ResponseEntity.ok(creatureDTOs); //반환된 'CreatureDTO' 객체 리스트를 'CreatureDTOs' 에 저장.
    }


    @GetMapping("/creatures/detail/{id}")
    public ResponseEntity<CreatureLocation> getCreatureDetail(@PathVariable long id){
        CreatureLocation creatureLocation = creatureService.findCreatureDetail(id);

        return ResponseEntity.ok()
                .body(creatureLocation);
    }

    @GetMapping("/creatures/{mainCategoryId}")
    public ResponseEntity<List<CreatureDTO>> getCategoryCreatureLocations(@PathVariable long mainCategoryId) {
        List<CreatureDTO> creatureDTOs = creatureService.getCategoryCreatureLocations(mainCategoryId);
        return ResponseEntity.ok(creatureDTOs);
    }

    @GetMapping("/creatures/{mainCategoryId}/{detailCategoryId}")
    public ResponseEntity<List<CreatureDTO>> getDetailCategoryCreatureLocations(@PathVariable Long mainCategoryId,
                                                                                @PathVariable Long detailCategoryId) {
        List<CreatureDTO> creatureDTOs = creatureService.getDetailCategoryCreatureLocations(mainCategoryId, detailCategoryId);
        return ResponseEntity.ok(creatureDTOs);
    }

    @GetMapping("/creatures/SearchName/{creatureName}")
    public ResponseEntity<List<CreatureLocationDTO>> getSearchCreatureDetails(@PathVariable String creatureName){
        List<CreatureLocationDTO> creatureLocationDTOS = creatureService.getSearchCreatureDetails(creatureName);
        return ResponseEntity.ok(creatureLocationDTOS);
    }

    @GetMapping("/creatures/SearchDetailCategoryName/{detailCategoryName}")
    public ResponseEntity<List<CreatureDTO>> getSearchDetailCategory(@PathVariable String detailCategoryName){
        List<CreatureDTO> creatureDTOS = creatureService.getSearchDetailCategory(detailCategoryName);
        return ResponseEntity.ok(creatureDTOS);
    }
}
