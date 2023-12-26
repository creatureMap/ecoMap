package ecobridge.EcologyMap.dto;

import lombok.Data;

@Data
public class CreatureQuizRequestDTO {
    private long creatureId; //생물 ID
    private int quizNumber; //퀴즈 번호
    private int correctCount; //정답 개수
    private byte userAnswer; //사용자가 답한 정답
}
