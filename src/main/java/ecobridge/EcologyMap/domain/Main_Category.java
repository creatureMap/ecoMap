package ecobridge.EcologyMap.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Main_Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="main_category_id", updatable = false)
    private Long main_category_id;

    @Column(name="main_category_name", nullable = false)
    private String main_category_name;

    //@OneToMany(mappedBy = "mainCategory")
    //private List<Creature> creatures;

}

