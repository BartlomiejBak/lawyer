package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.entity.Lawsuit;

import java.util.List;

public interface LawsuitService {
    List<Lawsuit> findAllLawsuits();

    Lawsuit findLawsuitById(int id);

    void saveLawsuit(Lawsuit lawsuit);

    void deleteLawsuitById(int id);
}
