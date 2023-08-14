package ecobridge.EcologyMap.controller;

import ecobridge.EcologyMap.domain.Creature_location;
import ecobridge.EcologyMap.dto.CreatureDTO;
import ecobridge.EcologyMap.service.CreatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //HTTP Response Body 에 객체 데이터를 JSON 형싟으로 변환하는 컨트롤러
@RequestMapping("/api")  // '/api' 로 시작하는 URL 을 처리한다는 의미
public class CreatureController {
    private final CreatureService creatureService;

    @Autowired //생성자는  CreatureService 객체를 주입받는다 - 조히 메서드 사용 가능
    public CreatureController(CreatureService creatureService) {
        this.creatureService = creatureService;
    }

    @GetMapping("/creatures")
    public ResponseEntity<List<CreatureDTO>> getAllCreatureLocations() {
        List<CreatureDTO> creatureDTOs = creatureService.getAllCreatureLocations(); //CreatureService 클래스의 메서드를 호출하여 모든 생물의 위치 정보를 조회
        return new ResponseEntity<>(creatureDTOs, HttpStatus.OK); //반환된 'CreatureDTO' 객체 리스트를 'CreatureDTOs' 에 저장.
    }


    @GetMapping("/cratures/{id}")
    public ResponseEntity<Creature_location> getCreatureDetail(@PathVariable long id){
        Creature_location creatureLocation = creatureService.findCreatureDetail(id);

        return ResponseEntity.ok()
                .body(creatureLocation);
    }


}
