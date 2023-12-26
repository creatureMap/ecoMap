package ecobridge.EcologyMap.service;

import ecobridge.EcologyMap.domain.CreatureQuiz;
import ecobridge.EcologyMap.dto.CreatureQuizDTO;
import ecobridge.EcologyMap.dto.CreatureQuizRequestDTO;
import ecobridge.EcologyMap.dto.CreatureQuizSolutionDTO;
import ecobridge.EcologyMap.repository.CreatureQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatureQuizService {

    private final CreatureQuizRepository creatureQuizRepository;

    @Autowired
    public CreatureQuizService(CreatureQuizRepository creatureQuizRepository) {
        this.creatureQuizRepository = creatureQuizRepository;
    }

    //생물의 퀴즈 정보 조회
    public CreatureQuizDTO getQuiz(long creatureId, Integer quizNumber){ //quizNumber : 퀴즈 번호(1,2,3)
        CreatureQuizDTO quizDTO = new CreatureQuizDTO();
        CreatureQuiz creatureQuiz = creatureQuizRepository.findByCreature_CreatureId(creatureId);

        String quiz = "";


        switch(quizNumber){
            case 1:
                quiz = creatureQuiz.getQuiz1();
                break;
            case 2:
                quiz = creatureQuiz.getQuiz2();
                break;
            case 3:
                quiz = creatureQuiz.getQuiz3();
                break;
        }
        return quizDTO.builder()
                .quiz(quiz)
                .build();
    }

    //사용자가 답한 퀴즈의 정답 여부 확인 후 반환
    public CreatureQuizSolutionDTO checkAnswer(CreatureQuizRequestDTO quizRequestDTO){
        CreatureQuizSolutionDTO quizSolution = new CreatureQuizSolutionDTO(); //반환할 객체 생성
        CreatureQuiz creatureQuiz = creatureQuizRepository.findByCreature_CreatureId(quizRequestDTO.getCreatureId()); //생물의 퀴즈 정보 조회
        String quizSolutionString=""; //정답 해설
        byte isCorrect = 0; //정답 여부
        int correctCount = quizRequestDTO.getCorrectCount(); //정답 개수
        switch(quizRequestDTO.getQuizNumber()){
            case 1:
                correctCount = 0;
                if(creatureQuiz.getQuiz1Answer() == quizRequestDTO.getUserAnswer()){
                    isCorrect = 1;
                    correctCount++;
                }
                else{
                    isCorrect = 0;
                }
                quizSolutionString = creatureQuiz.getQuiz1Solution();
                break;
            case 2:
                if(creatureQuiz.getQuiz2Answer() == quizRequestDTO.getUserAnswer()){
                    isCorrect = 1;
                    correctCount++;
                }
                else{
                    isCorrect = 0;
                }
                quizSolutionString = creatureQuiz.getQuiz2Solution();
                break;
            case 3:
                if(creatureQuiz.getQuiz3Answer() == quizRequestDTO.getUserAnswer()){
                    isCorrect = 1;
                    correctCount++;
                }
                else{
                    isCorrect = 0;
                }
                quizSolutionString = creatureQuiz.getQuiz3Solution();
                break;
        }
        quizSolution = quizSolution.builder()
                .isCorrect(isCorrect)
                .quizSolution(quizSolutionString)
                .correctCount(correctCount)
                .build();
        return quizSolution;
    }

}
