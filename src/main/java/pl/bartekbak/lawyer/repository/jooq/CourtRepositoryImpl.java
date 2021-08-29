package pl.bartekbak.lawyer.repository.jooq;

import org.springframework.stereotype.Repository;
import pl.bartekbak.lawyer.entity.Court;
import pl.bartekbak.lawyer.repository.CourtRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourtRepositoryImpl implements CourtRepository {
    @Override
    public List<Court> list() {
        return List.of();
    }

    @Override
    public Optional<Court> courtById(int id) {
        return Optional.empty();
    }

    @Override
    public void add(Court court) {

    }

    @Override
    public void deleteById(int id) {

    }
}
