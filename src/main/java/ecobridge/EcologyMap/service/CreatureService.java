//package ecobridge.EcologyMap.service;
//
//import ecobridge.EcologyMap.domain.Creature;
//import ecobridge.EcologyMap.dto.AddCreatureRequest;
//import ecobridge.EcologyMap.repository.CreatureRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//
//import java.util.Optional;
//
//
//@RequiredArgsConstructor //final 이 붙거나 @NotNull 이 붙은 필드의 생성자 추가
//@Service //빈으로 등록
//public class CreatureService {
//
//    private final CreatureRepository creatureRepository;
//
//
//    // 생물 리스트에 생물 추가 메서드
//    public Creature save(AddCreatureRequest request) { //save()로 AddCreatureReqest 클래스에서 저장된 값들을 Creature 데이터베이스에 저장
//        return creatureRepository.save(request.toEntity());
//
//    public static Optional<Object> findAll() {
//        return CreatureRepository.findAll()
//                .orElseThrow(() -> new IllegalArgumentException("not found all list"));
//    }
//
//    //생물 하나 조회하는 메서드
//    public Creature findById(long id) {
//        return CreatureRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
//
//    }
//}
