package pl.bartekbak.lawyer.service.jooq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dto.LawsuitDTO;
import pl.bartekbak.lawyer.entity.Lawsuit;
import pl.bartekbak.lawyer.repository.LawsuitRepository;
import pl.bartekbak.lawyer.service.LawsuitService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LawsuitServiceJooq implements LawsuitService {

    LawsuitRepository lawsuitRepository;

    @Autowired
    public LawsuitServiceJooq(LawsuitRepository lawsuitRepository) {
        this.lawsuitRepository = lawsuitRepository;
    }

    @Override
    public List<LawsuitDTO> findAllLawsuits() {
        return lawsuitRepository.list()
                .stream()
                .map(Lawsuit::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public LawsuitDTO findLawsuitById(UUID id) {
        Optional<Lawsuit> result = lawsuitRepository.lawsuitById(id);
        LawsuitDTO lawsuit;
        lawsuit = result.map(Lawsuit::toDto).orElse(null);
        return lawsuit;
    }

    @Override
    public void saveLawsuit(LawsuitDTO lawsuit) {
        lawsuitRepository.add(Lawsuit.fromDto(lawsuit));
    }

    @Override
    public void deleteLawsuitById(UUID id) {
        lawsuitRepository.deleteById(id);
    }
}
