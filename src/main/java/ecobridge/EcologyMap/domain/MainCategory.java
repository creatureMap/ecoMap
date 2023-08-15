package ecobridge.EcologyMap.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="main_category_id", updatable = false)
    private Long mainCategoryId;

    @Column(name="main_category_name", nullable = false)
    private String mainCategoryName;

    @OneToMany(mappedBy = "mainCategory")
    private List<Creature> creatures = new ArrayList<>();

    public Long getMain_category_id() {
        return mainCategoryId;
    }

    public void setMain_category_id(Long mainCategoryId) {
        this.mainCategoryId = mainCategoryId;
    }



=======
    @Column(name="Main_category_id", updatable = false)
    private Long mainCategoryId;

    @Column(name="Main_category_name", nullable = false)
    private String mainCategoryName;
>>>>>>> f28802824cadbda2bcadf2278dd50401f1e859db:src/main/java/ecobridge/EcologyMap/domain/MainCategory.java

}

