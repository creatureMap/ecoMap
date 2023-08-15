package ecobridge.EcologyMap.dto;



import lombok.Getter;
import lombok.Setter;

@Getter@Setter

public class CreatureLocationDTO {
    private long locationId;
    private double latitude;
    private double longitude;

    public CreatureLocationDTO(long locationId, double latitude, double longitude) {
        this.locationId = locationId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // getter, setter 등 필요한 메서드 작성
}
