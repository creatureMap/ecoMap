package ecobridge.EcologyMap.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreatureQuiz{

    //생물 ID
    @Id
    @OneToOne
    @JoinColumn(name = "creature_id")
    private Creature creature;

    //퀴즈1
    @Column(name = "quiz1", nullable = false)
    private String quiz1;

    //퀴즈2
    @Column(name = "quiz2", nullable = false)
    private String quiz2;

    //퀴즈3
    @Column(name = "quiz3", nullable = false)
    private String quiz3;

    //퀴즈1 정답 O=1, X=0
    @Column(name = "quiz1Answer", nullable = false)
    private Byte quiz1Answer;

    //퀴즈2 정답 O=1, X=0
    @Column(name = "quiz2Answer", nullable = false)
    private Byte quiz2Answer;

    //퀴즈3 정답 O=1, X=0
    @Column(name = "quiz3Answer", nullable = false)
    private Byte quiz3Answer;

    //퀴즈1
    @Column(name = "quiz1Solution", nullable = false)
    private String quiz1Solution;

    //퀴즈2
    @Column(name = "quiz2Solution", nullable = false)
    private String quiz2Solution;

    //퀴즈3
    @Column(name = "quiz3Solution", nullable = false)
    private String quiz3Solution;
}
