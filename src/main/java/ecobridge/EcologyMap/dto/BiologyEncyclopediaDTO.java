package ecobridge.EcologyMap.dto;

import ecobridge.EcologyMap.domain.BiologyEncyclopedia;
import ecobridge.EcologyMap.domain.CreatureLocation;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BiologyEncyclopediaDTO {
    private Long userId;
    private Long creatureId;
    private String creatureName;
    private String detailCategoryName;
    private String creatureInformation;
    private String creatureSummaryInformation;
    private String imageUrl;
    static public BiologyEncyclopediaDTO of(BiologyEncyclopedia biologyEncyclopedia){
        return BiologyEncyclopediaDTO.builder()
                .userId(biologyEncyclopedia.getUser().getId())
                .creatureId(biologyEncyclopedia.getCreature().getCreatureId())
                .creatureName(biologyEncyclopedia.getCreature().getCreatureName())
                .detailCategoryName(biologyEncyclopedia.getCreature().getDetailCategory().getDetailCategoryName())
                .creatureInformation(biologyEncyclopedia.getCreature().getCreatureInformation())
                .creatureSummaryInformation(biologyEncyclopedia.getCreature().getCreatureSummaryInformation())
                .imageUrl(biologyEncyclopedia.getCreature().getImageUrl())
                .build();
    }
}
