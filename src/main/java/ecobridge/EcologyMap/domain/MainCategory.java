package ecobridge.EcologyMap.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="main_category_id", nullable = false)
    private Long mainCategoryId;

    @Column(name="main_category_name", nullable = false)
    private String mainCategoryName;

    @OneToMany(mappedBy = "mainCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Creature> creatures = new ArrayList<>();

    public Long getMainCategoryId() {
        return mainCategoryId;
    }
}

