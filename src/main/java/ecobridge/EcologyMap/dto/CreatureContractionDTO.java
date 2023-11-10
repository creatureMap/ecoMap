package ecobridge.EcologyMap.dto;
import ecobridge.EcologyMap.domain.CreatureLocation;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatureContractionDTO {

    private Long creatureId;
    private String creatureName;
    private String imageUrl;
    private String mainCategoryName;
    private String detailCategoryName;
    private String creatureSummaryInformation;

    static public CreatureDetailDTO of(CreatureLocation location){
        return CreatureDetailDTO.builder()
                .creatureId(location.getCreature().getCreatureId())
                .mainCategoryName(location.getCreature().getMainCategory().getMainCategoryName())
                .detailCategoryName(location.getCreature().getDetailCategory().getDetailCategoryName())
                .imageUrl(location.getCreature().getImageUrl())
                .creatureName(location.getCreature().getCreatureName())
                .creatureSummaryInformation(location.getCreature().getCreatureSummaryInformation())
                .build();
    }
}

