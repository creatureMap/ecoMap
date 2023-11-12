package ecobridge.EcologyMap.service;

import ecobridge.EcologyMap.domain.BiologyEncyclopedia;
import ecobridge.EcologyMap.domain.Creature;
import ecobridge.EcologyMap.domain.User;
import ecobridge.EcologyMap.dto.BiologyEncyclopediaDTO;
import ecobridge.EcologyMap.repository.BiologyEncyclopediaRepository;
import ecobridge.EcologyMap.repository.CreatureRepository;
import ecobridge.EcologyMap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class BiologyEncyclopediaService {


    @Autowired
    BiologyEncyclopediaRepository biologyEncyclopediaRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CreatureRepository creatureRepository;

    public List<BiologyEncyclopediaDTO> getUserCreatures(Long userId) {
        List<BiologyEncyclopedia> userEntries = biologyEncyclopediaRepository.findByUserId(userId);
        List<BiologyEncyclopediaDTO> biologyEncyclopedias = new ArrayList<>();

        for (BiologyEncyclopedia entry : userEntries) {
            Creature creature = entry.getCreature();
            BiologyEncyclopediaDTO dto = new BiologyEncyclopediaDTO();
            dto.setCreatureId(creature.getCreatureId());
            dto.setCreatureName(creature.getCreatureName());
            dto.setDetailCategoryName(creature.getDetailCategory().getDetailCategoryName());
            dto.setUserId(entry.getUser().getId());
            dto.setCreatureInformation(creature.getCreatureInformation());
            dto.setCreatureSummaryInformation(creature.getCreatureSummaryInformation());
            dto.setImageUrl(creature.getImageUrl());

            biologyEncyclopedias.add(dto);
        }
        return biologyEncyclopedias;
    }

    public List<BiologyEncyclopediaDTO> getUserCreaturesByDetailCategoryName(Long userId, String detailCategoryName) {
        List<BiologyEncyclopedia> userEntries = biologyEncyclopediaRepository.findByUserId(userId);
        List<BiologyEncyclopediaDTO> biologyEncyclopedias = new ArrayList<>();

        for (BiologyEncyclopedia entry : userEntries) {
            Creature creature = entry.getCreature();
            if (entry.getCreature().getDetailCategory().getDetailCategoryName().equals(detailCategoryName)) {
                BiologyEncyclopediaDTO dto = new BiologyEncyclopediaDTO();
                dto.setCreatureId(creature.getCreatureId());
                dto.setCreatureName(creature.getCreatureName());
                dto.setDetailCategoryName(creature.getDetailCategory().getDetailCategoryName());
                dto.setUserId(entry.getUser().getId());
                dto.setCreatureInformation(creature.getCreatureInformation());
                dto.setCreatureSummaryInformation(creature.getCreatureSummaryInformation());
                dto.setImageUrl(creature.getImageUrl());
                biologyEncyclopedias.add(dto);
            }
        }
        return biologyEncyclopedias;
    }

    public BiologyEncyclopedia addUserCreature(Long userId, Long creatureId) throws Exception {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Creature> creatureOptional = creatureRepository.findById(creatureId);

        if (userOptional.isPresent() && creatureOptional.isPresent()) {
            User user = userOptional.get();
            Creature creature = creatureOptional.get();

            // 해당 유저의 도감에 이미 해당 생물이 있는지 확인
            boolean isCreatureAdded = biologyEncyclopediaRepository.existsByUserAndCreature(user, creature);

            if (isCreatureAdded) {
                // 이미 추가된 경우 예외 발생
                throw new Exception("이미 해당 생물이 도감에 추가되었습니다.");
            } else {
                // 추가되지 않은 경우 도감에 생물 추가
                try {
                    BiologyEncyclopedia biology = BiologyEncyclopedia.create(user, creature, new Date());
                    return biologyEncyclopediaRepository.save(biology);
                } catch (Exception e) {
                    System.out.println("Failed to save BiologyEncyclopedia: " + e.getMessage());
                    throw e;
                }
            }
        } else {
            System.out.println("User or Creature not found");
            throw new Exception("User or Creature not found");
        }
    }

}
