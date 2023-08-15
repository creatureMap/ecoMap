package ecobridge.EcologyMap.service;

import ecobridge.EcologyMap.domain.Creature;
import ecobridge.EcologyMap.domain.CreatureLocation;
import ecobridge.EcologyMap.dto.CreatureDTO;

import ecobridge.EcologyMap.repository.CreatureLocationRepository;
import ecobridge.EcologyMap.repository.CreatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.ArrayList;
import java.util.List;





@Service //빈으로 등록
public class CreatureService {

    private final CreatureRepository creatureRepository;
    private final CreatureLocationRepository creatureLocationRepository;

    @Autowired
    public CreatureService(CreatureRepository creatureRepository, CreatureLocationRepository creatureLocationRepository) {
        this.creatureRepository = creatureRepository;
        this.creatureLocationRepository = creatureLocationRepository;
    }


    /*
     * 전체 list 마커 찍기 -  DB 에서 생물의 위치 정보를 가져와 'CreatureDTO' 객체들을 생성하여 반환하는 메서드
     */
    public List<CreatureDTO> getAllCreatureLocationsWithId() {
        //findAll() 호출 -> DB 에서 모든 'Creature_location' 엔티티를 가져오기
        List<CreatureLocation> locations = creatureLocationRepository.findAll();
        List<CreatureDTO> creatureDTOs = new ArrayList<>();

        //가져온 위치 정보(Creature_location 엔티티 = locations)들을 순회하면서
        for (CreatureLocation location : locations) {
            Creature creature = location.getCreature(); //생물 정보를 바로 가져옵니다

            //해당 위치에 연관된 생물 정보가 있다면, 생물들의 위치정보를 dto 에 저장
            if (creature != null) {
                CreatureDTO dto = new CreatureDTO();  //'CreatureDTO' 객체 생성

                dto.setCreatureLatitude(location.getCreatureLatitude()); //위도 정보 설정
                dto.setCreatureLongitude(location.getCreatureLongitude()); //경도 정보 설정

                dto.setCreatureId(creature.getCreatureId()); //생물의 ID 설정
                dto.setCreatureLatitude(location.getCreatureLatitude()); //위도 정보 설정
                dto.setCreatureLongitude(location.getCreatureLongitude()); //경도 정보 설정



                creatureDTOs.add(dto); //위치 정보 설정 후 'creatureDTOs' 리스트에 추가.
            }
        }

        //모든 위치 정보에 대해 반복 수행 후, 생성된 'creatureDTOs' 리스트 반환.
        return creatureDTOs;
    }

    // creature_location의 ID를 이용해 생물을 조회-> 핀에는 creature_location 정보가 들어있기 때문에 creature가 아닌 creature_location 활용
    public CreatureLocation findCreatureDetail(long id){
        return creatureLocationRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found: "+id));

    }



    public List<CreatureDTO> getCategoryCreatureLocations(Long mainCategoryId) {
            List<CreatureLocation> locations = creatureLocationRepository.findAll();
            List<CreatureDTO> creatureDTOs = new ArrayList<>();

            for (CreatureLocation location : locations) {
                Creature creature = location.getCreature();
                if (creature != null && creature.getMainCategory().getMain_category_id().equals(mainCategoryId)) {
                    CreatureDTO dto = new CreatureDTO();

                    dto.setCreatureId(creature.getCreatureId());
                    dto.setCreatureLatitude(location.getCreatureLatitude());
                    dto.setCreatureLongitude(location.getCreatureLongitude());
                    dto.setLocationId(location.getLocationId());

                    creatureDTOs.add(dto);
                }
            }

            return creatureDTOs;
        }

}


