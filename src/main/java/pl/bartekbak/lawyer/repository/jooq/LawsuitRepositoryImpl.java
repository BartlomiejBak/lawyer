package pl.bartekbak.lawyer.repository.jooq;

import org.springframework.stereotype.Repository;
import pl.bartekbak.lawyer.entity.Lawsuit;
import pl.bartekbak.lawyer.repository.LawsuitRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class LawsuitRepositoryImpl implements LawsuitRepository {
    @Override
    public List<Lawsuit> list() {
        return List.of();
    }

    @Override
    public Optional<Lawsuit> lawsuitById(int id) {
        return Optional.empty();
    }

    @Override
    public void add(Lawsuit lawsuit) {

    }

    @Override
    public void deleteById(int id) {

    }
}
