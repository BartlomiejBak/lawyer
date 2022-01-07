package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.dto.LawsuitDTO;

import java.util.List;
import java.util.UUID;

public interface LawsuitService {
    List<LawsuitDTO> findAllLawsuits();

    LawsuitDTO findLawsuitById(UUID id);

    void saveLawsuit(LawsuitDTO lawsuit);

    void deleteLawsuitById(UUID id);
}
