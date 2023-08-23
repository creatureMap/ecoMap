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
public class DetailCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="detail_category_id")
    private Long detailCategoryId;

    @Column(name="detail_category_name")
    private String detailCategoryName;

    @OneToMany(mappedBy = "detailCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Creature> creatures = new ArrayList<>();

    public Long getDetailCategoryId() {
        return detailCategoryId;
    }
}
