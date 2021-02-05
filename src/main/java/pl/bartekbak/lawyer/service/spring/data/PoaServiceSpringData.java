package pl.bartekbak.lawyer.service.spring.data;

import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dao.PoaRepository;
import pl.bartekbak.lawyer.entity.Poa;
import pl.bartekbak.lawyer.service.PoaService;

import java.util.List;
import java.util.Optional;

@Service
public class PoaServiceSpringData implements PoaService {

    PoaRepository repository;

    public PoaServiceSpringData(PoaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Poa> findAllPoa() {
        return repository.findAllByOrderByPoaIdAsc();
    }

    @Override
    public Poa findPoaById(int id) {
        Optional<Poa> result = repository.findById(id);
        Poa poa = null;
        if (result.isPresent()) {
            poa = result.get();
        } else {
            throw new RuntimeException("Id not found");
        }
        return poa;
    }

    @Override
    public void savePoa(Poa poa) {
        repository.save(poa);
    }

    @Override
    public void deletePoaById(int id) {
        repository.deleteById(id);
    }
}
