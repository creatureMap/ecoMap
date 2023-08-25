package ecobridge.EcologyMap.dto;

import ecobridge.EcologyMap.domain.CreatureLocation;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatureDetailDTO { // 생물의 위치 정보를 전달하기 위한 데이터 전송 객체(DTO)

    private Long creatureId;
    private double creatureLatitude;
    private double creatureLongitude;
    private Long locationId;
    private Long mainCategoryId;
    private Long detailCategoryId;
    private Long creatureProtectionClass;
    private String creatureName;
    private String imageUrl;
    private boolean spring;
    private boolean summer;
    private boolean fall;
    private boolean winter;




    static public CreatureDetailDTO of(CreatureLocation location){
        return CreatureDetailDTO.builder()
                .creatureId(location.getCreature().getCreatureId())
                .creatureLatitude(location.getCreatureLatitude())
                .creatureLongitude(location.getCreatureLongitude())
                .locationId(location.getLocationId())
                .mainCategoryId(location.getCreature().getMainCategory().getMainCategoryId())
                .detailCategoryId(location.getCreature().getDetailCategory().getDetailCategoryId())
                .creatureProtectionClass(location.getCreature().getCreatureProtectionClass())
                .imageUrl(location.getCreature().getImageUrl())
                .creatureName(location.getCreature().getCreatureName())


                .build();
    }
}
