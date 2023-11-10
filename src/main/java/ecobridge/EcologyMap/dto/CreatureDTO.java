package ecobridge.EcologyMap.dto;

import ecobridge.EcologyMap.domain.Creature;
import ecobridge.EcologyMap.domain.CreatureLocation;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatureDTO { // 생물의 위치 정보를 전달하기 위한 데이터 전송 객체(DTO)

//    private Long creatureId;
    private double creatureLatitude;
    private double creatureLongitude;
    private Long locationId;
//    private Long mainCategoryId;
//    private Long detailCategoryId;
//    private Long creatureProtectionClass;
//    private String creatureName;



    static public CreatureDTO of(CreatureLocation location){
        return CreatureDTO.builder()
//                .creatureId(location.getCreature().getCreatureId())
                .creatureLatitude(location.getCreatureLatitude())
                .creatureLongitude(location.getCreatureLongitude())
                .locationId(location.getLocationId())
//                .mainCategoryId(location.getCreature().getMainCategory().getMainCategoryId())
//                .detailCategoryId(location.getCreature().getDetailCategory().getDetailCategoryId())
//                .creatureProtectionClass(location.getCreature().getCreatureProtectionClass())
//                .creatureName(location.getCreature().getCreatureName())
                .build();
    }
}
