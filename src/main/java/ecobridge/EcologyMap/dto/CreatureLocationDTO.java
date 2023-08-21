package ecobridge.EcologyMap.dto;
import ecobridge.EcologyMap.domain.Creature;
import ecobridge.EcologyMap.domain.CreatureLocation;
import lombok.*;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatureLocationDTO {
    private long locationId;
    private double creatureLatitude;
    private double creatureLongitude;
    private long creatureId;


    static public CreatureLocationDTO of(CreatureLocation location){
        return CreatureLocationDTO.builder()
                .creatureId(location.getCreature().getCreatureId())
                .creatureLatitude(location.getCreatureLatitude())
                .creatureLongitude(location.getCreatureLongitude())
                .locationId(location.getLocationId())
                .build();
    }

    // getter, setter 등 필요한 메서드 작성
}
