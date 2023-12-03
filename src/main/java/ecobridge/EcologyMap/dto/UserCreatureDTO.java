package ecobridge.EcologyMap.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreatureDTO {
    private Long userId;
    private Long creatureId;
    private int correctAnswers;
}
