package ecobridge.EcologyMap.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;



@Entity
@Getter //자동으로 GET메서드를 통해 필드 접근 가능
@NoArgsConstructor(access = AccessLevel.PROTECTED) //접근제어자가 protected인 기본생성자 코드없이 생성
public class Creature {

    //생물 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "creature_id", updatable = false)
    private Long creature_id;

    //생물 보호등급
    @Column(name = "creature_protection_class", nullable = false)
    private Long creature_protection_class;

    //생물 정보
    @Column(name = "creature_information", nullable = false)
    private String creature_information;


    //이미지 URL
    @Column(name = "image_url", nullable = false)
    private String image_url;

    //메인카테고리 ID
    @ManyToOne
    @JoinColumn(name = "main_category_id")
    private Main_Category category;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Detail_Category detail_category;

    //생물 이름
    @Column(name = "creature_name", nullable = false)
    private String creature_name;


    @Column(name = "spring", nullable = false)
    private boolean spring;

    @Column(name = "summer", nullable = false)
    private boolean summer;

    @Column(name = "fall", nullable = false)
    private boolean fall;

    @Column(name = "winter", nullable = false)
    private boolean winter;





    @Builder //빌더 패턴으로 객체 생성
    public Creature(String creature_information, String creature_name, Long creature_protection_class, String image_url) {
        this.creature_information = creature_information;
        this.creature_name = creature_name;
        this.creature_protection_class = creature_protection_class;
        this.image_url = image_url;
    }

}

