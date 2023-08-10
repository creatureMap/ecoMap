package ecobridge.EcologyMap.dto;


import ecobridge.EcologyMap.domain.Creature;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter

public class CreatureLocationDTO {

    private double creatureLatitude;
    private double creatureLongitude;


    public CreatureLocationDTO(Long creatureId, double creatureLatitude, double creatureLongitude, String creatureName, String locationName) {

        this.creatureLatitude = creatureLatitude;
        this.creatureLongitude = creatureLongitude;

    }


}
