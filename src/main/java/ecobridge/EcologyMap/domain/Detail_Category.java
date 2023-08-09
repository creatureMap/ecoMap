package ecobridge.EcologyMap.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Detail_Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Category_id")
    private Long category_id;

    @Column(name="Category_name")
    private String category_name;

}
