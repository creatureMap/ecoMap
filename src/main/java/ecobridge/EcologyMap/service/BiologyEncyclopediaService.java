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

        if(userOptional.isPresent() && creatureOptional.isPresent()) {
            User user = userOptional.get();
            Creature creature = creatureOptional.get();

            System.out.println("User and Creature found: " + user + ", " + creature);

            try {
                BiologyEncyclopedia biology = BiologyEncyclopedia.create(user, creature, new Date());
                return biologyEncyclopediaRepository.save(biology);
            } catch (Exception e) {
                System.out.println("Failed to save BiologyEncyclopedia: " + e.getMessage());
                throw e;
            }
        } else {
            System.out.println("User or Creature not found");
            throw new Exception("User or Creature not found");
        }
    }
}
