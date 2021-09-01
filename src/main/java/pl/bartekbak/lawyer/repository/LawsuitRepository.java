package pl.bartekbak.lawyer.repository;

import pl.bartekbak.lawyer.entity.Lawsuit;

import java.util.List;
import java.util.Optional;

public interface LawsuitRepository {

    List<Lawsuit> list();
    Optional<Lawsuit> lawsuitById(int id);
    void add(Lawsuit lawsuit);
    void deleteById(int id);
}
