package ecobridge.EcologyMap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatureQuizSolutionDTO {
    private Byte isCorrect; //정답 여부 O=1, X=0
    private String quizSolution; //퀴즈 해설
}


