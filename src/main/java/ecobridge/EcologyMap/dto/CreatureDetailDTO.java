package ecobridge.EcologyMap.dto;
import ecobridge.EcologyMap.domain.CreatureLocation;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatureDetailDTO {

    private Long creatureId;
    private double creatureLatitude;
    private double creatureLongitude;
    private long locationId;
    private Long mainCategoryId;
    private Long detailCategoryId;
    private Long creatureProtectionClass;
    private String creatureName;
    private String imageUrl;
    private boolean spring;
    private boolean summer;
    private boolean fall;
    private boolean winter;
    private String mainCategoryName;
    private String detailCategoryName;
    private String locatioName;
    private String creatureInformation;
    private String creatureSummaryInformation;




    static public CreatureDetailDTO of(CreatureLocation location){
        return CreatureDetailDTO.builder()
                .creatureId(location.getCreature().getCreatureId())
                .creatureLatitude(location.getCreatureLatitude())
                .creatureLongitude(location.getCreatureLongitude())
                .locationId(location.getLocationId())
                .mainCategoryId(location.getCreature().getMainCategory().getMainCategoryId())
                .mainCategoryName(location.getCreature().getMainCategory().getMainCategoryName())
                .detailCategoryName(location.getCreature().getDetailCategory().getDetailCategoryName())
                .detailCategoryId(location.getCreature().getDetailCategory().getDetailCategoryId())
                .creatureProtectionClass(location.getCreature().getCreatureProtectionClass())
                .imageUrl(location.getCreature().getImageUrl())
                .creatureName(location.getCreature().getCreatureName())
                .locatioName(location.getLocationName())
                .creatureInformation(location.getCreature().getCreatureInformation())
                .creatureSummaryInformation(location.getCreature().getCreatureSummaryInformation())

                .build();
    }
}
