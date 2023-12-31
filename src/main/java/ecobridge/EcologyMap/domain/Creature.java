package ecobridge.EcologyMap.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter//자동으로 GET메서드를 통해 필드 접근 가능
@NoArgsConstructor(access = AccessLevel.PROTECTED) //접근제어자가 protected인 기본생성자 코드없이 생성
public class Creature {

        //생물 ID
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "creature_id", updatable = false)
        private Long creatureId;

        //생물 보호등급
        @Column(name = "creature_protection_class", nullable = false)
        private Long creatureProtectionClass;

        //생물 정보
        @Column(name = "creature_information", nullable = false, length=1000)
        private String creatureInformation;

        //생물 요약 정보
        @Column(name = "creature_summary_information", nullable = false, length=1000)
        private String creatureSummaryInformation;

        //이미지 URL
        @Column(name = "image_url", nullable = false)
        private String imageUrl;

        //메인카테고리 ID
        @ManyToOne
        @JoinColumn(name = "main_category_id")
        private MainCategory mainCategory;

        @ManyToOne
        @JoinColumn(name = "detail_category_id")
        private DetailCategory detailCategory;

        //생물 이름
        @Column(name = "creature_name", nullable = false)
        private String creatureName;

        @Column(name = "spring", nullable = false)
        private boolean spring;

        @Column(name = "summer", nullable = false)
        private boolean summer;

        @Column(name = "fall", nullable = false)
        private boolean fall;

        @Column(name = "winter", nullable = false)
        private boolean winter;


}

