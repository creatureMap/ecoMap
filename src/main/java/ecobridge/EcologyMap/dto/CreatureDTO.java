package ecobridge.EcologyMap.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreatureDTO { // 생물의 위치 정보를 전달하기 위한 데이터 전송 객체(DTO)
    private double creature_latitude;
    private double creature_longitude;
}
