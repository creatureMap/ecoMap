package ecobridge.EcologyMap.dto;

import ecobridge.EcologyMap.domain.Creature;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor //기본 생성자 추가
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 추가
@Getter
public class AddCreatureRequest {

    private String creature_name;
    private String creature_information;

    public Creature toEntity() { // 생성자를 사용해 객체 생성
        return Creature.builder()
                .creature_name(creature_name)
                .creature_information(creature_information)
                .build();
    }
}
