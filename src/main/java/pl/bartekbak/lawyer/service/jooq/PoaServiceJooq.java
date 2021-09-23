package pl.bartekbak.lawyer.service.jooq;

import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dto.PoaDTO;
import pl.bartekbak.lawyer.entity.Poa;
import pl.bartekbak.lawyer.repository.PoaRepository;
import pl.bartekbak.lawyer.service.PoaService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PoaServiceJooq implements PoaService {

    PoaRepository repository;

    public PoaServiceJooq(PoaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PoaDTO> findAllPoa() {
        return repository.list()
                .stream()
                .map(Poa::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PoaDTO findPoaById(int id) {
        Optional<Poa> result = repository.poaById(id);
        PoaDTO poa;
        poa = result.map(Poa::toDto).orElse(null);
        return poa;
    }

    @Override
    public void savePoa(PoaDTO poa) {
        repository.add(Poa.fromDto(poa));
    }

    @Override
    public void deletePoaById(int id) {
        repository.deleteById(id);
    }
}
