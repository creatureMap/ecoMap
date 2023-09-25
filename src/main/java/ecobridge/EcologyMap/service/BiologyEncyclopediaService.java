package ecobridge.EcologyMap.service;

import ecobridge.EcologyMap.domain.BiologyEncyclopedia;
import ecobridge.EcologyMap.domain.Creature;
import ecobridge.EcologyMap.dto.BiologyEncyclopediaDTO;
import ecobridge.EcologyMap.repository.BiologyEncyclopediaRepository;
import ecobridge.EcologyMap.repository.CreatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BiologyEncyclopediaService {


    @Autowired
    BiologyEncyclopediaRepository biologyEncyclopediaRepository;

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

            biologyEncyclopedias.add(dto);
        }
        return biologyEncyclopedias;
    }

    public List<BiologyEncyclopediaDTO> getUserCreaturesByDetailCategoryName(Long userId, String detailCategoryName) {
        List<BiologyEncyclopedia> userEntries = biologyEncyclopediaRepository.findByUserId(userId);
        List<BiologyEncyclopediaDTO> biologyEncyclopedias = new ArrayList<>();

        for (BiologyEncyclopedia entry : userEntries) {
            Creature creature = entry.getCreature();
            if(entry.getCreature().getDetailCategory().getDetailCategoryName().equals(detailCategoryName)) {
                BiologyEncyclopediaDTO dto = new BiologyEncyclopediaDTO();
                dto.setCreatureId(creature.getCreatureId());
                dto.setCreatureName(creature.getCreatureName());
                dto.setDetailCategoryName(creature.getDetailCategory().getDetailCategoryName());
                dto.setUserId(entry.getUser().getId());

                biologyEncyclopedias.add(dto);
            }
        }
        return biologyEncyclopedias;
    }
}
