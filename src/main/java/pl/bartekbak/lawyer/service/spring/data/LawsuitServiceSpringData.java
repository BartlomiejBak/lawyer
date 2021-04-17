package pl.bartekbak.lawyer.service.spring.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dao.LawsuitRepository;
import pl.bartekbak.lawyer.dto.LawsuitDTO;
import pl.bartekbak.lawyer.entity.Lawsuit;
import pl.bartekbak.lawyer.service.LawsuitService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LawsuitServiceSpringData implements LawsuitService {

    LawsuitRepository lawsuitRepository;

    @Autowired
    public LawsuitServiceSpringData(LawsuitRepository lawsuitRepository) {
        this.lawsuitRepository = lawsuitRepository;
    }

    @Override
    public List<LawsuitDTO> findAllLawsuits() {
        return lawsuitRepository.findAllByOrderByDeadlineAsc()
                .stream()
                .map(Lawsuit::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public LawsuitDTO findLawsuitById(int id) {
        Optional<Lawsuit> result = lawsuitRepository.findById(id);
        LawsuitDTO lawsuit;
        if (result.isPresent()){
            lawsuit = result.get().toDto();
        } else {
            throw new RuntimeException("Lawsuit id not found");
        }
        return lawsuit;
    }

    @Override
    public void saveLawsuit(LawsuitDTO lawsuit) {
        lawsuitRepository.save(Lawsuit.fromDto(lawsuit));
    }

    @Override
    public void deleteLawsuitById(int id) {
        lawsuitRepository.deleteById(id);
    }
}
