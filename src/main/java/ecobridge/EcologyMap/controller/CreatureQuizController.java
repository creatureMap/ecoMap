package ecobridge.EcologyMap.controller;

import ecobridge.EcologyMap.dto.CreatureQuizDTO;
import ecobridge.EcologyMap.dto.CreatureQuizRequestDTO;
import ecobridge.EcologyMap.dto.CreatureQuizSolutionDTO;
import ecobridge.EcologyMap.service.CreatureQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //HTTP Response Body 에 객체 데이터를 JSON 형싟으로 변환하는 컨트롤러
@RequestMapping("/api")  // '/api' 로 시작하는 URL 을 처리한다는 의미
public class CreatureQuizController {
    private final CreatureQuizService creatureQuizService;

    @Autowired //생성자는  CreatureService 객체를 주입받는다 - 조히 메서드 사용 가능
    public CreatureQuizController(CreatureQuizService creatureQuizService) {
        this.creatureQuizService = creatureQuizService;
    }

    @GetMapping("/creatureQuiz/{creatureId}/{quizNumber}")
    public ResponseEntity<CreatureQuizDTO> getQuiz(@PathVariable Long creatureId, @PathVariable Integer quizNumber){
        CreatureQuizDTO quiz = creatureQuizService.getQuiz(creatureId,quizNumber);
        return ResponseEntity.ok(quiz);
    }

    @PostMapping("/creatureQuiz/{creatureId}/{quizNumber}")
    public ResponseEntity<CreatureQuizSolutionDTO> checkAnswer(@RequestBody CreatureQuizRequestDTO quizRequestDTO){
        CreatureQuizSolutionDTO quizSolution = creatureQuizService.checkAnswer(quizRequestDTO);
        return ResponseEntity.ok(quizSolution);
    }

}
