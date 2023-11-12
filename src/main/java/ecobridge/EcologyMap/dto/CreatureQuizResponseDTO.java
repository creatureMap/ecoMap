package ecobridge.EcologyMap.dto;

import lombok.Data;

@Data
public class CreatureQuizResponseDTO {
    private Long creatureId;
    private Integer quizNumber;
    private Byte userAnswer;
}
