package pl.bartekbak.lawyer.repository;

import pl.bartekbak.lawyer.entity.Court;

import java.util.List;
import java.util.Optional;

public interface CourtRepository {

    List<Court> list();
    Optional<Court> courtById(int id);
    void add(Court court);
    void update(Court court);
    void deleteById(int id);
}
