package ecobridge.EcologyMap.dto;

import ecobridge.EcologyMap.domain.Creature;
import lombok.Getter;


// 응답을 위한 DTO 작성
@Getter
public class CreatureResponse {

    private final String creature_information;
    private final String creature_name;
    private final Long creature_protection_class;
    private final String image_url;

    public CreatureResponse(Creature creature) { //Creature 엔티티를 인수로 받는 생성자
        this.creature_information = creature.getCreatureInformation();
        this.creature_name = creature.getCreatureName();
        this.creature_protection_class = creature.getCreatureProtectionClass();
        this.image_url = creature.getImageUrl();
    }

}


