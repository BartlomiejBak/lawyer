package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.entity.Lawsuit;

import java.util.List;

public interface LawsuitService {
    public List<Lawsuit> findAllLawsuits();

    public Lawsuit findLawsuitById(int id);

    public void saveLawsuit(Lawsuit lawsuit);

    public void deleteLawsuitById(int id);
}
