package ecobridge.EcologyMap.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Main_Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Main_category_id", updatable = false)
    private Long Main_category_id;

    @Column(name="Main_category_name", nullable = false)
    private String Main_category_name;

}

