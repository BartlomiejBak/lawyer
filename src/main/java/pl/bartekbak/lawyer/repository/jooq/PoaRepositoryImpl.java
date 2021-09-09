package pl.bartekbak.lawyer.repository.jooq;

import org.springframework.stereotype.Repository;
import pl.bartekbak.lawyer.entity.Poa;
import pl.bartekbak.lawyer.repository.PoaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class PoaRepositoryImpl implements PoaRepository {
    @Override
    public List<Poa> list() {
        return List.of();
    }

    @Override
    public Optional<Poa> poaById(int id) {
        return Optional.empty();
    }

    @Override
    public void add(Poa poa) {

    }

    @Override
    public void deleteById(int id) {

    }
}
