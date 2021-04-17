package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.dto.LawsuitDTO;

import java.util.List;

public interface LawsuitService {
    List<LawsuitDTO> findAllLawsuits();

    LawsuitDTO findLawsuitById(int id);

    void saveLawsuit(LawsuitDTO lawsuit);

    void deleteLawsuitById(int id);
}
