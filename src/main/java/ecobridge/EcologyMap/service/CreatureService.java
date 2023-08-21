package ecobridge.EcologyMap.service;

import ecobridge.EcologyMap.domain.Creature;
import ecobridge.EcologyMap.domain.CreatureLocation;
import ecobridge.EcologyMap.dto.CreatureDTO;

import ecobridge.EcologyMap.dto.CreatureLocationDTO;
import ecobridge.EcologyMap.repository.CreatureLocationRepository;
import ecobridge.EcologyMap.repository.CreatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service //빈으로 등록
@RequiredArgsConstructor
public class CreatureService {

    private final CreatureLocationRepository creatureLocationRepository;
    /*
     * 전체 list 마커 찍기 -  DB 에서 생물의 위치 정보를 가져와 'CreatureDTO' 객체들을 생성하여 반환하는 메서드
     */
    public List<CreatureDTO> getAllCreatureLocationsWithId() {
        //findAll() 호출 -> DB 에서 모든 'Creature_location' 엔티티를 가져오기
        List<CreatureLocation> locations = creatureLocationRepository.findAll();
        // locations를 creatureDTO로 변환
        List<CreatureDTO> creatureDTOs = locations.stream().map(CreatureDTO::of).collect(Collectors.toList());

        return creatureDTOs;
    }

    // creature_location의 ID를 이용해 생물을 조회-> 핀에는 creature_location 정보가 들어있기 때문에 creature가 아닌 creature_location 활용
    public CreatureLocation findCreatureDetail(long id){
        return creatureLocationRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found: "+id));

    }


    public List<CreatureDTO> getCategoryCreatureLocations(Long mainCategoryId) {
        List<CreatureLocation> locations = creatureLocationRepository.findAll();
        locations = locations.stream().filter(location->
                location.getCreature().getMainCategory().getMainCategoryId().equals(mainCategoryId)).collect(Collectors.toList());
        List<CreatureDTO> creatureDTOs = locations.stream().map(CreatureDTO::of).collect(Collectors.toList());

        return creatureDTOs;
    }

    public List<CreatureDTO> getDetailCategoryCreatureLocations(Long mainCategoryId, Long detailCategoryId) {
        List<CreatureLocation> locations = creatureLocationRepository.findAll();
        locations = locations.stream()
                .filter(location-> location.getCreature().getMainCategory().getMainCategoryId().equals(mainCategoryId))
                .filter(location-> location.getCreature().getDetailCategory().getDetailCategoryId().equals(detailCategoryId))
                .collect(Collectors.toList());
        List<CreatureDTO> creatureDTOs = locations.stream().map(CreatureDTO::of).collect(Collectors.toList());

        return creatureDTOs;
    }

    public List<CreatureLocationDTO> getSearchCreatureDetails (String creatureName) {
        List<CreatureLocation> locations = creatureLocationRepository.findAll();
        locations = locations.stream()
                .filter(location-> location.getCreature().getCreatureName().equals(creatureName))
                .collect(Collectors.toList());
        List<CreatureLocationDTO> creatureLocationDTOs = locations.stream().map(CreatureLocationDTO::of).collect(Collectors.toList());

        return creatureLocationDTOs;
    }


}


