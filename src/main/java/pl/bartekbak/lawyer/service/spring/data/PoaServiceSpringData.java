package pl.bartekbak.lawyer.service.spring.data;

import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dao.PoaRepository;
import pl.bartekbak.lawyer.dto.PoaDTO;
import pl.bartekbak.lawyer.entity.Poa;
import pl.bartekbak.lawyer.service.PoaService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PoaServiceSpringData implements PoaService {

    PoaRepository repository;

    public PoaServiceSpringData(PoaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PoaDTO> findAllPoa() {
        return repository.findAllByOrderByPoaIdAsc()
                .stream()
                .map(Poa::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PoaDTO findPoaById(int id) {
        Optional<Poa> result = repository.findById(id);
        PoaDTO poa;
        if (result.isPresent()) {
            poa = result.get().toDto();
        } else {
            throw new RuntimeException("Id not found");
        }
        return poa;
    }

    @Override
    public void savePoa(PoaDTO poa) {
        repository.save(Poa.fromDto(poa));
    }

    @Override
    public void deletePoaById(int id) {
        repository.deleteById(id);
    }
}
