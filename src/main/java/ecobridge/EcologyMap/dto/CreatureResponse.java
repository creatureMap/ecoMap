package ecobridge.EcologyMap.dto;

import ecobridge.EcologyMap.domain.Creature;
import ecobridge.EcologyMap.domain.Main_Category;
import lombok.Getter;


// 응답을 위한 DTO 작성
@Getter
public class CreatureResponse {

    private final String creature_information;
    private final String creature_name;
    private final Long creature_protection_class;
    private final String image_url;

    public CreatureResponse(Creature creature) { //Creature 엔티티를 인수로 받는 생성자
        this.creature_information = creature.getCreature_information();
        this.creature_name = creature.getCreature_name();
        this.creature_protection_class = creature.getCreature_protection_class();
        this.image_url = creature.getImage_url();
    }

}


